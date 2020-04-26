package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dao.ServiceDao;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.service.ServiceService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private ServiceDao serviceDao;

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
    public void prepareServices(){
        service1 = new Service();
        service1.setName("service1");
        service1.setDescription("description1");
        service1.setPrice(new BigDecimal("10000"));

        service2 = new Service();
        service2.setName("service2");
        service2.setDescription("description2");
        service2.setPrice(new BigDecimal("20000"));
    }

    @Test
    public void create() {
        serviceService.create(service1);
        serviceService.create(service2);
        assert(serviceService.findAll().size() == 0);
        //TODO: rest of the tests, this one should verify that there are 2 services
    }
}
