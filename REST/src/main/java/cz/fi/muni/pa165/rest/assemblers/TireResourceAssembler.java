package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.rest.controllers.TiresControllerHateoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 *
 * @author brossi
 */
@Component
public class TireResourceAssembler implements RepresentationModelAssembler<TireDTO, EntityModel<TireDTO>> {

    @Override
    public EntityModel<TireDTO> toModel(TireDTO TireDTO) {
        EntityModel<TireDTO> TireResource = new EntityModel<>(TireDTO);

        try {
            TireResource.add(linkTo(TiresControllerHateoas.class).slash(TireDTO.getId()).withSelfRel());
            TireResource.add(linkTo(TiresControllerHateoas.class).slash(TireDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(TireResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from TiresControllerHateoas", ex);
        }

        return TireResource;
    }
}
