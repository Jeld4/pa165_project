package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Order;

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
}