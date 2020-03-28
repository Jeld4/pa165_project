package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
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
    // 
    // @Autowired
    // private UserDao userDao;
    //
    // private User u1;
    // private User u2;
    //
    // @BeforeMethod
    // public void prepare(){
    //     u1 = new User();
    //     u2 = new User();
    //
    //     u1.setLogin("xlogin1");
    //     u2.setLogin("xlogin2");
    //
    //     u1.setName("user1");
    //     u2.setName("user2");
    //
    //     userDao.createUser(u1);
    //     userDao.createUser(u2);
    // }
    //
    // @Test
    // public void testFindUser(){
    //     Assert.assertEquals(userDao.findUser(u1.getId()), u1);
    // }
    // @Test
    // public void testFindAll(){
    //     Assert.assertEquals(userDao.findAllUsers().size(), 2);
    // }
    // @Test
    // public void testRemove(){
    //     userDao.removeUser(u1);
    //     Assert.assertNull(userDao.findUser(u1.getId()));
    //     Assert.assertEquals(userDao.findAllUsers().size(), 1);
    // }
    //
    // @Test
    // public void updateUser(){
    //     u1.setLogin("newLogin1");
    //     //TODO
    // }

}
