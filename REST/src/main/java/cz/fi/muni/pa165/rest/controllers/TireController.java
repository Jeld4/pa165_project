package cz.fi.muni.pa165.rest.controllers;


import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.facade.TireFacade;
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
 * @author Radim Sasinka, 456315
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_TIRES)
public class TireController {

    final static Logger logger = LoggerFactory.getLogger(TireController.class);

    @Inject
    private TireFacade tireFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TireDTO> getTires(){
        logger.debug("rest getTires()");
        return tireFacade.getAllTires();
    }
}
