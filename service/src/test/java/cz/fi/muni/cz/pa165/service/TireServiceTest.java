package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.entity.Tire;
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
 * @author Radim Sasinka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private TireService tireService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Tire tire1;
    private Tire tire2;


    @BeforeMethod
    public void prepareTire(){
        tire1 = new Tire();
        tire1.setType("Super Swift");
        tire1.setManufacturer("Barum");
        tire1.setPrice(new BigDecimal(6500));
        tire1.setSeason("Summer");
        tire1.setSize(BigDecimal.valueOf(16));


        tire2 = new Tire();
        tire2.setType("Super Slow");
        tire2.setManufacturer("Micheel");
        tire2.setPrice(new BigDecimal(4300));
        tire2.setSeason("All");
        tire2.setSize(BigDecimal.valueOf(12));
    }

    @Test
    public void create(){
        tireService.create(tire1);
        tireService.create(tire2);
        assert(tireService.findAll().size() == 2);
    }

    @Test
    public void findByID(){
        tireService.create(tire1);
        tireService.create(tire2);
        assert(tireService.findById(tire1.getId()).equals(tire1));
        assert(tireService.findById(tire2.getId()).equals(tire2));
    }

    @Test
    public void findAll(){
        tireService.create(tire1);
        assert(tireService.findAll().size() == 1);
        tireService.create(tire2);
        assert(tireService.findAll().size() == 2);
    }

    @Test
    public void findByManufacturer(){
        tireService.create(tire1);
        tireService.create(tire2);
        assert(tireService.findByManufacturer("Barum").get(0).equals(tire1));
    }

    @Test
    public void remove(){
        tireService.create(tire1);
        tireService.create(tire2);
        tireService.remove(tire1);
        assert(tireService.findAll().contains(tire2));
    }

}
