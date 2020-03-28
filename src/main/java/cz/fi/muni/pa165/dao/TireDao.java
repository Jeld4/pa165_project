package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Tire;

import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
public interface TireDao {

    void create(Tire t);

    List<Tire> findAll();

    Tire findById(Long id);

    void remove(Tire t);
}