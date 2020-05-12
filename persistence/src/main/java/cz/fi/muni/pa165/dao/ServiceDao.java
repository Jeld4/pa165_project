package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Service;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface ServiceDao {
    /**
     * Creates new service
     * @param service Service object
     */
    void create(Service service);

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
     * @param service Service to be deleted
     */
    void remove(Service service);

    /**
     * Updates given Service in database
     * @param service Service to be updated
     */
    void update(Service service);
}
