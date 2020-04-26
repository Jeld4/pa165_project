package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Tire;

import java.util.List;

/**
 * @author Radim Sasinka. 456315
 */

public interface CarService {

    /**
     * Returns a Car according to the given ID
     * @param id ID of car to be found
     * @return Found car
     */
    Car findById(Long id);

    /**
     * Returns list of all cars
     * @return List of all cars
     */
    List<Car> findAll();

    /**
     * Returns a car according to its licencePlate
     * @param licencePlate String of licence plate.
     * @return Car object
     */
    Car findByLicencePlate(String licencePlate);

    /**
     * Creates a car in the system
     * @param car Car to be created
     */
    void create(Car car);

    /**
     * Removes a car from the system
     * @param car Car to be removed
     */
    void remove(Car car);

    /**
     * Changes a tires in the given car
     * @param carId id of car which tires should be changed
     * @param tire Tire to be changed
     */
    void changeTire(Long carId, Tire tire);
}
