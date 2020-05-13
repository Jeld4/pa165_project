package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.TireDTO;
import cz.fi.muni.pa165.dto.TireCreateDTO;

import java.util.List;

/**
 * @author Jakub Malý, 456389
 */
public interface TireFacade {

    /**
     * Creates tire in the system
     * @param tire Object of TireCreateDTO type
     * @return Long tire
     */
    Long createTire(TireCreateDTO tire);

    /**
     * Deletes tire from the system
     * @param tireId ID number of the tire
     */
    void deleteTire(Long tireId);

    /**
     * Returns all tires in the system
     * @return List of tires
     */
    List<TireDTO> getAllTires();

    /**
     * Returns tire with specific id
     * @param id of the tire
     * @return TireDTO tire
     */
    TireDTO getTireWithId(Long id);

    /**
     * Returns tires of given manufacturer
     * @param String manufacturer name
     * @return list of tires
     */
    List<TireDTO> getTiresByManufacturer(String manufacturer);
}
