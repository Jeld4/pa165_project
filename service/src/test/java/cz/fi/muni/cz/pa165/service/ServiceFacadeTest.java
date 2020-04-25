package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;
import cz.fi.muni.pa165.facade.ServiceFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.ServiceService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.math.BigDecimal;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private ServiceFacade serviceFacade;

    @Mock
    private ServiceService serviceService;

    @Mock
    private BeanMappingService beanMappingService;


    private ServiceCreateDTO service1;
    private ServiceCreateDTO service2;
    private ServiceCreateDTO service3;

    @BeforeClass
    void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init(){
        service1 = new ServiceCreateDTO();
        service1.setName("service1");
        service1.setDescription("description1");
        service1.setPrice(new BigDecimal("10000"));


        service2 = new ServiceCreateDTO();
        service2.setName("service2");
        service2.setDescription("description2");
        service2.setPrice(new BigDecimal("20000"));

        service3 = new ServiceCreateDTO();
        service3.setName("service3");
        service3.setDescription("description3");
        service3.setPrice(new BigDecimal("30000"));
    }

    @AfterMethod
    void reset(){
        Mockito.reset(serviceService);
    }

    @Test
    public void create(){
        serviceFacade.crateService(service1);
        serviceFacade.crateService(service2);
        assert(serviceFacade.getAllServices().size() == 2);
    }

    @Test
    public void delete(){
        Long id1 = serviceFacade.crateService(service1);
        Long id2 = serviceFacade.crateService(service2);
        assert(serviceFacade.getAllServices().size() == 2);
        serviceFacade.deleteService(id1);
        assert(serviceFacade.getAllServices().size() == 1);
        Assert.assertEquals(serviceFacade.getAllServices().get(0).getName(), service2.getName());
        serviceFacade.deleteService(id2);
        Assert.assertTrue(serviceFacade.getAllServices().isEmpty());
    }

    @Test
    public void getServiceWithId(){
        /*serviceFacade.crateService(service1);
        serviceFacade.crateService(service2);
        serviceFacade.crateService(service3);
        ServiceDTO service = serviceFacade.getServiceWithId(service3.getId());
        Assert.assertEquals(service.getName(), service3.getName());
        */
        //TODO
    }



}
