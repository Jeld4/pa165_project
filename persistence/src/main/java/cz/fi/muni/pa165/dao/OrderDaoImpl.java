package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Order> findByUser(User u) {
        TypedQuery<Order> query = entityManager.createQuery(
                "Select o from Order o where o.user = :userid",
                Order.class);

        query.setParameter("userid", u);
        return query.getResultList();
    }

    @Override
    public void addService(Long orderId, Long serviceID) {
        Order order = findById(orderId);
        Service service = entityManager.find(Service.class, serviceID);
        order.getServices().add(service);
        update(order);
    }

    @Override
    public void addTire(Long orderId, Long tireID) {
        Order order = findById(orderId);
        Tire tire = entityManager.find(Tire.class, tireID);
        order.getTires().add(tire);
        update(order);
    }
}
