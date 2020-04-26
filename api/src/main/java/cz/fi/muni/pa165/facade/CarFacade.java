package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.TireDTO;

import java.util.List;
/**
 * @author Radim Sasinka
 */
public interface CarFacade {
    /**
     * Creates Car in the system
     * @param car Object of CarCreateDTO type
     * @return Long of cars
     */
    Long createCar(CarCreateDTO car);

    /**
     * Deletes Car from the system
     * @param carId ID number of the car, Long
     */
    void deleteCar(Long carId);

    /**
     * Returns all cars in the system
     * @return Cars in the lists
     */
    List<CarDTO> getAllCars();

    /**
     * Returns specific cars, described by its ID.
     * @param id ID of the car
     * @return CarDTO object of desired car
     */
    CarDTO getCarWithId(Long id);

    /**
     * Returns a car according to its licencePlate
     * @param licencePlate String of licence plate.
     * @return CardDTO object
     */
    CarDTO getCarWithLicencePlate(String licencePlate);

    /**
     * Changes tire assinged to the car
     * @param car Car which tires should be changed
     * @param tire Tire which should be changed
     */
    void changeTireType(CarDTO car, TireDTO tire);
}
