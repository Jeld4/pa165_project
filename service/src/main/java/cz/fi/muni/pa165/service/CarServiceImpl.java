package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
/**
 * @author Radim Sasinka. 456315
 */
@Service
public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;

    @Inject
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(CarServiceImpl.class);


    @Override
    public Car findById(Long id) {
        if (id== null){
            throw new IllegalArgumentException("ID of car cannot be null");
        }
        Car car = null;
        try {
            car = carDao.findById(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
        log.debug("Service - find car with ID {}", id);
        return car;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = null;
        try {
            cars = carDao.findAll();
        } catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("Service - find all cars");
        return cars;
    }

    @Override
    public Car findByLicencePlate(String licencePlate) {
        if(licencePlate.isEmpty()){
            throw new IllegalArgumentException("Licence plate cannot be empty");
        }
        Car car = null;
        try {
            car = carDao.findByLicencePlate(licencePlate);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
        log.debug("Service - find car with licence plate {}", licencePlate);
        return car;
    }

    @Override
    public void create(Car car) {
        if (car == null){
            throw new IllegalArgumentException("Car object cannot be null");
        }
        try {
            log.debug("Service - Create car");
            carDao.create(car);
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Car car) {
        if (car == null){
            throw new IllegalArgumentException("Car object cannot be null");
        }
        try {
            log.debug("Service - Remove car");
            carDao.remove(car);
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changeTire(Long carId, Tire tire) {
        if(carId == null){
            throw new IllegalArgumentException("Car ID cannot be null");
        }
        if(tire == null){
            throw new IllegalArgumentException("Tire cannot be null");
        }
        Car car = this.findById(carId);
        log.debug("Service - Change tires with ID {} for car with ID {}", tire.getId(), carId);
        car.setTireType(tire.getType());
    }

    @Override
    public List<Car> getCarsByUser(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        log.debug("Service - get cars belonging to user with ID {}", userId);
        User user = userDao.getUserById(userId);
        return user.getCars();
    }
}
