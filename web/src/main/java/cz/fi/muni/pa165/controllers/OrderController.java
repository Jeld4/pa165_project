package cz.fi.muni.pa165.controllers;

import cz.fi.muni.pa165.dto.OrderCreateDTO;
import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.exceptions.InvalidRequestException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<OrderDTO>> createOrder(@RequestBody @Valid OrderCreateDTO order, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()) {
            throw new InvalidRequestException("Failed validation");
        }
        Long id = orderFacade.createOrder(order, "admin");
        EntityModel<OrderDTO> orderModel = orderRepresentationModelAssembler.toModel(orderFacade.getOrderById(id));
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

}
