package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.service.CarService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CarServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CarDao carDao;

    @Autowired
    @InjectMocks
    private CarService carService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Car car01;
    private Car car02;

    @BeforeMethod
    public void init() {
        car01 = new Car();
        car01.setLicencePlate("1234");
        car01.setTireType("Winter");
        car01.setModel("truck");

        car02 = new Car();
        car02.setLicencePlate("56789");
        car02.setTireType("Summer");
        car02.setModel("SUV");
    }

    @AfterMethod
    void reset(){
        Mockito.reset(carDao);
    }

    @Test
    public void create() {
        carService.create(car01);
        carService.create(car02);
        //assert(carDao.findAll().size() == 2);
    }
/*
    @Test
    public void remove() {

    }

    @Test
    public void changeTire() {

    }

    @Test
    public void findByLicencePlate() {

    }

    @Test
    public void findAll() {

    }

    @Test
    public void findById() {

    }
 */
}
