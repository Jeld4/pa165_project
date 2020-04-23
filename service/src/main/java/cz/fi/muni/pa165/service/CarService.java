package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Tire;

import java.util.List;

/**
 * @author Radim Sasinka. 456315
 */

public interface CarService {
    Car findById(Long id);
    List<Car> findAll();
    Car findByLicencePlate(String licencePlate);
    void create(Car car);
    void remove(Car car);
    void changeTire(Car car, Tire tire);
}
