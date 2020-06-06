package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

@Repository
public class CarDaoImpl implements CarDao{

    private final static Logger log = LoggerFactory.getLogger(CarDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Car car)
    {
        if (car == null){
            throw new DataAccessException("Attempting to create null Car entity"){};
        }
        log.debug("DAO - create car");
        entityManager.persist(car);
    }

    @Override
    public List<Car> findAll() {
        log.debug("DAO - Find all cars");
        return  entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }


    @Override
    public Car findById(Long id) {
        if(id == null){
            throw new DataAccessException("Cannot find car under null ID"){};
        }
        log.debug("DAO - Find car with ID {}", id);
        return entityManager.find(Car.class, id);
    }

    @Override
    public void remove(Car car) {
        if (car == null){
            throw new DataAccessException("Attempting to remove null Car."){};
        }
        log.debug("DAO - removing car with ID {}", car.getId());
        entityManager.remove(car);
    }

    @Override
    public void update(Car car) {
        if (car == null){
            throw new DataAccessException("Attempting to update null Car."){};
        }
        log.debug("DAO - updating car with ID {}", car.getId());
        entityManager.merge(car);
    }

    @Override
    public Car findByLicencePlate(String licencePlate) {
        if(licencePlate.isEmpty()){
            throw new IllegalArgumentException("Cannot find car with empty licence plate");
        }

        log.debug("DAO - Searching for car with licence plate {}",licencePlate);
        return entityManager.createQuery("SELECT c From Car c where c.licencePlate=:licencePlate", Car.class)
                .setParameter("licencePlate", licencePlate)
                .getSingleResult();
    }

}
