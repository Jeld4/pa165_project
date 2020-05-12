package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Service;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

@Repository
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(Service service) {
        entityManager.persist(service);
    }

    @Override
    public List<Service> findAll() {
        return entityManager.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }

    @Override
    public Service findById(Long id) {
        return entityManager.find(Service.class, id);
    }

    @Override
    public void remove(Service service) {
        entityManager.remove(service);
    }

    @Override
    public void update(Service service) {
        entityManager.merge(service);
    }
}