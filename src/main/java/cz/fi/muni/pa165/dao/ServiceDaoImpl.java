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
    public void create(Service s) {
        entityManager.persist(s);
    }

    @Override
    public List<Service> findAll() {
//        TypedQuery<Service> query = entityManager.createQuery("SELECT q FROM Order q",
//                Service.class);
//        return query.getResultList();
        return null;
    }

    @Override
    public Service findById(Long id) {
        return entityManager.find(Service.class, id);
    }

    @Override
    public void remove(Service s) {
        entityManager.remove(s);
    }
}
