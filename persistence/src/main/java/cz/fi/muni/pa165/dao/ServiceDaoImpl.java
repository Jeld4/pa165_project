package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Service;
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
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    EntityManager entityManager;

    private final static Logger log = LoggerFactory.getLogger(ServiceDaoImpl.class);


    @Override
    public void create(Service service) {

        if (service == null){
            throw new DataAccessException("Attempting to create null Service entity"){};
        }
        log.debug("DAO - create Service");
        entityManager.persist(service);
    }

    @Override
    public List<Service> findAll() {
        log.debug("DAO - Find all services");
        return entityManager.createQuery("SELECT s FROM Service s", Service.class).getResultList();
    }

    @Override
    public Service findById(Long id) {

        if(id == null){
            throw new DataAccessException("Cannot find service under null ID"){};
        }
        log.debug("DAO - Find service with ID {}", id);
        return entityManager.find(Service.class, id);
    }

    @Override
    public void remove(Service service) {

        if (service == null){
            throw new DataAccessException("Attempting to remove null Service."){};
        }
        log.debug("DAO - removing service with ID {}", service.getId());
        entityManager.remove(service);
    }

    @Override
    public void update(Service service) {

        if (service == null){
            throw new DataAccessException("Attempting to update null Service."){};
        }
        log.debug("DAO - updating service with ID {}", service.getId());
        entityManager.merge(service);
    }
}