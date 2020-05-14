package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.rest.assemblers.TireResourceAssembler;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

/**
 * REST HATEOAS Controller for Tires
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link cz.fi.muni.pa165.rest.assemblers.TireResourceAssembler} to create
 * resources to be returned as ResponseEntities
 *
 */
@RestController
@RequestMapping("/Tires_hateoas")
public class TiresControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(TiresControllerHateoas.class);

    @Inject
    private TireFacade TireFacade;

    @Inject
    private TireResourceAssembler TireResourceAssembler;

    /**
     *
     * Get list of Tires
     * 
     * @return HttpEntity<CollectionModel<EntityModel<TireDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<TireDTO>>> getTires() {
        
        logger.debug("rest getTires({}) hateoas");

        Collection<TireDTO> TiresDTO = TireFacade.getAllTires();
        Collection<EntityModel<TireDTO>> TireResourceCollection = new ArrayList<>();

        for (TireDTO p : TiresDTO) {
            TireResourceCollection.add(TireResourceAssembler.toModel(p));
        }

        CollectionModel<EntityModel<TireDTO>> TiresResources = new CollectionModel<>(TireResourceCollection);
        TiresResources.add(linkTo(TiresControllerHateoas.class).withSelfRel());

        return new ResponseEntity<>(TiresResources, HttpStatus.OK);

    }
    
    /**
     *
     * Get list of Tires - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * 
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * 
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/eshop-rest/Tires_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     * 
     * @return HttpEntity<CollectionModel<EntityModel<TireDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<TireDTO>>> getTiresCached(WebRequest webRequest) {
        
        logger.debug("rest getTires({}) hateoas cached version");
       
        final Collection<TireDTO> TiresDTO = TireFacade.getAllTires();
        final Collection<EntityModel<TireDTO>> TireResourceCollection = new ArrayList<>();

        for (TireDTO p : TiresDTO) {
            TireResourceCollection.add(TireResourceAssembler.toModel(p));
        }

        CollectionModel<EntityModel<TireDTO>> TiresResources = new CollectionModel<>(TireResourceCollection);
        TiresResources.add(linkTo(TiresControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(TiresResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(TiresResources);
    }

    /**
     *
     * Get one Tire according to id
     * 
     * @param id identifier for Tire
     * @return HttpEntity<EntityModel<TireDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<TireDTO>> getTire(@PathVariable("id") long id) throws Exception {
        
        logger.debug("rest getTire({}) hateoas", id);

        try {
        TireDTO TireDTO = TireFacade.getTireWithId(id);
            EntityModel<TireDTO> resource = TireResourceAssembler.toModel(TireDTO);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception ex){
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
        logger.debug("rest deleteTire({}) hateoas", id);
        try {
            TireFacade.deleteTire(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}
