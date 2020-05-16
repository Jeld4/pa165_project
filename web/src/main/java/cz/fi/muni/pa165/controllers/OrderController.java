package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.OrderCreateDTO;
import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
import cz.fi.muni.pa165.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.facade.OrderFacade;
import cz.fi.muni.pa165.hateoas.OrderRepresentationModelAssembler;
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
import org.springframework.web.server.ServerErrorException;

import javax.validation.Valid;

@RestController
@ExposesResourceFor(OrderDTO.class)
@RequestMapping("/orders")
public class OrderController {

    private OrderFacade orderFacade;
    private OrderRepresentationModelAssembler orderRepresentationModelAssembler;

    private OrderController(@Autowired OrderFacade orderFacade, @Autowired OrderRepresentationModelAssembler orderRepresentationModelAssembler) {
        this.orderFacade = orderFacade;
        this.orderRepresentationModelAssembler = orderRepresentationModelAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders() {
        CollectionModel<EntityModel<OrderDTO>> ordersCollectionModel = orderRepresentationModelAssembler.toCollectionModel(orderFacade.getAllOrders());
        return new ResponseEntity<>(ordersCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<OrderDTO>> getUser(@PathVariable("id") long id) throws Exception {

        OrderDTO orderDTO = orderFacade.getOrderById(id);
        if (orderDTO == null) throw new ResourceNotFoundException("user " + id + " not found");
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderDTO);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<OrderDTO>> createOrder(@RequestBody @Valid OrderCreateDTO order, @RequestBody @Valid UserDTO user, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        Long id = orderFacade.createOrder(order, user.getLogin());
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderFacade.getOrderById(id));
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteUser(@PathVariable("id") long id) throws Exception {
        try {
            orderFacade.removeOrder(id);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("order " + id + " not found");
        } catch (Throwable ex) {
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
            }
            throw new ServerErrorException(rootCause.getMessage());
        }
    }

}
