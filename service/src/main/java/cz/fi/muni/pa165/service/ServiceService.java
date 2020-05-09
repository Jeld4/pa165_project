package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Service;

import java.util.List;
/**
 * @author Radim Sasinka. 456315
 */

public interface ServiceService {
    Service findById(Long id);
    List<Service> findAll();
    void create(Service service);
    void remove(Service service);
}
