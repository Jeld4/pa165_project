package dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.ServiceDao;
import cz.fi.muni.pa165.entity.Service;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public ServiceDao serviceDao;

    private Service service1;
    private Service service2;

    @BeforeMethod
    public void createInitialServices(){
        service1 = new Service();
        service2 = new Service();

        service1.setName("Engine oil renewal");
        service2.setName("Brake fluid renewal");

        service1.setDescription("Exactly what <name> says1");
        service2.setDescription("Exactly what <name> says2");

        service1.setPrice(new BigDecimal("1250"));
        service2.setPrice(new BigDecimal("1550"));

        serviceDao.create(service1);
        serviceDao.create(service2);
    }

    @Test
    public void findAll(){
        List<Service> all = serviceDao.findAll();
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void findByID(){
        assert(serviceDao.findById(service1.getId()) == service1);
        assert(serviceDao.findById(service2.getId()) != null);
    }

    @Test
    public void createService() {
        Service service = new Service();
        service.setName("Steering wheel replacement");
        service.setDescription("Replace steering wheel");
        service.setPrice(new BigDecimal("5000"));
        serviceDao.create(service);
        List<Service> all = serviceDao.findAll();
        assert(all.size() == 3);
        assert(serviceDao.findById(service.getId()) == service);
    }

    @Test
    public void removeService() {
        serviceDao.remove(service2);
        List<Service> all = serviceDao.findAll();
        assert(all.size() == 1);
        assert(serviceDao.findById(service2.getId()) == null);
    }

    @Test
    public void updateService() {
        assert(service1.getName() == "Engine oil renewal");
        String newName = "New name";
        service1.setName(newName);
        serviceDao.update(service1);
        Service service = serviceDao.findById(service1.getId());
        assert(service.getName().equals(newName));
    }
}





















