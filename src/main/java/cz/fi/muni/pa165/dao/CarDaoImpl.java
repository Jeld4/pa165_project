package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

@Repository
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Car car) {
        entityManager.persist(car);
    }

    @Override
    public List<Car> findAll() {
        return  entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public void remove(Car car) {
        entityManager.remove(car);
    }

    @Override
    public void update(Car c) {
        entityManager.merge(c);
    }
}
