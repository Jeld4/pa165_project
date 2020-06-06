package cz.fi.muni.cz.pa165.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.fi.muni.pa165.dto.*;
import cz.fi.muni.pa165.facade.*;
import cz.fi.muni.pa165.service.*;
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

import cz.fi.muni.pa165.enums.OrderState;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private OrderFacade orderFacade;

    @Autowired
    @InjectMocks
    private UserFacade userFacade;

    @Autowired
    @InjectMocks
    private ServiceFacade serviceFacade;

    @Autowired
    @InjectMocks
    private TireFacade tireFacade;

    @Autowired
    @InjectMocks
    private CarFacade carFacade;

    @Mock
    private OrderService orderService;

    @Mock
    private UserService userService;

    @Mock
    private ServiceService serviceService;

    @Mock
    private TireService tireService;

    @Mock
    private CarService carService;

    @Mock
    private BeanMappingService beanMappingService;

    private OrderCreateDTO order1;
    private OrderCreateDTO order2;
    private OrderCreateDTO order3;

    private UserCreateDTO user1;

    private TireCreateDTO tire;
    private ServiceCreateDTO service;

    private List<TireDTO> tires;
    private List<ServiceDTO> services;

    private CarCreateDTO car1;

    @BeforeClass
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private long userId;

    @BeforeMethod
    void init() {
        order1 = new OrderCreateDTO();
        order1.setDateOfOrder(new Date());
        order1.setServices(new ArrayList<>());
        order1.setState(OrderState.PENDING);
        order1.setTires(new ArrayList<>());

        order2 = new OrderCreateDTO();
        order2.setDateOfOrder(new Date());
        order2.setServices(new ArrayList<>());
        order2.setState(OrderState.PENDING);
        order2.setTires(new ArrayList<>());

        order3 = new OrderCreateDTO();
        order3.setDateOfOrder(new Date());
        order3.setServices(new ArrayList<>());
        order3.setState(OrderState.PENDING);
        order3.setTires(new ArrayList<>());

        user1 = new UserCreateDTO();
        user1.setLogin("TestSubject");
        user1.setPassword("Number62");
        user1.setIsAdmin(false);
        user1.setName("John");

        userId = userFacade.createUser(user1);

        order1.setUser(userFacade.getUserWithId(userId));
        order2.setUser(userFacade.getUserWithId(userId));
        order3.setUser(userFacade.getUserWithId(userId));

        car1 = new CarCreateDTO();
        car1.setModel("aaa");
        car1.setLicencePlate("asdasd");
        carFacade.createCar(car1);

        tire = new TireCreateDTO();
        tire.setPrice(new BigDecimal(50));
        tire.setType("snow");
        tire.setManufacturer("Michelin");
        Long tireId = tireFacade.createTire(tire);
        tires = new ArrayList<>();
        tires.add(tireFacade.getTireWithId(tireId));

        TireCreateDTO tire2 = new TireCreateDTO();
        tire2.setPrice(new BigDecimal(50));
        tire2.setType("snow");
        tire2.setManufacturer("Michelin");
        tireId = tireFacade.createTire(tire2);
        List<TireDTO> tires2 = new ArrayList<>();
        tires2.add(tireFacade.getTireWithId(tireId));

        TireCreateDTO tire3 = new TireCreateDTO();
        tire3.setPrice(new BigDecimal(50));
        tire3.setType("snow");
        tire3.setManufacturer("Michelin");
        tireId = tireFacade.createTire(tire3);
        List<TireDTO> tires3 = new ArrayList<>();
        tires3.add(tireFacade.getTireWithId(tireId));


        service = new ServiceCreateDTO();
        service.setDescription("service to wash car");
        service.setName("washing car");
        service.setPrice(new BigDecimal(80));
        Long serviceId = serviceFacade.createService(service);
        services = new ArrayList<>();
        services.add(serviceFacade.getServiceWithId(serviceId));

        ServiceCreateDTO service2 = new ServiceCreateDTO();
        service2.setDescription("service to wash car");
        service2.setName("washing car");
        service2.setPrice(new BigDecimal(80));
        serviceId = serviceFacade.createService(service2);
        List<ServiceDTO> services2 = new ArrayList<>();
        services2.add(serviceFacade.getServiceWithId(serviceId));

        ServiceCreateDTO service3 = new ServiceCreateDTO();
        service3.setDescription("service to wash car");
        service3.setName("washing car");
        service3.setPrice(new BigDecimal(80));
        serviceId = serviceFacade.createService(service3);
        List<ServiceDTO> services3 = new ArrayList<>();
        services3.add(serviceFacade.getServiceWithId(serviceId));

        order1.setTires(tires);
        order1.setServices(services);
        order2.setTires(tires2);
        order2.setServices(services2);
        order3.setTires(tires3);
        order3.setServices(services3);
    }

    @AfterMethod
    void reset() {
        Mockito.reset(orderService);
    }

    @Test
    public void getOrderCar() {
        order1.setCar(carFacade.getAllCars().get(0));
        assert(order1.getCar() != null);
    }

    @Test
    public void createOrderAndGetAllOrders() {
        orderFacade.createOrder(order1, user1.getLogin());
        orderFacade.createOrder(order2, user1.getLogin());
        assert (orderFacade.getAllOrders().size() == 2);
    }

    @Test
    public void removeOrder() {
        Long id1 = orderFacade.createOrder(order1, user1.getLogin());
        Long id2 = orderFacade.createOrder(order2, user1.getLogin());
        Long id3 = orderFacade.createOrder(order3, user1.getLogin());

        assert (orderFacade.getAllOrders().size() == 3);

        orderFacade.removeOrder(id2);

        assert (orderFacade.getAllOrders().size() == 2);
        assert (!orderFacade.getOrderById(id1).equals(null));
        assert (orderFacade.getOrderById(id2) == null);
        assert (!orderFacade.getOrderById(id3).equals(null));

        orderFacade.removeOrder(id1);

        assert (orderFacade.getAllOrders().size() == 1);
        assert (orderFacade.getOrderById(id1) == null);
        assert (orderFacade.getOrderById(id2) == null);
        assert (!orderFacade.getOrderById(id3).equals(null));


        orderFacade.removeOrder(id3);

        assert (orderFacade.getAllOrders().size() == 0);
        assert (orderFacade.getOrderById(id1) == null);
        assert (orderFacade.getOrderById(id2) == null);
        assert (orderFacade.getOrderById(id3) == null);
    }

    @Test
    public void finishOrder() {
        Long id = orderFacade.createOrder(order1, user1.getLogin());
        orderFacade.finishOrder(id);
        assert (orderFacade.getOrderById(id).getState() == OrderState.DONE);
    }

    @Test
    public void cancelOrder() {
        Long id = orderFacade.createOrder(order1, user1.getLogin());
        orderFacade.cancelOrder(id);
        assert (orderFacade.getOrderById(id).getState() == OrderState.CANCELED);
    }

    @Test
    public void getOrdersByUser() {

        Long orderId = orderFacade.createOrder(order1, user1.getLogin());
        orderFacade.createOrder(order2, user1.getLogin());
        assert (orderFacade.getOrdersByUser(userId).size() == 2);
    }


    @Test
    public void addTireToOrder() {
        TireCreateDTO tireCreateDTO;
        tireCreateDTO = new TireCreateDTO();
        tireCreateDTO.setManufacturer("Michellin");
        tireCreateDTO.setPrice(new BigDecimal(7500));
        tireCreateDTO.setType("SuperBlack");

        Long tireId = tireFacade.createTire(tireCreateDTO);
        Long orderId = orderFacade.createOrder(order1, user1.getLogin());

        orderFacade.addTireToOrder(orderId, tireId);

        assert (tireFacade.getTireWithId(tireId).equals(tireFacade.getTireWithId(tireId)));
        assert (orderFacade.getOrderById(orderId).getTires().get(1).equals(tireFacade.getTireWithId(tireId)));
    }

    @Test
    public void addServiceToOrder() {
        ServiceCreateDTO serviceCreteDTO;
        serviceCreteDTO = new ServiceCreateDTO();
        serviceCreteDTO.setName("service1");
        serviceCreteDTO.setDescription("description1");
        serviceCreteDTO.setPrice(new BigDecimal("10000"));

        Long serviceId = serviceFacade.createService(serviceCreteDTO);
        Long orderId = orderFacade.createOrder(order1, user1.getLogin());

        orderFacade.addServiceToOrder(orderId, serviceId);

        assert (orderFacade.getOrderById(orderId).getServices().get(1).equals(serviceFacade.getServiceWithId(serviceId)));
    }


}
