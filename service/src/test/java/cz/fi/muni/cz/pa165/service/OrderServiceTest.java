package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.OrderService;
import cz.fi.muni.pa165.service.TireService;
import cz.fi.muni.pa165.service.UserService;
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

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private OrderDao orderDao;

    @Autowired
    @InjectMocks
    private OrderService orderService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Order order;

    @BeforeMethod
    public void prepareTire(){
        order = new Order();
    }

    @Test
    public void create() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("name");
        userService.create(user);

        assert(userService.findAll().size() == 1);

        orderService.create(order, user.getLogin());
        int size = orderService.findAll().size();

        //assert(orderService.findAll().size() == 1);
    }
}
