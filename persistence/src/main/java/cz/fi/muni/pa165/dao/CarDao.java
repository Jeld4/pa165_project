package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface CarDao {
    /**
     * Creates new Car object and insert it into database through entityManager
     * @param car Car object
     */
    void create(Car car);

    /**
     * Finds all cars in database
     * @return List of all cars
     */
    List<Car> findAll();
    /**
     * Finds Car entity by given ID
     * @param id ID of Car
     * @return instance found of Car
     */
    Car findById(Long id);
    /**
     * Remove Car from database
     * @param p Car object to be deleted
     */
    void remove(Car p);

    /**
     * Updates Car information
      * @param c Car to be updated
     */
    void update(Car c);


    // TODO JAVADOC
    Car findByLicencePlate(String licencePlate);
}
