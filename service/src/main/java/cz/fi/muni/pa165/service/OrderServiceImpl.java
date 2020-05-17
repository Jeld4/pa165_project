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
        User user = userDao.getUserByLogin(userLogin);
        order.setUser(user);
        orderDao.create(order);

        /*
        List<Order> orders = null;
        orders = user.getOrders();
        orders.add(order);
        user.setOrders(orders);
        userDao.updateUser(user);
        
         */

    }

    @Override
    public void remove(Order order) {
        orderDao.remove(order);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = null;
        try {
            orders = orderDao.findAll();
        } catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderDao.findByUser(user);
    }

    @Override
    public void finish(Order order) {
        order.setState(OrderState.DONE);
    }

    @Override
    public void addTireToOrder(Long orderId, Long tireId) {
        orderDao.addTire(orderId, tireId);
    }

    @Override
    public void addServiceToOrder(Long orderId, Long serviceId) {
        orderDao.addService(orderId, serviceId);
    }

    @Override
    public Car getOrderCar(Long orderId) {
        Order order = orderDao.findById(orderId);
        return carDao.findById(order.getCar().getId());
    }

    @Override
    public void cancel(Order order) {
        order.setState(OrderState.CANCELED);
    }
}
