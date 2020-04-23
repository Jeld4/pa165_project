package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.entity.Car;
import javax.inject.Inject;

import cz.fi.muni.pa165.entity.Tire;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Radim Sasinka. 456315
 */
@Service
public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;

    @Override
    public Car findById(Long id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findByLicencePlate(String licencePlate) {
        return carDao.findByLicencePlate(licencePlate);
    }

    @Override
    public void create(Car car) {
        carDao.create(car);
    }

    @Override
    public void remove(Car car) {
        carDao.remove(car);
    }

    @Override
    public void changeTire(Car car, Tire tire) {
        car.setTireType(tire.getType());
    }


}
