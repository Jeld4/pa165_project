package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.OrderController;
import cz.fi.muni.pa165.controllers.ServiceController;
import cz.fi.muni.pa165.dto.ServiceDTO;
import cz.fi.muni.pa165.exceptions.FailToLinkHateosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Radim Sasinka, 456315
 */

@Component
public class ServiceRepresentationModelAssembler implements RepresentationModelAssembler<ServiceDTO, EntityModel<ServiceDTO>> {

    private final static Logger log = LoggerFactory.getLogger(ServiceRepresentationModelAssembler.class);

    @Override
    public EntityModel<ServiceDTO> toModel(ServiceDTO serviceDTO) {
        long id = serviceDTO.getId();
        EntityModel<ServiceDTO> serviceResource = new EntityModel<>(serviceDTO);
        try {
            serviceResource.add(linkTo(OrderController.class).slash(serviceDTO.getId()).withSelfRel());

            Method deleteService = ServiceController.class.getMethod("deleteService", long.class);
            serviceResource.add(linkTo(deleteService.getDeclaringClass(), deleteService, id).withSelfRel());
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
            throw new FailToLinkHateosException("Unable to link HATEOAS for ServiceDTO");
        }
        return serviceResource;
    }
}
