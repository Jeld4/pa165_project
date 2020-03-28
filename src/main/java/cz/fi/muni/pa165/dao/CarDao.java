package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface CarDao {

    void create(Car car);

    List<Car> findAll();

    Car findById(Long id);

    void remove(Car car);

    void update(Car car);
}
