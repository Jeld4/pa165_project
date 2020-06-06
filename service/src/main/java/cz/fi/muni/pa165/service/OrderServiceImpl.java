package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jan Jelínek 445416
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CarDao carDao;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void create(Order order, String userLogin) {
        log.debug("service create order");

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getServices().isEmpty() && order.getTires().isEmpty()) {
            throw new IllegalArgumentException("Cannot create empty order.");
        }
        if (userLogin == null) {
            throw new IllegalArgumentException("User login cannot be null.");
        }
        User user = userDao.getUserByLogin(userLogin);
        order.setUser(user);
        try {
            orderDao.create(order);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Order order) {
        log.debug("service remove order");
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        try {
            orderDao.remove(order);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Order findById(Long id) {
        log.debug("service findById({})", id);
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        Order order = null;
        try {
            order = orderDao.findById(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        log.debug("service findAll()");
        List<Order> orders = null;
        try {
            orders = orderDao.findAll();
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        log.debug("service get orders by user");
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return orderDao.findByUser(user);
    }

    @Override
    public void confirm(Order order) {
        log.debug("service confirm order");
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.CONFIRMED);
    }

    @Override
    public void finish(Order order) {
        log.debug("service finish order");
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.DONE);
    }

    @Override
    public void addTireToOrder(Long orderId, Long tireId) {
        log.debug("service addTireToOrder({}, {})", orderId, tireId);
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        if (tireId == null) {
            throw new IllegalArgumentException("Service id cannot be null");
        }
        orderDao.addTire(orderId, tireId);
    }

    @Override
    public void addServiceToOrder(Long orderId, Long serviceId) {
        log.debug("service addServiceToOrder({}, {})", orderId, serviceId);
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        if (serviceId == null) {
            throw new IllegalArgumentException("Service id cannot be null");
        }
        orderDao.addService(orderId, serviceId);
    }

    @Override
    public Car getOrderCar(Long orderId) {
        log.debug("service getOrderCar({})", orderId);
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        Order order = orderDao.findById(orderId);
        return carDao.findById(order.getCar().getId());
    }

    @Override
    public void cancel(Order order) {
        log.debug("service cancel order");
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.CANCELED);
    }
}
