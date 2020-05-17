package cz.fi.muni.pa165;

import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.enums.OrderState;
import cz.fi.muni.pa165.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private TireService tireService;
	
    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @Override
    public void loadData() throws IOException {

        Tire t1 = tire("Barum","Winter", "Dry", BigDecimal.valueOf(420));
        Tire t2 = tire("Michelin","Winter", "All-terain", BigDecimal.valueOf(420));
        Tire t3 = tire("Barum","Winter", "Dry", BigDecimal.valueOf(330));
        Tire t4 = tire("Cooper","Summer", "Wet", BigDecimal.valueOf(420));
        Tire t5 = tire("Falken","Summer", "Low profile", BigDecimal.valueOf(500));
        Tire t6 = tire("Goodyear","Winter", "Wet", BigDecimal.valueOf(430));
        Tire t7 = tire("Continental","Summer", "Wet", BigDecimal.valueOf(350));
        Tire t8 = tire("Toyo","Winter", "Truck", BigDecimal.valueOf(555));
        Tire t9 = tire("Yokohama","Summer", "Snow", BigDecimal.valueOf(358));
        Tire t10 = tire("Pirelli","Winter", "SUV", BigDecimal.valueOf(500));

        List<Tire> tires1 = new ArrayList<>();
        tires1.add(t1);
        tires1.add(t2);
        tires1.add(t3);
        tires1.add(t4);
        List<Tire> tires2 = new ArrayList<>();
        tires1.add(t5);
        tires1.add(t6);
        tires1.add(t7);
        tires1.add(t8);

        Service s1 = service(new BigDecimal(50), "Come to our service and we check/change your tires", "Tires change");
        Service s2 = service(new BigDecimal(150), "Wheel balancing", "Calibration of wheels");

        List<Service> services1 = new ArrayList<>();
        services1.add(s1);
        services1.add(s2);

        Car c1 = car("SUV", "SUV", "7B8 - 5670");
        Car c2 = car("truck", "Truck", "8AX - 5877");

        List<Car> cars1 = new ArrayList<>();
        cars1.add(c1);
        cars1.add(c2);

        List<Car> cars2 = new ArrayList<>();
        cars2.add(c1);

        User admin = user("admin", "admin", "admin", true, cars2);
        User pepa = user("pepa123", "pepa123", "Pepa", false, cars1);
        User jessica = user("jessica123", "jessica", "Jessica", false, cars2);

        Order order1 = order(pepa, tires1, services1);
        Order order2 = order(jessica, null, null);
        Order order3 = order(pepa, null, null);
        Order order4 = order(jessica, null, null);
        Order order5 = order(admin, null, null);

    }

    private Tire tire(String man, String season, String type, BigDecimal price){
        Tire t = new Tire();
        t.setManufacturer(man);
        t.setSeason(season);
        t.setPrice(price);
        t.setType(type);

        tireService.create(t);
        return t;
    }

    private User user(String password, String login, String name, boolean isAdmin, List<Car> cars) {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setName(name);
        user.setIsAdmin(isAdmin);
        user.setCars(cars);

        userService.create(user);
        return user;
    }

    private Order order(User user, List<Tire> tires, List<Service> services) {
        Order order = new Order();
        order.setTires(tires);
        order.setState(OrderState.PENDING);
        order.setTotalPrice(new BigDecimal(50));
        order.setServices(services);

        orderService.create(order, user.getLogin());
        return order;
    }

    private Car car(String model, String tireType, String licencePlate) {
        Car car = new Car();
        car.setModel(model);
        car.setTireType(tireType);
        car.setLicencePlate(licencePlate);

        carService.create(car);
        return car;
    }

    private Service service(BigDecimal price, String description, String name) {
        Service service = new Service();
        service.setPrice(price);
        service.setDescription(description);
        service.setName(name);

        serviceService.create(service);
        return service;
    }
}
