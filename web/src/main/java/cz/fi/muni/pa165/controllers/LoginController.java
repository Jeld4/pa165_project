package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.UserRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Radim Sasinka, 456315
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserFacade userFacade;
    private UserRepresentationModelAssembler userRepresentationModelAssembler;
    private PasswordEncoder passwordEncoder;

    public LoginController(@Autowired UserFacade userFacade,
                           @Autowired UserRepresentationModelAssembler userRepresentationModelAssembler,
                           @Autowired PasswordEncoder passwordEncoder) {
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<UserDTO>> login(@RequestBody @Valid UserCreateDTO userCreateDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during user login validation");
        }
        UserDTO foundUser = userFacade.getUserWithLogin(userCreateDTO.getLogin());
        if (foundUser != null && passwordEncoder.matches(userCreateDTO.getPassword(), foundUser.getPassword())){
            EntityModel<UserDTO> userModel = userRepresentationModelAssembler.toModel(foundUser);
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}
