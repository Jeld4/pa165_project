package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Tire;

import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
public interface TireDao {

    /**
     * Method used to create a new tire
     * @param tire
     */
    void create(Tire tire);

    /**
     * Method used to find all tires
     * @return
     */
    List<Tire> findAll();

    /**
     * Method used to find tire by id
     * @param id
     * @return
     */
    Tire findById(Long id);

    /**
     * Method used to find tire by manufacturer
     * @param manufacturer
     * @return
     */
    List<Tire> findByManufacturer(String manufacturer);

    /**
     * Method used to remove order.
     * @param tire
     */
    void remove(Tire tire);

    /**
     * Method used to update existing tire
     * @param tire
     */
    void update(Tire tire);
}