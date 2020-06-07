package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.OrderCreateDTO;
import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.OrderFacade;
import cz.fi.muni.pa165.hateoas.OrderRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jan Jelínek
 */
@RestController
@ExposesResourceFor(OrderDTO.class)
@RequestMapping("/orders")
public class OrderController {

    private OrderFacade orderFacade;
    private OrderRepresentationModelAssembler orderRepresentationModelAssembler;
    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderController(@Autowired OrderFacade orderFacade, @Autowired OrderRepresentationModelAssembler orderRepresentationModelAssembler) {
        this.orderFacade = orderFacade;
        this.orderRepresentationModelAssembler = orderRepresentationModelAssembler;
    }

    /**
     * REST function for returning all orders
     * @return Response entity containing all orders
     */
    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders() {
        log.debug("rest getOrders()");

        CollectionModel<EntityModel<OrderDTO>> ordersCollectionModel = orderRepresentationModelAssembler.toCollectionModel(orderFacade.getAllOrders());
        return new ResponseEntity<>(ordersCollectionModel, HttpStatus.OK);
    }

    /**
     * REST function for getting one specific order
     * @param id ID of the order
     * @return Response entity containing desired order
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<OrderDTO>> getOrder(@PathVariable("id") long id) {
        log.debug("rest getOrder({})", id);

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) throw new ResourceNotFoundException("order " + id + " not found");
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderDTO);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    /**
     * REST function for creating new order
     * @param userLogin users login who creates the order
     * @param order OrderCreateDTO obj of the new car
     * @return http response
     */
    @RequestMapping(value = "/create/{userLogin}", method = RequestMethod.POST)
    public final HttpStatus createOrder(@PathVariable("userLogin") String userLogin, @RequestBody OrderCreateDTO order) {
        log.debug("rest createOrder()");

        Long id = orderFacade.createOrder(order, userLogin);
        return HttpStatus.OK;
    }

    /**
     * Controller used to call confirm function
     * @param id of the order
     * @return Response entity containing the order
     */
    @RequestMapping(value = "/{id}/confirm", method = RequestMethod.POST)
    public final HttpEntity<EntityModel<OrderDTO>> confirmOrder(@PathVariable("id") long id) {
        log.debug("rest confirmOrder({})", id);

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) throw new ResourceNotFoundException("order " + id + " not found");
        orderFacade.confirmOrder(id);
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderDTO);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    /**
     * Controller used to call cancel function
     * @param id of the order
     * @return Response entity containing the order
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public final HttpEntity<EntityModel<OrderDTO>> cancelOrder(@PathVariable("id") long id) {
        log.debug("rest cancelOrder({})", id);

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) throw new ResourceNotFoundException("order " + id + " not found");
        orderFacade.cancelOrder(id);
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderDTO);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    /**
     * Controller used to call finish function
     * @param id of the order
     * @return Response entity containing the order
     */
    @RequestMapping(value = "/{id}/finish", method = RequestMethod.POST)
    public final HttpEntity<EntityModel<OrderDTO>> finishOrder(@PathVariable("id") long id) {
        log.debug("rest finishOrder({})", id);

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) throw new ResourceNotFoundException("order " + id + " not found");
        orderFacade.finishOrder(id);
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderDTO);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    /**
     * REST function for creating new order
     * @param user user who creates the order
     * @param order OrderCreateDTO obj of the new car
     * @param bindingResult bindingResult
     * @return Response entity containing new created order
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<OrderDTO>> createOrder(@RequestBody @Valid OrderCreateDTO order, @RequestBody @Valid UserDTO user, BindingResult bindingResult) {
        log.debug("rest createOrder()");

        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        Long id = orderFacade.createOrder(order, user.getLogin());
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderFacade.getOrderById(id));
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    /**
     * REST Function for deleting specific order
     * @param id of the order
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteOrder(@PathVariable("id") long id) {
        log.debug("rest deleteOrder({})", id);
        try {
            orderFacade.removeOrder(id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("order " + id + " not found");
        }
    }

    /**
     * Returns all orders under specific user
     * @param id of the user
     * @return Response entity containing collection of orders belonging to the user
     */
    @RequestMapping(value = "/getByUser/{id}", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<OrderDTO>>> getOrdersByUser(@PathVariable("id") long id) {
        log.debug("rest getOrdersByUser({})", id);

        List<OrderDTO> ordersListDTO = orderFacade.getOrdersByUser(id);

        if (ordersListDTO == null) throw new ResourceNotFoundException("orders " + id + " not found");

        CollectionModel<EntityModel<OrderDTO>> ordersCollectionModel = orderRepresentationModelAssembler.toCollectionModel(ordersListDTO);

        return new ResponseEntity<>(ordersCollectionModel, HttpStatus.OK);
    }

}