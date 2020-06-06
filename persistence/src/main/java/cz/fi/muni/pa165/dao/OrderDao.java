package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jan Jelínek
 */
public interface OrderDao {

    /**
     * Method to create a new order.
     * @param order order to create
     */
    void create(Order order);

    /**
     * Method to find all orders.
     * @return list of all orders
     */
    List<Order> findAll();

    /**
     * Method to find order by id.
     * @param id of the order we want to find
     * @return order
     */
    Order findById(Long id);

    /**
     * Method used to remove order.
     * @param order to be removed
     */
    void remove(Order order);

    /**
     * Method to update existing order.
     * @param order to be updated
     */
    void update(Order order);

    /**
     * Finds order by user
     * @param user to find his orders
     * @return list of user's orders
     */
    List<Order> findByUser(User user);

    /**
     * Adds service to order
     */
    void addService(Long orderId, Long serviceId);

    /**
     * Adds tire to order
     */
    void addTire(Long orderId, Long tireId);

    /**
     * Calculates and set total price of order
     * @param order to be set
     * @return total price of order
     */
    BigDecimal calculateTotalPrice(Order order);
}