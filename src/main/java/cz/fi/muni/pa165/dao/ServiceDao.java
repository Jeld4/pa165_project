package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Service;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface ServiceDao {
    /**
     * Creates new service
     * @param s Service object
     */
    void create(Service s);

    /**
     * Find all services in database and returns them as a list
     * @return List of all services
     */
    List<Service> findAll();

    /**
     * Finds concrete Service by its ID
     * @param id ID of service
     * @return Service object
     */
    Service findById(Long id);

    /**
     * Removes given Service from database
     * @param s Service to be deleted
     */
    void remove(Service s);

    /**
     * Updates given Service in database
     * @param s Service to be updated
     */
    void update(Service s);
}
