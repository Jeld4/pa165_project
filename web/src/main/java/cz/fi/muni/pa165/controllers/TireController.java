package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.TireFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/tires")
public class TireController {

    @Inject
    private TireFacade tireFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final TireDTO getTire(@PathVariable("id") long id){
        TireDTO tireDTO = tireFacade.getTireWithId(id);
        if (tireDTO != null){
            return tireDTO;
        }else {
            throw new ResourceNotFoundException("Tire with id " + id + " not found");
        }
    }
}
