package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.CarFacade;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.hateoas.CarRepresentationModelAssembler;
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
import org.springframework.web.server.ServerErrorException;

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

    public CarController(@Autowired CarFacade carFacade, @Autowired UserFacade userFacade,
                         @Autowired CarRepresentationModelAssembler carRepresentationModelAssembler) {
        this.carFacade = carFacade;
        this.carRepresentationModelAssembler = carRepresentationModelAssembler;
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CarDTO>>> getCars(){
        CollectionModel<EntityModel<CarDTO>> carDTOCollectionModel
                = carRepresentationModelAssembler.toCollectionModel(carFacade.getAllCars());
        return new ResponseEntity<>(carDTOCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<CarDTO>> getCar(@PathVariable("id") long id) throws Exception{
        CarDTO carDTO = carFacade.getCarWithId(id);
        if (carDTO == null){
            throw new ResourceNotFoundException("Car " + id + "not found");
        }
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carDTO);
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CarDTO>> createCar(@RequestBody @Valid CarCreateDTO car, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during car creating");
        }

        Long id = carFacade.createCar(car);
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carFacade.getCarWithId(id));
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CarDTO>> createCarByCustomer(@RequestBody @Valid CarCreateDTO car, @PathVariable("id") long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidRequestException("Failed validation during car creating");
        }

        UserDTO user = userFacade.getUserWithId(id);

        Long carId = carFacade.createCar(car);
        userFacade.addCarToUser(user.getId(), carId);
        EntityModel<CarDTO> carModel = carRepresentationModelAssembler.toModel(carFacade.getCarWithId(carId));
        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCar(@PathVariable("id") long id) throws Exception{
        try{
            carFacade.deleteCar(id);
        } catch (IllegalArgumentException ex){
            throw new ResourceNotFoundException("Car with id " + id + " cannot be found.");
        }catch (Throwable ex){
            Throwable rootCause = ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
            }
            throw new ServerErrorException(rootCause.getMessage());

        }
    }

    @RequestMapping(value = "/getByUser/{id}", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CarDTO>>> getOrdersByUser(@PathVariable("id") long id) throws Exception {

        List<CarDTO> carsListDTO = carFacade.getCarsByUser(id);

        if (carsListDTO == null) throw new ResourceNotFoundException("cars " + id + " not found");

        CollectionModel<EntityModel<CarDTO>> ordersCollectionModel = carRepresentationModelAssembler.toCollectionModel(carsListDTO);

        return new ResponseEntity<>(ordersCollectionModel, HttpStatus.OK);
    }

}
