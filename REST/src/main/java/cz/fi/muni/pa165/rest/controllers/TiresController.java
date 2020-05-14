package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.rest.ApiUris;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.fi.muni.pa165.facade.TireFacade;

import cz.fi.muni.pa165.rest.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Tires
 *
 * @author brossi
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TIRES)
public class TiresController {

    final static Logger logger = LoggerFactory.getLogger(TiresController.class);

    @Inject
    private TireFacade TireFacade;

    /**
     * Get list of Tires curl -i -X GET
     * http://localhost:8080/eshop-rest/Tires
     *
     * @return TireDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TireDTO> getTires() {
        logger.debug("rest getTires()");
        return TireFacade.getAllTires();
    }

    /**
     *
     * Get Tire by identifier id curl -i -X GET
     * http://localhost:8080/eshop-rest/Tires/1
     *
     * @param id identifier for a Tire
     * @return TireDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TireDTO getTire(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getTire({})", id);
        
        TireCreateDTO tireCreateDTO;
        
        tireCreateDTO = new TireCreateDTO();
        tireCreateDTO.setManufacturer("Michellin");
        tireCreateDTO.setPrice(new BigDecimal(7500));
        tireCreateDTO.setType("SuperBlack");
        
        Long idX = TireFacade.createTire(tireCreateDTO);
        
        TireDTO TireDTO = TireFacade.getTireWithId(idX);
        if (TireDTO != null) {
            return TireDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one Tire by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/Tires/1
     *
     * @param id identifier for Tire
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTire(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteTire({})", id);
        try {
            TireFacade.deleteTire(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new Tire by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"test","description":"test","color":"UNDEFINED","price":"200",
     * "currency":"CZK", "categoryId":"1"}' 
     * http://localhost:8080/eshop-rest/Tires/create
     * 
     * @param Tire TireCreateDTO with required fields for creation
     * @return the created Tire TireDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TireDTO createTire(@RequestBody TireCreateDTO Tire) throws Exception {

        logger.debug("rest createTire()");

        try {
            Long id = TireFacade.createTire(Tire);
            return TireFacade.getTireWithId(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }




}
