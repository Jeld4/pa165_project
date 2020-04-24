package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.dto.TireCreateDTO;

import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
public interface TireFacade {

    Long createTire(TireCreateDTO tire);
    void deleteTire(Long tireId);
    List<TireDTO> getAllTires();
    TireDTO getTireWithId(Long id);
    TireDTO getTireWithManufacturer(String manufacturer);
}
