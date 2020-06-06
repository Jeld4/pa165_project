package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.UserService;
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

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private UserService orderService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Order order;
    private User user;

    @BeforeMethod
    public void prepare(){
        user = new User();
        user.setLogin("admain");
        user.setPassword("aadmin");
        user.setName("name");
        user.setTelephone("555444666");
        user.setUserAddress("Brno");
    }

    @Test
    public void create() {
        userService.create(user);

        assert(userService.findAll().size() == 1);

    }

}
