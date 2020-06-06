package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.TireService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Radim Sasinka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireFacadeTest extends AbstractTransactionalTestNGSpringContextTests {


    @Autowired
    @InjectMocks
    private TireFacade tireFacade;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private TireService tireService;



    private Tire tire;

    private TireCreateDTO tireCreateDTO;
    private TireCreateDTO tireCreateDTO2;
    private TireCreateDTO tireCreateDTO3;
    private TireCreateDTO tireCreateDTO4;



    @BeforeClass
    void initMocks(){
        MockitoAnnotations.initMocks(this);
    }


    @BeforeMethod
    void init(){
        tireCreateDTO = new TireCreateDTO();
        tireCreateDTO.setManufacturer("Michellin");
        tireCreateDTO.setPrice(new BigDecimal(7500));
        tireCreateDTO.setType("SuperBlack");
        tireCreateDTO.setSeason("winter");

        tireCreateDTO3 = new TireCreateDTO();
        tireCreateDTO3.setManufacturer("Michellin");
        tireCreateDTO3.setPrice(new BigDecimal(7500));
        tireCreateDTO3.setType("SuperDuper");
        tireCreateDTO3.setSeason("winter");

        tireCreateDTO4 = new TireCreateDTO();
        tireCreateDTO4.setManufacturer("Michellin");
        tireCreateDTO4.setPrice(new BigDecimal(7500));
        tireCreateDTO4.setType("SuperTrooper");
        tireCreateDTO4.setSeason("winter");

        tireCreateDTO2 = new TireCreateDTO();
        tireCreateDTO2.setManufacturer("Barumm");
        tireCreateDTO2.setPrice(new BigDecimal(7500));
        tireCreateDTO2.setType("White");
        tireCreateDTO2.setSeason("winter");

        tire = new Tire();
        tire.setManufacturer("Michellin");
        tire.setPrice(new BigDecimal(7500));
        tire.setType("SuperBlack");
        tire.setSeason("winter");
    }

    @AfterMethod
    void reset(){
        Mockito.reset(tireService);
    }


    @Test
    public void create(){
        tireFacade.createTire(tireCreateDTO);
        assert(tireFacade.getAllTires().size() == 1);
    }

    @Test
    public void remove(){
        Long id1 = tireFacade.createTire(tireCreateDTO);
        Long id2 = tireFacade.createTire(tireCreateDTO2);
        assert(tireFacade.getAllTires().size() == 2);
        tireFacade.deleteTire(id1);
        assert(tireFacade.getAllTires().size() == 1);
        assert(tireFacade.getTireWithId(id2).getType().equals("White"));
    }

    @Test
    public void getAll() {
        tireFacade.createTire(tireCreateDTO);
        tireFacade.createTire(tireCreateDTO2);
        List<TireDTO> result = tireFacade.getAllTires();
        assert(result.size() == 2);
        assert(result.get(1).getType().equals("White"));
        assert(result.get(0).getManufacturer().equals("Michellin"));
    }

    @Test
    public void getTireWithManufacturer(){
        tireFacade.createTire(tireCreateDTO);
        tireFacade.createTire(tireCreateDTO2);
        tireFacade.createTire(tireCreateDTO3);
        tireFacade.createTire(tireCreateDTO4);

        List<TireDTO> result = tireFacade.getTiresByManufacturer("Michellin");
        assert(result.size() == 3);
        assert(result.get(0).getType().equals("SuperBlack"));
    }






}


