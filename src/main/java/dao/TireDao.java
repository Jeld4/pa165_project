package dao;

import entity.Tire;

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