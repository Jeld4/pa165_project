package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;

import java.util.List;
/**
 * @author Radim Sasinka
 */
public interface ServiceFacade {

    /**
     * Creates new service in the system
     * @param serviceCreateDTO service to be created
     * @return Long ID of the created service
     */
    Long createService(ServiceCreateDTO serviceCreateDTO);

    /**
     * Deletes service from the system
     * @param id ID of service to be deleted
     */
    void deleteService(Long id);

    /**
     * Returns a list of all available services in the system
     * @return List of services
     */
    List<ServiceDTO> getAllServices();

    /**
     * Returns a services according to given ID
     * @param id ID of service to be found
     * @return ServiceDTO
     */
    ServiceDTO getServiceWithId(Long id);
}
