package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Service;

import java.util.List;
/**
 * @author Radim Sasinka. 456315
 */

public interface ServiceService {
    /**
     * find service by id
     * @param id
     * @return service
     */
    Service findById(Long id);

    /**
     * fin all services
     * @return all services
     */
    List<Service> findAll();

    /**
     * create service
     * @param service
     */
    void create(Service service);

    /**
     * remove service
     * @param service
     */
    void remove(Service service);
}
