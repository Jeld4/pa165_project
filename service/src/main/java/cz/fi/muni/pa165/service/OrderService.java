package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;

import java.util.List;

/**
 * @author Jan Jelínek 445416
 */
public interface OrderService {

    /**
     * Creates order in the system
     * @param order to be created
     * @param userLogin user login
     */
    void create(Order order, String userLogin);

    /**
     * Removes car from the system
     * @param order to be removed
     */
    void remove(Order order);

    /**
     * Finds car by its id
     * @param id ID of car to be found
     * @return found order
     */
    Order findById(Long id);

    /**
     * Finds all orders in system
     * @return list of all orders in system
     */
    List<Order> findAll();

    /**
     * Returns order witch belongs to the user
     * @param user which order we want to find
     * @return user´s order
     */
    List<Order> getOrdersByUser(User user);

    /**
     * Sets the order Confirm status
     * @param order to be changed
     */
    void confirm(Order order);

    /**
     * Sets the order Cancel status
     * @param order to be changed
     */
    void cancel(Order order);

    /**
     * Sets the order finished status
     * @param order to be changed
     */
    void finish(Order order);

    /**
     * Adds tire to the order
     * @param orderId id of the order we want to change
     * @param tireId id of the tire we want to add to order
     */
    void addTireToOrder(Long orderId, Long tireId);

    /**
     * Adds service to the order
     * @param orderId id of the order we want to change
     * @param serviceId id of the service we want to add to order
     */
    void addServiceToOrder(Long orderId, Long serviceId);

    /**
     * Get car of the order
     * @param orderId id of the order
     * @return car of the order
     */
    Car getOrderCar(Long orderId);
}
