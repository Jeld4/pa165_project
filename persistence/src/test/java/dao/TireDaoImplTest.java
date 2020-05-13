package dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Tire;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TireDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public TireDao tireDao;

    @Autowired
    public CarDao carDao;

    private Tire tire1;
    private Tire tire2;

    @BeforeMethod
    public void prepare(){
        tire1 = new Tire();
        tire2 = new Tire();

        tire1.setType("black");
        tire2.setType("white");

        tire1.setSeason("winter");
        tire2.setSeason("summer");

        tire1.setPrice(4500);
        tire2.setPrice(4567);

        tire1.setSize(340);
        tire2.setSize(340);

        tireDao.create(tire1);
        tireDao.create(tire2);
    }

    @Test
    public void findAll(){
        List<Tire> found = tireDao.findAll();
        Assert.assertEquals(2, found.size());
    }

    @Test
    public void findByID() {
        Tire found = tireDao.findById(tire1.getId());
        Assert.assertEquals(found, tire1);
    }

    @Test
    public void remove(){
        Assert.assertNotNull(tireDao.findById(tire1.getId()));
        tireDao.remove(tire1);
        org.testng.Assert.assertNull(tireDao.findById(tire1.getId()));
        Assert.assertEquals(tireDao.findAll().size(), 1);
    }


}
