package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.config.RestSpringMVCConfig;
import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.UserRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Radim Sasinka, 456315
 */

@RestController
@RequestMapping("/login")
@Import(RestSpringMVCConfig.class)
public class LoginController {

    private UserFacade userFacade;
    private UserRepresentationModelAssembler userRepresentationModelAssembler;
    private final static Logger log = LoggerFactory.getLogger(LoginController.class);


    public LoginController(@Autowired UserFacade userFacade,
                           @Autowired UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    /**
     * REST method for checking user password
     * @param userCreateDTO user that tries to log in
     * @param bindingResult bindingResult
     * @return either OK or UNAUTHORIZED HTTP status
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public final ResponseEntity login(@RequestBody UserCreateDTO userCreateDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during user login validation");
        }
        UserDTO foundUser = userFacade.getUserWithLogin(userCreateDTO.getLogin());
        if(foundUser == null){return null;}
        if (userFacade.checkPassword(foundUser.getId(), userCreateDTO.getPassword())){
            log.error("Controller - login returning ok status");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else{
            log.error("Controller - login returning unauthorized status");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
