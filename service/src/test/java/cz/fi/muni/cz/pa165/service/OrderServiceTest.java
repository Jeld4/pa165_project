package cz.fi.muni.cz.pa165.service;

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
    private Order order2;
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

        order.setServices(services);
        order.setTires(tires);

        order2 = new Order();
        order2.setTires(tires);
    }

    @Test
    public void create() {
        assert(userService.findAll().size() == 1);

        orderService.create(order, user.getLogin());

        int size = orderService.findAll().size();

        assert(size == 1);
    }

    @Test
    public void remove() {
        assert(orderService.findAll().size() == 0);

        assert(userService.findAll().size() == 1);

        orderService.create(order, user.getLogin());

        assert(orderService.findAll().size() == 1);

        orderService.remove(order);

        assert(orderService.findAll().size() == 0);
    }

    @Test
    public void findById() {
        orderService.create(order, user.getLogin());
        orderService.create(order2, user.getLogin());

        assert(orderService.findAll().size() == 2);
        assert(orderService.findById(order2.getId()) == order2);
    }

    @Test
    public void findAll() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);

        orderService.create(order, user.getLogin());
        orderService.create(order2, user.getLogin());

        assert(orderService.findAll().size() == 2);
        assert(orderService.findAll().containsAll(orders));
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

    @Test
    public void confirm() {
        orderService.create(order, user.getLogin());
        assert(orderService.findById(order.getId()).getState() == OrderState.PENDING);

        orderService.confirm(orderService.findById(order.getId()));
        assert(orderService.findById(order.getId()).getState() == OrderState.CONFIRMED);
    }

    @Test
    public void cancel() {
        orderService.create(order, user.getLogin());
        assert(orderService.findById(order.getId()).getState() == OrderState.PENDING);

        orderService.cancel(orderService.findById(order.getId()));
        assert(orderService.findById(order.getId()).getState() == OrderState.CANCELED);
    }

    @Test
    public void finish() {
        orderService.create(order2, user.getLogin());
        assert(orderService.findById(order2.getId()).getState() == OrderState.PENDING);

        orderService.finish(orderService.findById(order2.getId()));
        assert(orderService.findById(order2.getId()).getState() == OrderState.DONE);
    }

    @Test
    public void addTireToOrder() {
        orderService.create(order, user.getLogin());

        assert(orderService.findById(order.getId()).getTires().size() == 1);

        Long orderId = orderService.findById(order.getId()).getId();
        orderService.addTireToOrder(orderId, tire.getId());

        assert(orderService.findById(order.getId()).getTires().size() == 2);
    }

    @Test
    public void addServiceToOrder() {
        orderService.create(order, user.getLogin());

        assert(orderService.findById(order.getId()).getServices().size() == 1);

        Long orderId = orderService.findById(order.getId()).getId();
        orderService.addServiceToOrder(orderId, service.getId());

        assert(orderService.findById(order.getId()).getServices().size() == 2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullOrder() {
        orderService.create(null, user.getLogin());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createOrderWithNoUserId() {
        orderService.create(order, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullOrder() {
        orderService.remove(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findOrderWithNullId() {
        orderService.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getOrdersByNullUser() {
        orderService.getOrdersByUser(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void confirmNullOrder() {
        orderService.confirm(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cancelNullOrder() {
        orderService.cancel(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void finishNullOrder() {
        orderService.finish(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addTireToOrderWithNullIds() {
        orderService.create(order, user.getLogin());
        orderService.addTireToOrder(null, null);
        orderService.addTireToOrder(order.getId(), null);
        orderService.addTireToOrder(null, tire.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addServiceToOrderWithNullIds() {
        orderService.create(order, user.getLogin());
        orderService.addServiceToOrder(null, null);
        orderService.addServiceToOrder(order.getId(), null);
        orderService.addServiceToOrder(null, service.getId());
    }
}
