package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.OrderController;
import cz.fi.muni.pa165.dto.OrderDTO;
import cz.fi.muni.pa165.exceptions.FailToLinkHateosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Jan Jelínek 445416
 */
@Component
public class OrderRepresentationModelAssembler implements RepresentationModelAssembler<OrderDTO, EntityModel<OrderDTO>> {

    private final static Logger log = LoggerFactory.getLogger(OrderRepresentationModelAssembler.class);

    @Override
    public EntityModel<OrderDTO> toModel(OrderDTO orderDTO) {
        long id = orderDTO.getId();
        EntityModel<OrderDTO> orderResource = new EntityModel<>(orderDTO);
        try {
            orderResource.add(linkTo(OrderController.class).slash(orderDTO.getId()).withSelfRel());
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
            throw new FailToLinkHateosException("Unable to link HATEOAS for OrderDTO");
        }
        return orderResource;
    }
}
