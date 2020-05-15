package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.hateoas.TireRepresentationModelAssembler;
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

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(TireDTO.class)
@RequestMapping("/tires")
public class TireController {


    private TireController(@Autowired TireFacade tireFacade, @Autowired TireRepresentationModelAssembler tireRepresentationModelAssembler){
        this.tireFacade = tireFacade;
        this.tireRepresentationModelAssembler = tireRepresentationModelAssembler;

    }

    private TireFacade tireFacade;
    private TireRepresentationModelAssembler tireRepresentationModelAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<TireDTO>>> getTires(){
        // log.debug("rest getProducts");
        CollectionModel<EntityModel<TireDTO>> tiresCollectionModel = tireRepresentationModelAssembler.toCollectionModel(tireFacade.getAllTires());
        //tiresCollectionModel.add(linkTo(TireController.class).withSelfRel());
        //tiresCollectionModel.add(linkTo(TireController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(tiresCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<TireDTO>> createTire(@RequestBody @Valid TireCreateDTO tire, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation");
        }
        Long id = tireFacade.createTire(tire);
        EntityModel<TireDTO> tireModel = tireRepresentationModelAssembler.toModel(tireFacade.getTireWithId(id));
        return new ResponseEntity<>(tireModel, HttpStatus.OK);
    }
}
