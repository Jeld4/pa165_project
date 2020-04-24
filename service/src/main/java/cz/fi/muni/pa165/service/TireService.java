package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Tire;

import java.util.List;
/**
 * @author Jakub Malý, 456389
 */

public interface TireService {
    Tire findById(Long id);
    Tire findByManufacturer(String manufacturer);
    List<Tire> findAll();
    void create(Tire tire);
    void remove(Tire tire);
}
