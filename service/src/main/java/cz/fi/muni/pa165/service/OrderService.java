package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;

import java.util.List;

/**
 * @author Jan Jelínek 445416
 */
public interface OrderService {

    void create(Order order);
    void remove(Order order);
    Order findById(Long id);
    List<Order> findAll();
    List<Order> getOrdersByUser(User user);
    void cancel(Order order);
    void finish(Order order);
    void addTireToOrder(Long orderId, Long tireId);
    void addServiceToOrder(Long orderId, Long serviceId);
}
