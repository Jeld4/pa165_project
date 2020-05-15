package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.TireController;
import cz.fi.muni.pa165.dto.TireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class TireRepresentationModelAssembler implements RepresentationModelAssembler<TireDTO, EntityModel<TireDTO>> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(TireRepresentationModelAssembler.class);

    @Override
    public EntityModel<TireDTO> toModel(TireDTO tireDTO) {
        long id = tireDTO.getId();
        EntityModel<TireDTO> tireResource = new EntityModel<>(tireDTO);
        try {
            tireResource.add(linkTo(TireController.class).slash(tireDTO.getId()).withSelfRel());
        } catch (Exception ex){
            log.error("cannot link HATEOAS", ex);
        }
        return tireResource;
    }
}
