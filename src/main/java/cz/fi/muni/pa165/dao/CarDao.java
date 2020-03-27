package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface CarDao {

    void crate(Car car);

    List<Car> findAll();

    Car findById(Long id);

    void remove(Car p);
}
