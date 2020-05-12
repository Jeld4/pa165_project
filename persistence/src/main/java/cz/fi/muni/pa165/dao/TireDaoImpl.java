package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Tire;
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

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Tire tire) {
        em.persist(tire);
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
    public List<Tire> findByManufacturer(String manufacturer) {
        TypedQuery<Tire> query = em.createQuery(
                "Select tires from Tire tires WHERE manufacturer = :manufacturer",
                Tire.class);

        query.setParameter("manufacturer", manufacturer);
        return query.getResultList();
    }

    @Override
    public void remove(Tire tire) {
        em.remove(tire);
    }

    @Override
    public void update (Tire tire) {
        em.merge(tire);
    }
}
