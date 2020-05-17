package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.UserRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;

import javax.validation.Valid;

@RestController
@ExposesResourceFor(UserDTO.class)
@RequestMapping("/users")
public class UserController {


    private UserController(@Autowired UserFacade userFacade, @Autowired UserRepresentationModelAssembler userRepresentationModelAssembler){
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;

    }

    private UserFacade userFacade;
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<UserDTO>>> getUsers(){
        // log.debug("rest getUsers");
        CollectionModel<EntityModel<UserDTO>> usersCollectionModel = userRepresentationModelAssembler.toCollectionModel(userFacade.getAllUsers());
        //usersCollectionModel.add(linkTo(UserController.class).withSelfRel());
        //usersCollectionModel.add(linkTo(UserController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(usersCollectionModel, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getUser(@PathVariable("id") long id) throws Exception {
        
        UserDTO userDTO = userFacade.getUserWithId(id);
        if (userDTO == null) throw new ResourceNotFoundException("user " + id + " not found");
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userDTO);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
    

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<UserDTO>> createUser(@RequestBody @Valid UserCreateDTO user, BindingResult bindingResult) throws Exception {
       
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        Long id = userFacade.createUser(user);
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userFacade.getUserWithId(id));
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteUser(@PathVariable("id") long id) throws Exception {
        try {
            userFacade.deleteUser(id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("user " + id + " not found");
        } catch (Throwable ex) {
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
            }
            throw new ServerErrorException(rootCause.getMessage());
        }
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getUserProfile(@PathVariable("id") long id) throws Exception {

        UserDTO userDTO = userFacade.getUserWithId(id);
        if (userDTO == null) throw new ResourceNotFoundException("user " + id + " not found");
        EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(userDTO);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
    
}
