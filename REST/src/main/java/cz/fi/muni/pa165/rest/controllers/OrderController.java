package cz.fi.muni.pa165.rest.controllers;


import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.facade.OrderFacade;
import cz.fi.muni.pa165.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jan Jelínek, 445416
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_ORDERS)
public class OrderController {

    final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderFacade orderFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<OrderDTO> getOrders() {
        logger.debug("rest getOrders()");
        return orderFacade.getAllOrders();
    }

    @RequestMapping(value = "by_user_id/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<OrderDTO> getOrdersByUserId(@PathVariable("user_id") long userId) {

        logger.debug("rest getOrderByUserId({})", userId);

        List<OrderDTO> orderDTOs = orderFacade.getOrdersByUser(userId);
        if (orderDTOs == null){
            throw new NullPointerException();
        }
        return orderDTOs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final OrderDTO getOrder(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getOrder({})", id);

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) {
            throw new NullPointerException();
        }

        return orderDTO;
    }


}
