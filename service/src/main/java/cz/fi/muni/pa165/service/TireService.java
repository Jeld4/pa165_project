package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Tire;

import java.util.List;
/**
 * @author Jakub Malý, 456389
 */

public interface TireService {

    /**
     * Returns a tire by its id
     * @param id of tire
     * @return found tire
     */
    Tire findById(Long id);

    /**
     * Returns list of tires manufactured by specific manufacturer
     * @param name of the manufacturer
     * @return list of tires
     */
    List<Tire> findByManufacturer(String manufacturer);

    /**
     * @return list of all tires
     */
    List<Tire> findAll();

    /**
     * creates tire in the system
     * @param Tire object to be created
     */
    void create(Tire tire);

    /**
     * removes tire from the system
     * @param Tire object to be removed
     */
    void remove(Tire tire);
}
