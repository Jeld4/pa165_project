package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;

import java.util.List;

/**
 * @author Jan Jelínek
 */
public interface OrderDao {

    /**
     * Method to create a new order.
     * @param order
     */
    void create(Order order);

    /**
     * Method to find all orders.
     * @return
     */
    List<Order> findAll();

    /**
     * Method to find order by id.
     * @param id
     * @return
     */
    Order findById(Long id);

    /**
     * Method used to remove order.
     * @param order
     */
    void remove(Order order);

    /**
     * Method to update existing order.
     * @param order
     */
    void update(Order order);

    /**
     * Finds order by user
     * @param u
     * @return
     */
    List<Order> findByUser(User u);

    /**
     * Adds service to order
     */
    void addService(Long orderId, Long serviceId);

    /**
     * Adds tire to order
     */
    void addTire(Long orderId, Long tireId);
}