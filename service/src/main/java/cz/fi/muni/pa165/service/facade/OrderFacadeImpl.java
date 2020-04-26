package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.enums.OrderState;
import cz.fi.muni.pa165.facade.OrderFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.OrderService;
import cz.fi.muni.pa165.service.UserService;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jan Jelínek 445416
 */
@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade {

    @Inject
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createOrder() {
        Order newOrder = new Order();
        newOrder.setState(OrderState.PENDING);
        orderService.create(newOrder);
        return newOrder.getId();
    }

    @Override
    public void addTireToOrder(Long orderId, Long tireId) {
        orderService.addTireToOrder(orderId, tireId);
    }

    @Override
    public void addServiceToOrder(Long orderId, Long serviceId) {
        orderService.addServiceToOrder(orderId, serviceId);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        User user = userService.findById(userId);
        List<Order> orders = orderService.getOrdersByUser(user);

        return beanMappingService.mapTo(orders, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return beanMappingService.mapTo(orderService.findAll(),
                OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderService.findById(id);
        return (order == null) ? null : beanMappingService.mapTo(order, OrderDTO.class);
    }

    @Override
    public void finishOrder(Long id) {
        orderService.finish(orderService.findById(id));
    }

    @Override
    public void cancelOrder(Long id) {
        orderService.cancel(orderService.findById(id));
    }

    @Override
    public void removeOrder(Long id) {
        orderService.remove(orderService.findById(id));
    }

}