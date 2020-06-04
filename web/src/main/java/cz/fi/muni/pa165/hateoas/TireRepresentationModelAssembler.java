package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.TireController;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.exceptions.FailToLinkHateosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Jakub Malý, 456389
 */

@Component
public class TireRepresentationModelAssembler implements RepresentationModelAssembler<TireDTO, EntityModel<TireDTO>> {

    private final static Logger log = LoggerFactory.getLogger(TireRepresentationModelAssembler.class);

    @Override
    public EntityModel<TireDTO> toModel(TireDTO tireDTO) {
        long id = tireDTO.getId();
        EntityModel<TireDTO> tireResource = new EntityModel<>(tireDTO);
        try {
            tireResource.add(linkTo(TireController.class).slash(tireDTO.getId()).withSelfRel());

            Method deleteTire = TireController.class.getMethod("deleteTire", long.class);
            tireResource.add(linkTo(deleteTire.getDeclaringClass(), deleteTire, id).withSelfRel());
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
            throw new FailToLinkHateosException("Unable to link HATEOAS for TireDTO");
        }
        return tireResource;
    }
}
