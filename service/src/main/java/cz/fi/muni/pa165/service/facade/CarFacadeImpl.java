package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.facade.CarFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
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

    @Override
    public Long createCar(CarCreateDTO car) {
        if(car == null){
            throw new IllegalArgumentException("Car cannot be null");
        }
        if(car.getLicencePlate().isEmpty()){
            throw new IllegalArgumentException("Car needs to have assigned licence plate.");
        }
        if(car.getModel().isEmpty()){
            throw new IllegalArgumentException("Carr needs to have assigned model type");
        }
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
        carService.remove(carService.findById(carId));
    }

    @Override
    public List<CarDTO> getAllCars() {
        return beanMappingService.mapTo(carService.findAll(), CarDTO.class);
    }

    @Override
    public CarDTO getCarWithId(Long id) {
        Car foundCar = carService.findById(id);
        return (foundCar == null) ? null : beanMappingService.mapTo(foundCar, CarDTO.class);
    }

    @Override
    public CarDTO getCarWithLicencePlate(String licencePlate) {
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

        carService.changeTire(car.getId()
                , beanMappingService.mapTo(tire, Tire.class));

    }
}
