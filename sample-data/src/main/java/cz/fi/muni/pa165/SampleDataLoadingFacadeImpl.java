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

/**
 * @author Jan Jelínek 445416
 */
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

        Tire t1 = tire("Barum", BigDecimal.valueOf(2),"Winter", "Dry", BigDecimal.valueOf(420));
        Tire t2 = tire("Michelin", BigDecimal.valueOf(3),"Winter", "All-terain", BigDecimal.valueOf(420));
        Tire t3 = tire("Barum", BigDecimal.valueOf(2),"Winter", "Dry", BigDecimal.valueOf(330));
        Tire t4 = tire("Cooper", BigDecimal.valueOf(2),"Summer", "Wet", BigDecimal.valueOf(420));
        Tire t5 = tire("Falken", BigDecimal.valueOf(4),"Summer", "Low profile", BigDecimal.valueOf(500));
        Tire t6 = tire("Goodyear", BigDecimal.valueOf(5),"Winter", "Wet", BigDecimal.valueOf(430));
        Tire t7 = tire("Continental", BigDecimal.valueOf(7),"Summer", "Wet", BigDecimal.valueOf(350));
        Tire t8 = tire("Toyo", BigDecimal.valueOf(1),"Winter", "Truck", BigDecimal.valueOf(555));
        Tire t9 = tire("Yokohama", BigDecimal.valueOf(6),"Summer", "Snow", BigDecimal.valueOf(358));
        Tire t10 = tire("Pirelli", BigDecimal.valueOf(2),"Winter", "SUV", BigDecimal.valueOf(500));

        List<Tire> tires1 = new ArrayList<>();
        tires1.add(t1);
        tires1.add(t2);
        tires1.add(t3);
        tires1.add(t4);
        List<Tire> tires2 = new ArrayList<>();
        tires2.add(t5);
        tires2.add(t6);
        tires2.add(t7);
        tires2.add(t8);

        Service s1 = service(new BigDecimal(50), "Come to our service and we check/change your tires", "Tires change");
        Service s2 = service(new BigDecimal(150), "Wheel balancing", "Calibration of wheels");

        List<Service> services1 = new ArrayList<>();
        services1.add(s1);

        Car c1 = car("SUV", "SUV", "7B8 - 5670");
        Car c2 = car("truck", "Truck", "8AX - 5877");
        Car c3 = car("trabant", "trabant", "XXX - 1234");

        List<Car> cars1 = new ArrayList<>();
        cars1.add(c1);
        cars1.add(c2);

        List<Car> cars2 = new ArrayList<>();
        cars2.add(c3);

        User admin = user("admin", "admin", "Administrator", true, cars2, "BigCity, LongStreet 1243", "+45086713924");
        User pepa = user("pepa123", "pepa123", "Pepa", false, cars1, "ShortCity, Short 1243", "+45086713924");
        User jessica = user("jessica123", "jessica", "Jessica", false, null, "Tiny Ville, Blue 1243", "+341243278569");

        Order order1 = order(pepa, tires1, services1);

    }

    private Tire tire(String man, BigDecimal size, String season, String type, BigDecimal price){
        Tire t = new Tire();
        t.setManufacturer(man);
        t.setSize(size);
        t.setSeason(season);
        t.setPrice(price);
        t.setType(type);

        tireService.create(t);
        return t;
    }

    private User user(String password, String login, String name, boolean isAdmin, List<Car> cars, String address, String phone) {
        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setName(name);
        user.setIsAdmin(isAdmin);
        user.setCars(cars);
        user.setUserAddress(address);
        user.setTelephone(phone);

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
