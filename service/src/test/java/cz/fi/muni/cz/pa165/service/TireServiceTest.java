package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.service.TireService;
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

import java.math.BigDecimal;

/**
 * @author Radim Sasinka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private TireDao tireDao;

    @Autowired
    @InjectMocks
    private TireService tireService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Tire tire;

    @BeforeMethod
    public void prepareTire(){
        tire = new Tire();
        tire.setType("Super Swift");
        tire.setManufacturer("Barum");
        tire.setPrice(new BigDecimal(6500));
        tire.setSeason("Summer");
        tire.setSize(BigDecimal.valueOf(16));
    }


}
