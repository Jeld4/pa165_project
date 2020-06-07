package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.CarFacade;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.CarRepresentationModelAssembler;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

@RestController
@ExposesResourceFor(CarDTO.class)
@RequestMapping("/cars")
public class CarController {

    private CarFacade carFacade;
    private CarRepresentationModelAssembler carRepresentationModelAssembler;
    private UserFacade userFacade;
    private final static Logger log = LoggerFactory.getLogger(CarController.class);

    public CarController(@Autowired CarFacade carFacade, @Autowired UserFacade userFacade,
                         @Autowired CarRepresentationModelAssembler carRepresentationModelAssembler) {
        this.carFacade = carFacade;
        this.carRepresentationModelAssembler = carRepresentationModelAssembler;
        this.userFacade = userFacade;
    }


    /**
     * REST function for returning all cars
     * @return Response entity containing all cars
     */
    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CarDTO>>> getCars(){
        log.debug("Controller - get all cars");
        CollectionModel<EntityModel<CarDTO>> carDTOCollectionModel
                = carRepresentationModelAssembler.toCollectionModel(carFacade.getAllCars());
        return new ResponseEntity<>(carDTOCollectionModel, HttpStatus.OK);
    }

    /**
     * REST function for getting one specific car
     * @param id ID of the car
     * @return Response entity containing desired car
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<CarDTO>> getCar(@PathVariable("id") long id) {
        CarDTO carDTO = carFacade.getCarWithId(id);
        if (carDTO == null){
            throw new ResourceNotFoundException("Car " + id + "not found");
        }
        log.debug("Controller - get car with ID {}", id);
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carDTO);
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    /**
     * REST function for creating new car
     * @param car CarCreateDTO obj of the new car
     * @param bindingResult  bindingResult
     * @return Response entity containing new created car
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CarDTO>> createCar(@RequestBody @Valid CarCreateDTO car, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during car creating");
        }
        log.debug("Controller - create car");
        Long id = carFacade.createCar(car);
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carFacade.getCarWithId(id));
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    /**
     * Creates car under specific customer
     * @param car Car to be created
     * @param id ID of the user
     * @param bindingResult bindingResult
     * @return Response entity containing new created car
     */
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CarDTO>> createCarByCustomer(@RequestBody @Valid CarCreateDTO car, @PathVariable("id") long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during car creating");
        }

        UserDTO user = userFacade.getUserWithId(id);

        Long carId = carFacade.createCar(car);
        log.debug("Controller -  create car by customer");
        userFacade.addCarToUser(user.getId(), carId);
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carFacade.getCarWithId(carId));
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    /**
     * REST Function for deleting specific car
     * @param id ID of the car
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCar(@PathVariable("id") long id) {
        try{
            carFacade.deleteCar(id);
            log.debug("Controller - delete car with ID {}", id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Car with id " + id + " cannot be found.");
        }
    }

    /**
     * Returns all cars under specific user
     * @param id ID of the user
     * @return Response entity containing collection of cars belonging to the user
     */
    @RequestMapping(value = "/getByUser/{id}", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CarDTO>>> getOrdersByUser(@PathVariable("id") long id) {

        log.debug("Controller - get cars by user with ID {}", id);


        List<CarDTO> carsListDTO = carFacade.getCarsByUser(id);

        if (carsListDTO == null) throw new ResourceNotFoundException("cars " + id + " not found");

        CollectionModel<EntityModel<CarDTO>> ordersCollectionModel = carRepresentationModelAssembler.toCollectionModel(carsListDTO);

        return new ResponseEntity<>(ordersCollectionModel, HttpStatus.OK);
    }

}
