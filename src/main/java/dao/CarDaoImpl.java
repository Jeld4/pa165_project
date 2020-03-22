package dao;

import entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

@Repository
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void crate(Car car) {
        entityManager.persist(car);
    }

    @Override
    public List<Car> findAll() {
//        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c", Car.class);

//        return  query.getResultList();
        return null;
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public void remove(Car car) {
        entityManager.remove(car);
    }
}
