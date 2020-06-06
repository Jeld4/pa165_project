package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.TireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
@Service
@Transactional
public class TireFacadeImpl implements TireFacade {

    private final static Logger log = LoggerFactory.getLogger(ServiceFacadeImpl.class);

    @Inject
    private TireService tireService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTire(TireCreateDTO tire) {
        if(tire == null){
            throw new IllegalArgumentException("Tire cannot be null");
        }
        if(tire.getManufacturer().isEmpty()){
            throw new IllegalArgumentException("Tire manufacturer cannot be empty");
        }
        if(tire.getSeason().isEmpty()){
            throw new IllegalArgumentException("Tire season cannot be empty");
        }
        if(tire.getType().isEmpty()){
            throw new IllegalArgumentException("Tire type cannot be null");
        }

        log.debug("Facade - Create Tire");

        Tire newTire = new cz.fi.muni.pa165.entity.Tire();
        newTire.setManufacturer(tire.getManufacturer());
        newTire.setType(tire.getType());
        newTire.setPrice(tire.getPrice());
        newTire.setSize(tire.getSize());
        newTire.setSeason(tire.getSeason());

        tireService.create(newTire);

        return newTire.getId();
    }

    @Override
    public void deleteTire(Long tireId) {
        if(tireId == null){
            throw new IllegalArgumentException("Tire ID cannot be null");
        }
        log.debug("Facade - deleting tire with ID {}", tireId);
        tireService.remove(tireService.findById(tireId));
    }

    @Override
    public List<TireDTO> getAllTires() {
        log.debug("Facade - get all tires");
        return beanMappingService.mapTo(tireService.findAll(), TireDTO.class);
    }

    @Override
    public TireDTO getTireWithId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Tire ID cannot be null");
        }
        log.debug("Facade - get tire with ID {}", id);
        Tire tire = tireService.findById(id);
        return (tire == null) ? null : beanMappingService.mapTo(tireService.findById(id), TireDTO.class);
    }

    @Override
    public List<TireDTO> getTiresByManufacturer(String manufacturer) {
        if(manufacturer == null){
            throw new IllegalArgumentException("Tire manufacturer cannot be null");
        }
        log.debug("Facade - get tire with manufacturer {}", manufacturer);
        return beanMappingService.mapTo(tireService.findByManufacturer(manufacturer), TireDTO.class);
    }
}
