package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.config.RestSpringMVCConfig;
import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.UserRepresentationModelAssembler;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Import(RestSpringMVCConfig.class)
public class LoginController {

    private UserFacade userFacade;
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    public LoginController(@Autowired UserFacade userFacade,
                           @Autowired UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    @RequestMapping(method = RequestMethod.POST)
    public final HttpStatus login(@RequestBody UserCreateDTO userCreateDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during user login validation");
        }
        UserDTO foundUser = userFacade.getUserWithLogin(userCreateDTO.getLogin());
        if (foundUser != null && DigestUtils.md2Hex(userCreateDTO.getPassword()).equals(foundUser.getPassword())){
            return HttpStatus.OK;
        }else{
            return HttpStatus.FORBIDDEN;
        }
    }
}