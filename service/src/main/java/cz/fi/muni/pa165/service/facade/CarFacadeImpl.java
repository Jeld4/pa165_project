package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.facade.CarFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Radim Sasinka
 */
@Service
@Transactional
public class CarFacadeImpl implements CarFacade {

    @Inject
    private CarService carService;

    @Autowired
    private BeanMappingService beanMappingService;

    private final static Logger log = LoggerFactory.getLogger(CarFacadeImpl.class);


    @Override
    public Long createCar(CarCreateDTO car) {
        if(car == null){
            throw new IllegalArgumentException("Car cannot be null");
        }
        if(car.getLicencePlate().isEmpty()){
            throw new IllegalArgumentException("Car needs to have assigned licence plate.");
        }
        if(car.getModel().isEmpty()){
            throw new IllegalArgumentException("Car needs to have assigned model type");
        }
        log.debug("Facade - create car");
        Car newCar = new Car();
        newCar.setModel(car.getModel());
        newCar.setLicencePlate(car.getLicencePlate());
        carService.create(newCar);
        return  newCar.getId();

    }

    @Override
    public void deleteCar(Long carId)
    {
        if(carId == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.debug("Facade - deleting car with ID {}",carId);
        carService.remove(carService.findById(carId));
    }

    @Override
    public List<CarDTO> getAllCars() {

        log.debug("Facade - get all cars");
        return beanMappingService.mapTo(carService.findAll(), CarDTO.class);
    }

    @Override
    public CarDTO getCarWithId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Car id cannot be null");
        }
        log.debug("Facade - get Car with ID {}", id);
        Car foundCar = carService.findById(id);
        return (foundCar == null) ? null : beanMappingService.mapTo(foundCar, CarDTO.class);
    }

    @Override
    public CarDTO getCarWithLicencePlate(String licencePlate) {
        if(licencePlate.isEmpty()){
            throw new IllegalArgumentException("Licence plate cannot be empty.");
        }
        log.debug("Facade - get car with licence plate {}", licencePlate);
        Car foundCar = carService.findByLicencePlate(licencePlate);
        return (foundCar == null) ? null : beanMappingService.mapTo(foundCar, CarDTO.class);
    }

    @Override
    public void changeTireType(CarDTO car, TireDTO tire) {

        if(car == null){
            throw new IllegalArgumentException("Car cannot be null");
        }

        if(tire == null){
            throw new IllegalArgumentException("Car cannot be null");
        }

        log.debug("Facade - changing tires {} for car with ID {}", tire.getId(), car.getId());

        carService.changeTire(car.getId()
                , beanMappingService.mapTo(tire, Tire.class));

    }

    @Override
    public List<CarDTO> getCarsByUser(Long userId) {

        if (userId == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        log.debug("Facade - getting all car belonging to user with ID {}", userId);

        List<Car> cars = carService.getCarsByUser(userId);

        return beanMappingService.mapTo(cars, CarDTO.class);
    }
}
