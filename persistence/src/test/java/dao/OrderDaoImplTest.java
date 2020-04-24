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
    
    Order o1;
    Order o2;
 
    @BeforeMethod
    public void createOrder() {
    	o1 = new Order();
    	o2 = new Order();
    	
    	o1.setDateOfOrder(new Date());
    	o2.setDateOfOrder(new Date());
    	
    	o1.setState(OrderState.CONFIRMED);
    	o2.setState(OrderState.DONE);
    	
    	o1.setTotalPrice(11250);
    	o2.setTotalPrice(18560);
    	
    	
        Tire t1 = new Tire();
        Tire t2 = new Tire();

        t1.setManufacturer("Black");
        t2.setManufacturer("White");

        t1.setPrice(3500);
        t2.setPrice(3400);

        t1.setSeason("summer");
        t2.setSeason("winter");

        t1.setType("Big");
        t2.setType("Small");
        
        tireDao.create(t1);
        tireDao.create(t2);

        List<Tire> l1 = new ArrayList<Tire>();
        List<Tire> l2 = new ArrayList<Tire>();
       
        l1.add(t1);
        l2.add(t2);
        
        o1.setTires(l1);
        o2.setTires(l2);

        User user = new User();
        userDao.createUser(user);

        o1.setUser(user);
        o2.setUser(user);

        orderDao.create(o1);
        orderDao.create(o2);
    }

    @Test
    public void findAll(){
        List<Order> f = orderDao.findAll();
        AssertJUnit.assertEquals(f.size(), 2);
    }
    
    @Test
    public void findById(){
        Order f = orderDao.findById(o1.getId());
        AssertJUnit.assertEquals(f, o1);
    }
    
    
    @Test
    public void remove(){
        orderDao.remove(o1);
        List<Order> li =  orderDao.findAll();
        AssertJUnit.assertEquals(li.get(0).getId(), o2.getId());
        AssertJUnit.assertEquals(li.size(), 1);
        AssertJUnit.assertEquals(orderDao.findById(o1.getId()), null);
    }
    
    @Test
    public void update() {
    	o1.setState(OrderState.DONE);
    	orderDao.update(o1);
    	AssertJUnit.assertEquals(orderDao.findById(o1.getId()), o1);
    }
    
    @Test
    public void createAnotherOrder() {
        User user = new User();
        userDao.createUser(user);
        List<Order> bef = orderDao.findAll();
        Order o3 = new Order();
        o3.setUser(user);
        userDao.createUser(user);
        orderDao.create(o3);
        List<Order> aft = orderDao.findAll();
        AssertJUnit.assertEquals(aft.size(), bef.size() + 1);
        AssertJUnit.assertEquals(orderDao.findById(o3.getId()),o3);
    }
}
