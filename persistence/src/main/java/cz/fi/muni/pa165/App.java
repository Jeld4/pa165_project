package cz.fi.muni.pa165;

import cz.fi.muni.pa165.entity.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class App {
    @PersistenceUnit
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabase.class);

        emf = Persistence.createEntityManagerFactory("default");

        EntityManager entityManager = emf.createEntityManager();

        Car car = new Car();
        car.setModel("Volvo");

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();


        Car found = entityManager.find(Car.class, car.getId());
        assert(!found.getModel().equals(car.getModel()));



    }
}
