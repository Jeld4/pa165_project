package cz.fi.muni.pa165.rest.controllers;


import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michal Klima, 456234
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(TireController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDTO> getTires(){
        logger.debug("rest getUsers()");
        return userFacade.getAllUsers();
    }
}
