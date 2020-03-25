package test;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dao.OrderDaoImpl;
import dao.UserDaoImpl;
import entity.Customer;
import entity.Order;
 
public class TestHibernate
{

   private static EntityManagerFactory emf;
   public static void main(String[] args)
   {
	   AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		UserDaoImpl ord = new UserDaoImpl();
		ord.setEntityManager(em);
		ord.createCustomer(new Customer("name", "login","password", "emial", "telephone"));
		em.getTransaction().commit();
		em.close();
		
		emf.close();
		appContext.close();
   }
}