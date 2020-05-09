package dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michal Klíma
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public OrderDao orderDao;
    
    @Autowired
    public TireDao tireDao;

    @Autowired
    public UserDao userDao;
    
    Order order1;
    Order order2;
 
    @BeforeMethod
    public void createOrder() {
    	order1 = new Order();
    	order2 = new Order();
    	
    	order1.setDateOfOrder(new Date());
    	order2.setDateOfOrder(new Date());
    	
    	order1.setState(OrderState.CONFIRMED);
    	order2.setState(OrderState.DONE);
    	
    	order1.setTotalPrice(11250);
    	order2.setTotalPrice(18560);
    	
    	
        Tire tire1 = new Tire();
        Tire tire2 = new Tire();

        tire1.setManufacturer("Black");
        tire2.setManufacturer("White");

        tire1.setPrice(3500);
        tire2.setPrice(3400);

        tire1.setSeason("summer");
        tire2.setSeason("winter");

        tire1.setType("Big");
        tire2.setType("Small");
        
        tireDao.create(tire1);
        tireDao.create(tire2);

        List<Tire> list1 = new ArrayList<Tire>();
        List<Tire> list2 = new ArrayList<Tire>();
       
        list1.add(tire1);
        list2.add(tire2);
        
        order1.setTires(list1);
        order2.setTires(list2);

        User user = new User();
        userDao.createUser(user);

        order1.setUser(user);
        order2.setUser(user);

        orderDao.create(order1);
        orderDao.create(order2);
    }

    @Test
    public void findAll(){
        List<Order> f = orderDao.findAll();
        AssertJUnit.assertEquals(f.size(), 2);
    }
    
    @Test
    public void findById(){
        Order found = orderDao.findById(order1.getId());
        AssertJUnit.assertEquals(found, order1);
    }
    
    
    @Test
    public void remove(){
        orderDao.remove(order1);
        List<Order> list =  orderDao.findAll();
        AssertJUnit.assertEquals(list.get(0).getId(), order2.getId());
        AssertJUnit.assertEquals(list.size(), 1);
        AssertJUnit.assertEquals(orderDao.findById(order1.getId()), null);
    }
    
    @Test
    public void update() {
    	order1.setState(OrderState.DONE);
    	orderDao.update(order1);
    	AssertJUnit.assertEquals(orderDao.findById(order1.getId()), order1);
    }
    
    @Test
    public void createAnotherOrder() {
        User user = new User();
        userDao.createUser(user);
        List<Order> bef = orderDao.findAll();
        Order order3 = new Order();
        order3.setUser(user);
        userDao.createUser(user);
        orderDao.create(order3);
        List<Order> aft = orderDao.findAll();
        AssertJUnit.assertEquals(aft.size(), bef.size() + 1);
        AssertJUnit.assertEquals(orderDao.findById(order3.getId()),order3);
    }
}
