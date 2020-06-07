package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.UserRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michal Klíma, 456234	
 */
@RestController
@ExposesResourceFor(UserDTO.class)
@RequestMapping("/users")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(ServiceController.class);

    private UserController(@Autowired UserFacade userFacade, @Autowired UserRepresentationModelAssembler userRepresentationModelAssembler){
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;

    }

    private UserFacade userFacade;
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    /**
     * REST function for returning all users
     * @return Response entity containing all users
     */
    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<UserDTO>>> getUsers(){
        log.debug("Controller - get all users");
        CollectionModel<EntityModel<UserDTO>> usersCollectionModel = userRepresentationModelAssembler.toCollectionModel(userFacade.getAllUsers());
        return new ResponseEntity<>(usersCollectionModel, HttpStatus.OK);
    }
    
    /**
     * REST function for getting one specific user
     * @param id ID of the user
     * @return Response entity containing desired user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getUser(@PathVariable("id") long id) {
        log.debug("Controller - get user by id");
        UserDTO userDTO = userFacade.getUserWithId(id);
        if (userDTO == null) throw new ResourceNotFoundException("user " + id + " not found");
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userDTO);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
 
    /**
     * REST function for getting one specific user by login
     * @param login of the user
     * @return Response entity containing desired user
     */
    @RequestMapping(value = "login/{login}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getUserByLogin(@PathVariable("login") String login) {
        
        UserDTO userDTO = userFacade.getUserWithLogin(login);
        log.debug("Controller - get user by login");
        if (userDTO == null) throw new ResourceNotFoundException("user " + login + " not found");
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userDTO);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
    
    /**
     * Creates user
     * @param user User to be created
     * @param bindingResult bindingResult
     * @return Response entity containing new created user
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public final HttpEntity<EntityModel<UserDTO>> createUser(@RequestBody UserCreateDTO user, BindingResult bindingResult) {
       
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        log.debug("Controller - create user");
        Long id = userFacade.createUser(user);
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userFacade.getUserWithId(id));
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    /**
     * REST Function for deleting specific user
     * @param id ID of the user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteUser(@PathVariable("id") long id) {
        try {
            userFacade.deleteUser(id);
            log.debug("Controller - delete user");
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("user " + id + " not found");
        }
    }

    /**
     * REST function for getting one specific user profile
     * @param id ID of the user
     * @return Response entity containing desired user
     */
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getUserProfile(@PathVariable("id") long id) {

        UserDTO userDTO = userFacade.getUserWithId(id);
        log.debug("Controller - get user profile");
        if (userDTO == null) throw new ResourceNotFoundException("user " + id + " not found");
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userDTO);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{userId}/{carId}", method = RequestMethod.POST)
    public final void deleteOrder(@PathVariable("userId") long userId, @PathVariable("carId") long carId) {
        log.debug("rest deleteRemoveCarFromUser({},{})", userId,carId);
        try {
            userFacade.removeCarFromUser(userId, carId);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("error when removing car " + carId + " from user " + userId);
        }
    }
    
}
