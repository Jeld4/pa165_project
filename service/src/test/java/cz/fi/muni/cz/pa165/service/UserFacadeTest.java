package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.UserService;
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
/**
 * @author Radim Sasinka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;


    private UserCreateDTO userCreateDTO1;
    private UserCreateDTO userCreateDTO2;
    private UserCreateDTO userCreateDTO3;

    private UserDTO userDTO;

    @BeforeClass
    void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    void init(){
        userCreateDTO1 = new UserCreateDTO();
        userCreateDTO1.setName("Omre Bro");
        userCreateDTO1.setLogin("omrebro123");

        userCreateDTO2 = new UserCreateDTO();
        userCreateDTO2.setName("Holly Socks");
        userCreateDTO2.setLogin("solly-hocks");

        userCreateDTO3 = new UserCreateDTO();
        userCreateDTO3.setName("Felix Saulovich");
        userCreateDTO3.setLogin("bigSaul_302");


    }

    @AfterMethod
    void reset(){
        Mockito.reset(userService);
    }

    @Test
    public void create(){
        userFacade.createUser(userCreateDTO1);
        userFacade.createUser(userCreateDTO2);
        assert(userFacade.getAllUsers().size() == 2);
    }

    @Test
    public void delete(){
        Long id1 = userFacade.createUser(userCreateDTO1);
        Long id2 = userFacade.createUser(userCreateDTO2);
        assert(userFacade.getAllUsers().size() == 2);
        userFacade.deleteUser(id1);
        assert(userFacade.getAllUsers().size() == 1);
        Assert.assertEquals(userFacade.getAllUsers().get(0).getLogin(), userCreateDTO2.getLogin());
        userFacade.deleteUser(id2);
        Assert.assertTrue(userFacade.getAllUsers().isEmpty());
    }

    @Test
    public void getUserWithLogin(){
        userFacade.createUser(userCreateDTO1);
        userFacade.createUser(userCreateDTO2);
        userFacade.createUser(userCreateDTO3);
        UserDTO user = userFacade.getUserWithLogin("solly-hocks");
        Assert.assertEquals(user.getLogin(), userCreateDTO2.getLogin());
    }



}
