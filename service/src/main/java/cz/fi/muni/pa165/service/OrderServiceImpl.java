package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.OrderDao;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
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

    @Override
    public void create(Order order) {
        orderDao.create(order);
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
        return orderDao.findAll();
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
    public void cancel(Order order) {
        order.setState(OrderState.CANCELED);
    }
}
