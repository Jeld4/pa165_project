package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.ServiceFacade;
import cz.fi.muni.pa165.hateoas.ServiceRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Radim Sasinka, 456315
 */

@RestController
@ExposesResourceFor(ServiceDTO.class)
@RequestMapping("/services")
public class ServiceController {

    private ServiceFacade serviceFacade;
    private ServiceRepresentationModelAssembler serviceRepresentationModelAssembler;

    public ServiceController(@Autowired ServiceFacade serviceFacade,@Autowired ServiceRepresentationModelAssembler serviceRepresentationModelAssembler) {
        this.serviceFacade = serviceFacade;
        this.serviceRepresentationModelAssembler = serviceRepresentationModelAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    private final HttpEntity<CollectionModel<EntityModel<ServiceDTO>>> getServices(){
        CollectionModel<EntityModel<ServiceDTO>> servicesCollectionModel
                = serviceRepresentationModelAssembler.toCollectionModel(serviceFacade.getAllServices());
        return new ResponseEntity<>(servicesCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<ServiceDTO>> getService(@PathVariable("id") long id) throws Exception{
        ServiceDTO serviceDTO = serviceFacade.getServiceWithId(id);
        if (serviceDTO == null){
            throw new ResourceNotFoundException("Resource with id " + id + " was not found.");
        }
        EntityModel<ServiceDTO> serviceModel = serviceRepresentationModelAssembler.toModel(serviceDTO);
        return new ResponseEntity<>(serviceModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<ServiceDTO>> createService(@RequestBody @Valid ServiceCreateDTO serviceCreateDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        Long id = serviceFacade.createService(serviceCreateDTO);
        EntityModel<ServiceDTO> serviceModel = serviceRepresentationModelAssembler.toModel(serviceFacade.getServiceWithId(id));
        return new ResponseEntity<>(serviceModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteService(@PathVariable("id") long id) throws Exception {
        try {
            serviceFacade.deleteService(id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Service with id " + id + " cannot be found.");
        }
    }
}
