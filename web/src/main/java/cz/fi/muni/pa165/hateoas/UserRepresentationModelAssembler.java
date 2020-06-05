package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.UserController;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.FailToLinkHateosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(UserRepresentationModelAssembler.class);

    @Override
    public EntityModel<UserDTO> toModel(UserDTO userDTO) {
        long id = userDTO.getId();
        EntityModel<UserDTO> userResource = new EntityModel<>(userDTO);
        try {
            userResource.add(linkTo(UserController.class).slash(userDTO.getId()).withSelfRel());

            Method deleteUser = UserController.class.getMethod("deleteUser", long.class);
            userResource.add(linkTo(deleteUser.getDeclaringClass(),deleteUser, id).withRel("delete"));

            } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
            throw new FailToLinkHateosException("Unable to link HATEOAS for UserDTO");
        }
        return userResource;
    }
}
