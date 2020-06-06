package dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Radim Sasinka, 456315
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoImplTest extends AbstractTestNGSpringContextTests {
     
     @Autowired
     private UserDao userDao;
    
     private User user1;
     private User user2;
    
     @BeforeMethod
     public void prepare(){
         user1 = new User();
         user2 = new User();
    
         user1.setLogin("xlogin1");
         user2.setLogin("xlogin2");
    
         user1.setName("user1");
         user2.setName("user2");

         user1.setPassword("passss");
         user2.setPassword("worddd");

         user1.setUserAddress("Prague");
         user2.setUserAddress("Brno");

         user1.setTelephone("666555444");
         user2.setTelephone("554423212");
    
         userDao.createUser(user1);
         userDao.createUser(user2);
     }
    
     @Test
     public void testFindUser(){
         Assert.assertEquals(userDao.getUserById(user1.getId()), user1);
     }
     @Test
     public void testFindAll(){
         Assert.assertEquals(userDao.findAllUsers().size(), 2);
     }
     @Test
     public void testRemove(){
         userDao.removeUser(user1);
         Assert.assertNull(userDao.getUserById(user1.getId()));
         Assert.assertEquals(userDao.findAllUsers().size(), 1);
     }
    
     @Test
     public void updateUser(){
         user1.setLogin("newLogin1");
         //TODO
     }

}
