package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Tire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * @author Jakub Malý, 456389
 */
@Repository
public class TireDaoImpl implements TireDao {

    private final static Logger log = LoggerFactory.getLogger(CarDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Tire tire) {
        if (tire == null){
            throw new DataAccessException("Attempting to create null Tire entity"){};
        }
        log.debug("DAO - create Tire");
        em.persist(tire);
    }

    @Override
    public List<Tire> findAll() {
        log.debug("DAO - Find all tires");
        return em.createQuery("select t from Tire t", Tire.class).getResultList();
    }

    @Override
    public Tire findById(Long id) {
        if(id == null){
            throw new DataAccessException("Cannot find tire under null ID"){};
        }
        log.debug("DAO - Find tire by ID");
        return em.find(Tire.class, id);
    }

    @Override
    public List<Tire> findByManufacturer(String manufacturer) {
        TypedQuery<Tire> query = em.createQuery(
                "Select tires from Tire tires WHERE manufacturer = :manufacturer",
                Tire.class);

        query.setParameter("manufacturer", manufacturer);
        log.debug("DAO - Find tire by MANUFACTURER {}", manufacturer);
        return query.getResultList();
    }

    @Override
    public void remove(Tire tire) {
        if (tire == null){
            throw new DataAccessException("Attempting to remove null Tire."){};
        }
        log.debug("DAO - removing tire with ID {}", tire.getId());
        em.remove(tire);
    }

    @Override
    public void update (Tire tire) {
        if (tire == null){
            throw new DataAccessException("Attempting to update null Tire."){};
        }
        log.debug("DAO - updating tire with ID {}", tire.getId());
        em.merge(tire);
    }
}
