package java.cz.fi.muni.pa165.controllers;

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

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import java.cz.fi.muni.pa165.exceptions.InvalidRequestException;
import java.cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import java.cz.fi.muni.pa165.exceptions.ServerProblemException;
import cz.fi.muni.pa165.facade.TireFacade;
import java.cz.fi.muni.pa165.hateoas.TireRepresentationModelAssembler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * SpringMVC controller for managing REST requests for the product resources. Conforms to HATEOAS principles.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@RestController
@ExposesResourceFor(TireDTO.class)
@RequestMapping("/tires")
public class TiresRestController {

    private final static Logger log = LoggerFactory.getLogger(TiresRestController.class);

    public TiresRestController(@Autowired TireFacade productFacade,@Autowired TireRepresentationModelAssembler productRepresentationModelAssembler) {
        this.tireFacade = productFacade;
        this.tireRepresentationModelAssembler = productRepresentationModelAssembler;
    }

    private TireFacade tireFacade;

    private TireRepresentationModelAssembler tireRepresentationModelAssembler;


    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<TireDTO>>> getProducts() {
        log.debug("rest getProducts()");
        CollectionModel<EntityModel<TireDTO>> productsCollectionModel = tireRepresentationModelAssembler.toCollectionModel(tireFacade.getAllTires());
        productsCollectionModel.add(linkTo(TiresRestController.class).withSelfRel());
        productsCollectionModel.add(linkTo(TiresRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(productsCollectionModel, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<TireDTO>> getProduct(@PathVariable("id") long id) throws Exception {
        log.debug("rest getProduct({})", id);
        TireDTO tireDTO = tireFacade.getTireWithId(id);
        if (tireDTO == null) throw new ResourceNotFoundException("product " + id + " not found");
        EntityModel<TireDTO> productModel = tireRepresentationModelAssembler.toModel(tireDTO);
        return new ResponseEntity<>(productModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteProduct(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteProduct({})", id);
        try {
            tireFacade.deleteTire(id);
        } catch (IllegalArgumentException ex) {
            log.error("product " + id + " not found");
            throw new ResourceNotFoundException("product " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete product " + id + " :" + ex.getMessage());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<TireDTO>> createProduct(@RequestBody @Valid TireCreateDTO tire, BindingResult bindingResult) throws Exception {
        log.debug("rest createProduct()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        Long id = tireFacade.createTire(tire);
        EntityModel<TireDTO> productModel = tireRepresentationModelAssembler.toModel(tireFacade.getTireWithId(id));
        return new ResponseEntity<>(productModel, HttpStatus.OK);
    }
}
