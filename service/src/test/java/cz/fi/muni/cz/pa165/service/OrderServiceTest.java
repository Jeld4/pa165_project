package cz.fi.muni.cz.pa165.service;

import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
import cz.fi.muni.pa165.service.OrderService;
import cz.fi.muni.pa165.service.ServiceService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private OrderService orderService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @InjectMocks
    private TireService tireService;

    @Autowired
    @InjectMocks
    private ServiceService serviceService;

    @BeforeClass
    private void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Order order;
    private User user;
    private Tire tire;
    private Service service;
    private List<Service> services;
    private List<Tire> tires;

    @BeforeMethod
    public void prepareTire(){
        order = new Order();
        user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("name");
        userService.create(user);

        tire = new Tire();
        tire.setType("eee");
        tire.setSize(BigDecimal.valueOf(50));
        tire.setPrice(new BigDecimal(80));
        tire.setSeason("snow");
        tire.setManufacturer("Michelin");

        service = new Service();
        service.setName("wash");
        service.setDescription("wash car");
        service.setPrice(new BigDecimal(50));

        tireService.create(tire);
        serviceService.create(service);

        tires = new ArrayList<>();
        tires.add(tire);
        services = new ArrayList<>();
        services.add(service);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void create() {
        assert(userService.findAll().size() == 1);
        //order.setServices(services);
        //order.setTires(tires);
        orderService.create(order, user.getLogin());

        int size = orderService.findAll().size();

        //assert(orderService.findAll().size() == 1);
    }

    @Test
    public void getOrdersByUser() {
        Order order = new Order();
        order.setState(OrderState.PENDING);
        order.setTotalPrice(new BigDecimal(50));
        order.setServices(services);
        order.setTires(tires);

        orderService.create(order, user.getLogin());

        assert(orderService.getOrdersByUser(user).size() == 1);
        assert(orderService.getOrdersByUser(user).get(0) == order);
    }
}
