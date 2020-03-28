package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class CarDaoImplTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    public EntityManager em;

    @Autowired
    public CarDao carDao;

    private Car ferrari;
    private Car porsche;

    @Before
    public void createCar() {
        ferrari = new Car("4A2 3000", "Cabriolet");
        porsche = new Car("4B1 3244", "SUV");

        ferrari.setTireType("winter");
        porsche.setTireType("summer");

        carDao.create(ferrari);
        carDao.create(porsche);
    }

    @Test
    public void removeCarTest() {
        List<Car> carListBefore = carDao.findAll();
        carDao.remove(ferrari);
        List<Car> carListAfter = carDao.findAll();
        assert(carListAfter.size() == carListBefore.size() - 1);
        assert(carDao.findById(ferrari.getId()) == null);
    }

    @Test
    public void createCarTest() {
        List<Car> carListBefore = carDao.findAll();
        Car felicia = new Car("4C1 5887", "winter");
        carDao.create(felicia);
        List<Car> carListAfter = carDao.findAll();
        assert(carListAfter.size() == carListBefore.size() + 1);
        assert(carDao.findById(felicia.getId()) == felicia);
    }

    @Test
    public void updateCarTest() {
        String newSPZ = "XXX";
        ferrari.setLicencePlate(newSPZ);
        carDao.update(ferrari);
        Car updatedCar = carDao.findById(ferrari.getId());
        assert(updatedCar.getLicencePlate().equals(newSPZ));
    }

    @Test
    public void findCarTest() {
        assert(carDao.findById(ferrari.getId()) != null);
    }
}
