package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.service.ServiceService;
import cz.fi.muni.pa165.service.TireService;
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

/**
 * @author Jakub Malý, 456389
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private ServiceService serviceService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Service service1;
    private Service service2;


    @BeforeMethod
    public void prepareService(){
        service1 = new Service();
        service1.setName("Super Swift");
        service1.setDescription("Barum");
        service1.setPrice(new BigDecimal(6500));

        service2 = new Service();
        service2.setName("service2");
        service2.setPrice(new BigDecimal(150));
        service2.setDescription("really good");
    }

    @Test
    public void create(){
        serviceService.create(service1);
        serviceService.create(service2);
        assert(serviceService.findAll().size() == 2);
    }

    @Test
    public void findByID(){
        serviceService.create(service1);
        serviceService.create(service2);
        assert(serviceService.findById(service1.getId()).equals(service1));
        assert(serviceService.findById(service2.getId()).equals(service2));
    }

    @Test
    public void findAll(){
        serviceService.create(service1);
        assert(serviceService.findAll().size() == 1);
        serviceService.create(service2);
        assert(serviceService.findAll().size() == 2);
    }

    @Test
    public void remove(){
        serviceService.create(service1);
        serviceService.create(service2);
        serviceService.remove(service1);
        assert(serviceService.findAll().contains(service2));
    }

}
