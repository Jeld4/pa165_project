package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Car;

import java.util.List;

public interface CarFacade {

    Long createCar(CarCreateDTO car);
    void deleteCar(Long carId);
    List<CarDTO> getAllCars();
    CarDTO getCarWithId(Long id);
    CarDTO getCarWithLicencePlate(String licencePlate);
    void changeTireType(CarDTO car, TireDTO tire);
}
