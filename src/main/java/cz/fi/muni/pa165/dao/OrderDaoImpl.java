package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Jan Jelínek
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Order order) {
        entityManager.persist(order);
    }

    @Override
    public List<Order> findAll() {
        Query query = entityManager.createQuery("SELECT o FROM Order o");
        return (List<Order>) query.getResultList();
    }

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void remove(Order order) {
        entityManager.remove(order);
    }

    @Override
    public void update (Order order) {
        entityManager.merge(order);
    }
}
