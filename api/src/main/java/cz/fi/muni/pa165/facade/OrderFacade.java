package cz.fi.muni.pa165.facade;

import java.util.List;

import cz.fi.muni.pa165.dto.OrderCreateDTO;
import cz.fi.muni.pa165.dto.OrderDTO;

/**
 * @author Jan Jelínek 445416
 */
public interface OrderFacade {

    /**
     * Returns all orders in system
     * @return list of all orders in system
     */
    List<OrderDTO> getAllOrders();

    /**
     * Returns user´s orders
     * @param userId id of user
     * @return list of user´s orders
     */
    List<OrderDTO> getOrdersByUser(Long userId);

    /**
     * Returns order by its id
     * @param id of the order
     * @return order
     */
    OrderDTO getOrderById(Long id);

    /**
     * Changes the status of the order to Finished
     * @param id of the order
     */
    void finishOrder(Long id);

    /**
     * Changes the status of the order to Canceled
     * @param id of the order
     */
    void cancelOrder(Long id);

    /**
     * Removes the specific order from the system
     * @param id of the order we want to remove
     */
    void removeOrder(Long id);

    /**
     * Changes the status of the order to Confirm
     * @param id of the Order
     */
    void confirmOrder(Long id);

    /**
     * Creates order to the user specified by users login
     * @param order order
     * @param userLogin login of the user who creates the order
     * @return id of the new order
     */
    Long createOrder(OrderCreateDTO order, String userLogin);

    /**
     * Adds tire to the order
     * @param orderId id of the order
     * @param tireId id of the tire
     */
    void addTireToOrder(Long orderId, Long tireId);

    /**
     * Adds service to the order
     * @param orderId id of the order
     * @param serviceId id of the service
     */
    void addServiceToOrder(Long orderId, Long serviceId);
}
