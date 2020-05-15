package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.ApiUris;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Radim Sasinka, 456315
 */

@RestController
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources(){

        Map<String, String> resourcesMap = new HashMap<>();
        resourcesMap.put("tire_uri", ApiUris.ROOT_URI_TIRES);

        return Collections.unmodifiableMap(resourcesMap);
    }
}
