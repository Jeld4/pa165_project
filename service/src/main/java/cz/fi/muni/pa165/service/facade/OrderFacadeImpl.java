package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.CarDTO;
import cz.fi.muni.pa165.dto.OrderCreateDTO;
import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.facade.OrderFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.OrderService;
import cz.fi.muni.pa165.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jan Jelínek 445416
 */
@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade {

    @Inject
    private OrderService orderService;

    @Inject
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createOrder(OrderCreateDTO order, String userLogin) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getServices().isEmpty() && order.getTires().isEmpty()) {
            throw new IllegalArgumentException("Cannot create empty order.");
        }
        if (userLogin.isEmpty()) {
            throw new IllegalArgumentException("Cannot assign order to empty user.");
        }
        Order newOrder = new Order();
        newOrder.setState(order.getState());
        newOrder.setDateOfOrder(order.getDateOfOrder());
        orderService.create(newOrder, userLogin);
        return newOrder.getId();
    }

    @Override
    public void addTireToOrder(Long orderId, Long tireId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        if (tireId == null) {
            throw new IllegalArgumentException("Tire id cannot be null");
        }
        orderService.addTireToOrder(orderId, tireId);
    }

    @Override
    public void addServiceToOrder(Long orderId, Long serviceId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        if (serviceId == null) {
            throw new IllegalArgumentException("Service id cannot be null");
        }
        orderService.addServiceToOrder(orderId, serviceId);
    }

    @Override
    public CarDTO getOrderCar(Long orderId) {
        Car car = orderService.getOrderCar(orderId);
        return beanMappingService.mapTo(car, CarDTO.class);
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
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        orderService.finish(orderService.findById(id));
    }

    @Override
    public void cancelOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        orderService.cancel(orderService.findById(id));
    }

    @Override
    public void removeOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        orderService.remove(orderService.findById(id));
    }

    @Override
    public void confirmOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        orderService.confirm(orderService.findById(id));
    }

}