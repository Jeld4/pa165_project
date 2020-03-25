package test;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import dao.OrderDaoImpl;
import dao.TireDaoImpl;
import dao.UserDaoImpl;
import entity.Employee;
import entity.Order;
import entity.Tire;
import util.HibernateUtil;
 
public class TestHibernate
{

   private static EntityManagerFactory emf;
   public static void main(String[] args)
   {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      // Add new Employee object
      //emf = Persistence.createEntityManagerFactory("pu1");
      
      
      OrderDaoImpl orderDao = new OrderDaoImpl();
      orderDao.create(new Order ());
      //System.out.println(emp);
      //session.save(emp);
      session.getTransaction().commit();
      HibernateUtil.shutdown();
   }
}