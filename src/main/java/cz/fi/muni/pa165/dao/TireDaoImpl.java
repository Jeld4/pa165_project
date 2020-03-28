package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Tire;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
@Repository
public class TireDaoImpl implements TireDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Tire t) {
        em.persist(t);
    }

    @Override
    public List<Tire> findAll() {
        return em.createQuery("select t from Tire t", Tire.class).getResultList();
    }

    @Override
    public Tire findById(Long id) {
        return em.find(Tire.class, id);
    }

    @Override
    public void remove(Tire t) {
        em.remove(t);
    }
}
