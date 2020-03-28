package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.enums.OrderState; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import java.util.ArrayList;
import java.util.Date;

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
        
        orderDao.create(o1);
        orderDao.create(o2);
    }

    @Test
    public void findAll(){
        List<Order> f = orderDao.findAll();
        Assert.assertEquals(f.size(), 2);
    }
    
    @Test
    public void findById(){
        Order f = orderDao.findById(o1.getId());
        Assert.assertEquals(f.getId(), o1.getId());
    }
    
    
    @Test
    public void remove(){
        orderDao.remove(o1);
        List<Order> li =  orderDao.findAll();
        Assert.assertEquals(li.get(0).getId(), o2.getId());
        Assert.assertEquals(li.size(), 1);
        assert(orderDao.findById(o1.getId()) == null);
    }
    
    @Test
    public void update() {
    	o1.setState(OrderState.DONE);
    	orderDao.update(o1);
    	Assert.assertEquals(orderDao.findById(o1.getId()).getState(), o1.getState());
    	
    }
    
    

}
