package cz.fi.muni.pa165.hateoas;

import cz.fi.muni.pa165.controllers.CarController;
import cz.fi.muni.pa165.dto.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Radim Sasinka, 456315
 */
@Component
public class CarRepresentationModelAssembler implements RepresentationModelAssembler<CarDTO, EntityModel<CarDTO>> {

    private final static Logger log = LoggerFactory.getLogger(CarRepresentationModelAssembler.class);

    @Override
    public EntityModel<CarDTO> toModel(CarDTO carDTO) {
        long id = carDTO.getId();
        EntityModel<CarDTO> carResource = new EntityModel<>(carDTO);
        try {
            carResource.add(linkTo(CarController.class).slash(carDTO.getId()).withSelfRel());

            Method deleteCar = CarController.class.getMethod("deleteCar", long.class);
            carResource.add(linkTo(deleteCar.getDeclaringClass(),deleteCar, id).withSelfRel());

        }catch (Exception ex){
            log.error("cannot link HATEOAS", ex);
        }
        return carResource;
    }
}
