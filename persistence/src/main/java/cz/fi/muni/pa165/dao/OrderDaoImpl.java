package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private final static Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public void create(Order order) {
        log.debug("dao create order");

        if (order == null) {
            throw new DataAccessException("Attempting to create null order."){};
        }
        calculateTotalPrice(order);
        entityManager.persist(order);
    }

    @Override
    public List<Order> findAll() {
        log.debug("dao find all orders");

        return  entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public Order findById(Long id) {
        log.debug("dao find order({})", id);

        if (id == null) {
            throw new DataAccessException("Cannot find order, because id is null."){};
        }
        return entityManager.find(Order.class, id);
    }

    @Override
    public void remove(Order order) {
        log.debug("dao remove order");
        if (order == null) {
            throw new DataAccessException("Attempting to remove null order."){};
        }
        entityManager.remove(order);
    }

    @Override
    public void update (Order order) {
        log.debug("dao update order");
        if (order == null) {
            throw new DataAccessException("Attempting to update null order."){};
        }
        calculateTotalPrice(order);
        entityManager.merge(order);
    }

    @Override
    public List<Order> findByUser(User user) {
        log.debug("dao find order by user");
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
        log.debug("dao addService to order");

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
        log.debug("dao addTire to order");
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
        log.debug("dao calculate total order price");
        if (order == null)
            return new BigDecimal(0);
        BigDecimal totalPrice = new BigDecimal(0);
        if (order.getTires() != null) {
            for (Tire tire : order.getTires()) {
                totalPrice = totalPrice.add(tire.getPrice());
            }
        }

        if (order.getServices() != null) {
            for (Service service : order.getServices()) {
                totalPrice = totalPrice.add(service.getPrice());
            }
        }

        order.setTotalPrice(totalPrice);
        return totalPrice;
    }
}
