package java.cz.fi.muni.pa165.hateoas;

import java.cz.fi.muni.pa165.controllers.TiresRestController;
import cz.fi.muni.pa165.dto.TireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Converts ProductDTO instance into ProductResource, which is later rendered into HAL JSON format with _links.
 *
 * @author Martin Kuba makub@ics.muni.cz
 *
 */
@Component
public class TireRepresentationModelAssembler implements RepresentationModelAssembler<TireDTO, EntityModel<TireDTO>> {

    private final static Logger log = LoggerFactory.getLogger(TireRepresentationModelAssembler.class);

    @Override
    public EntityModel<TireDTO> toModel(TireDTO tireDTO) {
        long id = tireDTO.getId();
        EntityModel<TireDTO> tireResource = new EntityModel<>(tireDTO);
        try {
            tireResource.add(linkTo(TiresRestController.class).slash(tireDTO.getId()).withSelfRel());

            Method deleteTire = TiresRestController.class.getMethod("deleteTire", long.class);
            tireResource.add(linkTo(deleteTire.getDeclaringClass(),deleteTire, id).withRel("delete"));

            } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return tireResource;
    }
}
