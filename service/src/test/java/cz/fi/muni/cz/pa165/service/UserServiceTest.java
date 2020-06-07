package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dto.CarCreateDTO;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.CarService;
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

import java.util.ArrayList;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private CarService carService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private User user;

    private Car car;

    @BeforeMethod
    public void prepare(){
        user = new User();
        user.setLogin("admain");
        user.setPassword("aadmin");
        user.setName("name");
        user.setTelephone("555444666");
        user.setUserAddress("Brno");
        user.setCars(new ArrayList<>());

        car = new Car();
        car.setLicencePlate("1234");
        car.setModel("SUV");
        car.setTireType("winter");

    }

    @Test
    public void create() {
        userService.create(user);
        assert(userService.findAll().size() == 1);
    }

    @Test
    public void testPassword(){
        userService.create(user);
        assert(userService.checkPassword(user.getId(), "aadmin"));
        assert(!userService.checkPassword(user.getId(), "a"));
    }

    @Test
    public void cars(){
        userService.create(user);
        carService.create(car);
        userService.addCarToUser(user.getId(), car.getId());
        assert(user.getCars().size() == 1);
        userService.removeCarFromUser(user.getId(), car.getId());
        assert(user.getCars().isEmpty());
    }

    @Test
    public void remove(){
        userService.create(user);
        assert(userService.findAll().size() == 1);
        userService.remove(user);
        assert(userService.findAll().isEmpty());
    }

    @Test
    public void findByLogin(){
        userService.create(user);
        assert(userService.findByLogin("admain").equals(user));
    }


}
