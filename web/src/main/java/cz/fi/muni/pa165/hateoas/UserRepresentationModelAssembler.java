package cz.fi.muni.pa165.hateoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cz.fi.muni.pa165.controllers.UserController;
import cz.fi.muni.pa165.dto.UserDTO;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

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

            } catch (Exception ex){
            log.error("cannot link HATEOAS", ex);
        }
        return userResource;
    }
}
