package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.TireService;
import cz.fi.muni.pa165.service.UserService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private CarService carService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private TireService tireService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Car car1;
    private Car car2;
    private Car car3;

    private User user;

    private Tire tire;

    @BeforeMethod
    public void init() {
        car1 = new Car();
        car1.setLicencePlate("0123-5897");
        car1.setTireType("terrain");
        car1.setModel("SUV");

        carService.create(car1);

        car2 = new Car();
        car2.setLicencePlate("5978-1587");
        car2.setTireType("offroad");
        car2.setModel("cabriolet");

        car3 = new Car();
        car3.setLicencePlate("7891-ACDV");
        car3.setTireType("winter");
        car3.setModel("Truck");

        user = new User();
        user.setUserAddress("Brno");
        user.setTelephone("555444888");
        user.setPassword("password");
        user.setLogin("login");
        user.setName("useros");

        tire = new Tire();
        tire.setPrice(new BigDecimal(50));
        tire.setManufacturer("Michelin");
        tire.setSeason("winter");
        tire.setType("winter");
        tire.setSize(new BigDecimal(800));

        tireService.create(tire);
    }

    @Test
    public void create() {

        carService.create(car2);
        carService.create(car3);

        int size = carService.findAll().size();

        assert(size == 3);
    }

    @Test
    public void remove() {
        assert(carService.findAll().size() == 1);

        carService.create(car2);

        assert(carService.findAll().size() == 2);

        carService.remove(car1);

        assert(carService.findAll().size() == 1);
    }

    @Test
    public void findById() {
        carService.create(car2);
        carService.create(car3);

        assert(carService.findAll().size() == 3);
        assert(carService.findById(car2.getId()) == car2);
    }

    @Test
    public void findAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        carService.create(car2);
        carService.create(car3);

        assert(carService.findAll().size() == 3);
        assert(carService.findAll().containsAll(cars));
    }

    @Test
    public void findByLicencePlate() {
        carService.create(car2);

        assert(carService.findAll().size() == 2);
        assert(carService.findByLicencePlate(car2.getLicencePlate()) == car2);
    }

    @Test
    public void changeTire() {
        carService.create(car2);
        carService.create(car3);

        assert(carService.findById(car2.getId()).getTireType() == "offroad");

        carService.changeTire(car2.getId(), tire);

        assert(carService.findById(car2.getId()).getTireType() == tire.getType());
    }

    @Test
    public void getCarsByUser() {
        carService.create(car2);

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        user.setCars(cars);
        userService.create(user);

        assert(carService.getCarsByUser(user.getId()).containsAll(cars));
        assert(carService.getCarsByUser(user.getId()).size() == 2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullCar() {
        carService.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullCar() {
        carService.remove(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId() {
        carService.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullLicencePlate() {
        carService.findByLicencePlate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getCarsByNullUser() {
        carService.getCarsByUser(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void changeTireWithNullValues() {
        carService.changeTire(null, null);
        carService.changeTire(car1.getId(), null);
        carService.changeTire(null, tire);
    }
}
