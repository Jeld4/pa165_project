package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.CarDao;
import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
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

    @Override
    public void create(Order order, String userLogin) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getServices().isEmpty() && order.getTires().isEmpty()) {
            throw new IllegalArgumentException("Cannot create empty order.");
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
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return orderDao.findByUser(user);
    }

    @Override
    public void confirm(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.CONFIRMED);
    }

    @Override
    public void finish(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.DONE);
    }

    @Override
    public void addTireToOrder(Long orderId, Long tireId) {
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
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        Order order = orderDao.findById(orderId);
        return carDao.findById(order.getCar().getId());
    }

    @Override
    public void cancel(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        order.setState(OrderState.CANCELED);
    }
}
