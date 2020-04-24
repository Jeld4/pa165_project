package cz.fi.muni.pa165.facade;

import java.util.List;

import cz.fi.muni.pa165.dto.OrderDTO;

/**
 * @author Jan Jelínek 445416
 */
public interface OrderFacade {
    public List<OrderDTO> getAllOrders();

    public List<OrderDTO> getOrdersByUser(Long userId);

    public OrderDTO getOrderById(Long id);

    public void finishOrder(Long id);
    public void cancelOrder(Long id);
    public void removeOrder(Long id);

    public Long createOrder();

    public void addTireToOrder(Long orderId, Long tireId);
    public void addServiceToOrder(Long orderId, Long serviceId);

}
