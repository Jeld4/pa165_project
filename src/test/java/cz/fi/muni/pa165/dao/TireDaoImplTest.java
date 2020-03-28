package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
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

    private Tire t1;
    private Tire t2;

    @BeforeMethod
    public void prepare(){
        t1 = new Tire();
        t2 = new Tire();

        t1.setType("black");
        t2.setType("white");

        t1.setSeason("winter");
        t2.setSeason("summer");

        t1.setPrice(4500);
        t2.setPrice(4567);

        t1.setSize(340);
        t2.setSize(340);

        tireDao.create(t1);
        tireDao.create(t2);
    }

    @Test
    public void findAll(){
        List<Tire> found = tireDao.findAll();
        Assert.assertEquals(2, found.size());
    }

    @Test
    public void remove(){
        Assert.assertNotNull(tireDao.findById(t1.getId()));
        tireDao.remove(t1);
        org.testng.Assert.assertNull(tireDao.findById(t1.getId()));
        Assert.assertEquals(tireDao.findAll().size(), 1);
    }


}
