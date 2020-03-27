package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Tire;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TireDaoImplTest {
    @PersistenceContext
    public EntityManager em;

    @Autowired
    public TireDao tireDao;

    @BeforeMethod
    public void createTire(){
        Tire t1 = new Tire();
        Tire t2 = new Tire();

        t1.setManufacturer("Black");
        t2.setManufacturer("White");

        t1.setPrice(3500);
        t2.setPrice(3400);

        t1.setSeason("summer");
        t2.setSeason("winter");

        t1.setType("Big");
        t2.setType("Small");

        tireDao.create(t1);
        tireDao.create(t2);
    }

    @Test
    public void findAll(){
        List<Tire> found = tireDao.findAll();
        Assert.assertEquals(found.size(), 2);

    }
}
