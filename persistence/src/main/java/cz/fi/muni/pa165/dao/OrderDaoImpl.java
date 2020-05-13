package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
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
        if (order == null) {
            throw new DataAccessException("Attempting to create null order."){};
        }
        calculateTotalPrice(order);
        entityManager.persist(order);
    }

    @Override
    public List<Order> findAll() {
        Query query = entityManager.createQuery("SELECT o FROM Order o");
        return (List<Order>) query.getResultList();
    }

    @Override
    public Order findById(Long id) {
        if (id == null) {
            throw new DataAccessException("Cannot find order, because id is null."){};
        }
        return entityManager.find(Order.class, id);
    }

    @Override
    public void remove(Order order) {
        if (order == null) {
            throw new DataAccessException("Attempting to remove null order."){};
        }
        entityManager.remove(order);
    }

    @Override
    public void update (Order order) {
        if (order == null) {
            throw new DataAccessException("Attempting to update null order."){};
        }
        calculateTotalPrice(order);
        entityManager.merge(order);
    }

    @Override
    public List<Order> findByUser(User user) {
        if (user == null) {
            throw new DataAccessException("Attempting to find order by null user."){};
        }
        TypedQuery<Order> query = entityManager.createQuery(
                "Select o from Order o where o.user = :userid",
                Order.class);

        query.setParameter("userid", user);
        return query.getResultList();
    }

    @Override
    public void addService(Long orderId, Long serviceID) {
        if (orderId == null) {
            throw new DataAccessException("Cannot add service to order, because orderId is null."){};
        }
        if (serviceID == null) {
            throw new DataAccessException("Cannot add service to order, because serviceID is null."){};
        }
        Order order = findById(orderId);
        Service service = entityManager.find(Service.class, serviceID);
        order.getServices().add(service);
        update(order);
    }

    @Override
    public void addTire(Long orderId, Long tireID) {
        if (orderId == null) {
            throw new DataAccessException("Cannot add service to order, because orderId is null."){};
        }
        if (tireID == null) {
            throw new DataAccessException("Cannot add service to order, because tireID is null."){};
        }
        Order order = findById(orderId);
        Tire tire = entityManager.find(Tire.class, tireID);
        order.getTires().add(tire);
        update(order);
    }

    @Override
    public BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Tire tire : order.getTires()) {
            totalPrice.add(tire.getPrice());
        }

        for (Service service : order.getServices()) {
            totalPrice.add(service.getPrice());
        }

        order.setTotalPrice(totalPrice);
        return totalPrice;
    }
}
