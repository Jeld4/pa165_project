package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.TireCreateDTO;
import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.facade.TireFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.TireService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
@org.springframework.stereotype.Service
@Transactional
public class TireFacadeImpl implements TireFacade {

    @Inject
    private TireService tireService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTire(TireCreateDTO tire) {
        Tire newTire = new cz.fi.muni.pa165.entity.Tire();
        newTire.setManufacturer(tire.getManufacturer());
        newTire.setType(tire.getType());
        newTire.setPrice(tire.getPrice());

        tireService.create(newTire);

        return newTire.getId();
    }

    @Override
    public void deleteTire(Long tireId) {
        tireService.remove(tireService.findById(tireId));
    }

    @Override
    public List<TireDTO> getAllTires() {
        return beanMappingService.mapTo(tireService.findAll(), TireDTO.class);
    }

    @Override
    public TireDTO getTireWithId(Long id) {
        return beanMappingService.mapTo(tireService.findById(id), TireDTO.class);
    }

    @Override
    public List<TireDTO> getTiresByManufacturer(String manufacturer) {
        return beanMappingService.mapTo(tireService.findByManufacturer(manufacturer), TireDTO.class);
    }
}
