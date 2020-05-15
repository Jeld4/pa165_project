package cz.fi.muni.pa165;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.TireService;
import cz.fi.muni.pa165.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private TireService tireService;
	
    @Autowired
    private UserService userService;

    @Override
    public void loadData() throws IOException {

        Tire t1 = tire("Barum","Zima", "BIG AF", BigDecimal.valueOf(420));
        
        User u1 = user("Rainbow","NoOne", "123456");
        User u2 = user("AbsoluteUnitOfUser","Magic", "654321");
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
    
    private User user(String name, String login, String password){
        User u = new User();
        u.setIsAdmin(false);
        u.setLogin(login);
        u.setName(name);
        u.setOrders(new ArrayList<Order>());
        u.setCars(new ArrayList<Car>());
        u.setPassword(password);

        userService.create(u);
        return u;
    }
}
