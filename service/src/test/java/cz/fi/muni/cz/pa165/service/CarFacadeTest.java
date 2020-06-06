package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.facade.CarFacade;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.TireService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private CarFacade carFacade;

    @Autowired
    @InjectMocks
    private TireFacade tireFacade;

    @Mock
    private TireService tireService;

    @Mock
    private CarService carService;

    @Mock
    private BeanMappingService beanMappingService;

    private CarCreateDTO car01;
    private CarCreateDTO car02;
    private CarCreateDTO car03;

    @BeforeClass
    void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init() {
        car01 = new CarCreateDTO();
        car01.setLicencePlate("1234");
        car01.setModel("SUV");

        car02 = new CarCreateDTO();
        car02.setLicencePlate("5678");
        car02.setModel("cabriolet");

        car03 = new CarCreateDTO();
        car03.setLicencePlate("7890");
        car03.setModel("truck");
    }

    @AfterMethod
    void reset(){
        Mockito.reset(carService);
    }

    @Test
    public void create() {
        carFacade.createCar(car01);
        carFacade.createCar(car02);
        assert(carFacade.getAllCars().size() == 2);
    }

    @Test
    public void delete() {
        Long id1 = carFacade.createCar(car01);
        Long id2 = carFacade.createCar(car02);
        assert(carFacade.getAllCars().size() == 2);
        carFacade.deleteCar(id2);
        assert(carFacade.getAllCars().size() == 1);
        Assert.assertEquals(carFacade.getAllCars().get(0).getId(), id1);
        carFacade.deleteCar(id1);
        assert(carFacade.getAllCars().size() == 0);
    }

    @Test
    public void getAllCars() {
        carFacade.createCar(car01);
        carFacade.createCar(car02);
        List<CarDTO> result = carFacade.getAllCars();
        assert(result.size() == 2);
        assert(result.get(0).getLicencePlate().equals("1234"));
        assert(result.get(1).getLicencePlate().equals("5678"));
    }

    @Test
    public void getCarWithId() {
        Long id1 = carFacade.createCar(car01);
        Long id2 = carFacade.createCar(car02);
        assert(carFacade.getAllCars().size() == 2);
        Assert.assertEquals(carFacade.getCarWithId(id1).getLicencePlate(), car01.getLicencePlate());
        Assert.assertEquals(carFacade.getCarWithId(id2).getLicencePlate(), car02.getLicencePlate());
    }

    @Test
    public void getCarWithLicencePlate() {
        Long id1 = carFacade.createCar(car01);
        carFacade.createCar(car02);
        Long id3 = carFacade.createCar(car03);
        assert(carFacade.getAllCars().size() == 3);
        String car01LicencePlate = car01.getLicencePlate();
        Assert.assertEquals(carFacade.getCarWithLicencePlate(car01LicencePlate).getId(), id1);
        String car03LicencePlate = car03.getLicencePlate();
        Assert.assertEquals(carFacade.getCarWithLicencePlate(car03LicencePlate).getId(), id3);
    }

    @Test
    public void changeTireType() {
        Long id1 = carFacade.createCar(car01);
        assert(carFacade.getAllCars().size() == 1);
        Assert.assertEquals(carFacade.getCarWithId(id1).getTireType(), null);

        TireCreateDTO tire = new TireCreateDTO();
        tire.setManufacturer("Michelin");
        tire.setType("Winter");
        tire.setSeason("Spring");
        Long tireId = tireFacade.createTire(tire);

        carFacade.changeTireType(carFacade.getCarWithId(id1), tireFacade.getTireWithId(tireId));

        Assert.assertEquals(carFacade.getCarWithId(id1).getTireType(), tire.getType());
    }
}
