package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.hateoas.TireRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.persistence.TransactionRequiredException;
import javax.validation.Valid;

/**
 * @author Jakub Malý, 456389
 */

@RestController
@ExposesResourceFor(TireDTO.class)
@RequestMapping("/tires")
public class TireController {

    private TireFacade tireFacade;
    private TireRepresentationModelAssembler tireRepresentationModelAssembler;

    private final static Logger log = LoggerFactory.getLogger(ServiceController.class);

    public TireController(@Autowired TireFacade tireFacade,
                          @Autowired TireRepresentationModelAssembler tireRepresentationModelAssembler){
        this.tireFacade = tireFacade;
        this.tireRepresentationModelAssembler = tireRepresentationModelAssembler;

    }

    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<TireDTO>>> getTires(){
        log.debug("Controller - get all tires");
        CollectionModel<EntityModel<TireDTO>> tiresCollectionModel
                = tireRepresentationModelAssembler.toCollectionModel(tireFacade.getAllTires());
        return new ResponseEntity<>(tiresCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<TireDTO>> getTire(@PathVariable("id") long id) {
        TireDTO tireDTO = tireFacade.getTireWithId(id);
        log.debug("Controller - get tire by ID");
        if (tireDTO == null){
            throw new ResourceNotFoundException("Tire " + id + "not found");
        }
        EntityModel<TireDTO> tireModel = tireRepresentationModelAssembler.toModel(tireDTO);
        return new ResponseEntity<>(tireModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<TireDTO>> createTire(@RequestBody @Valid TireCreateDTO tire,
                                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation");
        }
        log.debug("Controller - create tire");
        Long id = tireFacade.createTire(tire);
        EntityModel<TireDTO> tireModel = tireRepresentationModelAssembler.toModel(tireFacade.getTireWithId(id));
        return new ResponseEntity<>(tireModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteTire(@PathVariable("id") long id) {
        try {
            tireFacade.deleteTire(id);
            log.debug("Controller - delete tire with ID {}", id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Tire with id " + id + " cannot be found.");
        } catch (TransactionRequiredException ex) {
        	throw new InvalidRequestException("You cant delete tire which is part of some order.");
        }
    }

}
