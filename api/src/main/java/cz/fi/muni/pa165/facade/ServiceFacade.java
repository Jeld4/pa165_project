package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;

import java.util.List;

public interface ServiceFacade {

    Long createService(ServiceCreateDTO serviceCreateDTO);
    void deleteService(Long id);
    List<ServiceDTO> getAllServices();
    ServiceDTO getServiceWithId(Long id);
}
