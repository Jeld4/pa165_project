package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.facade.ServiceFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Radim Sasinka
 */

@org.springframework.stereotype.Service
@Transactional
public class ServiceFacadeImpl implements ServiceFacade {

    @Inject
    private ServiceService serviceService;

    @Autowired
    private BeanMappingService beanMappingService;


    private final static Logger log = LoggerFactory.getLogger(ServiceFacadeImpl.class);


    @Override
    public Long createService(ServiceCreateDTO serviceCreateDTO) {

        if(serviceCreateDTO == null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        if(serviceCreateDTO.getName().isEmpty()){
            throw new IllegalArgumentException("Service name cannot be empty");
        }
        if(serviceCreateDTO.getDescription().isEmpty()){
            throw new IllegalArgumentException("Service description cannot be empty");
        }
        if(serviceCreateDTO.getPrice() == null){
            throw new IllegalArgumentException("Service price cannot be null");
        }

        log.debug("Facade - Create Service");
        Service newService = new cz.fi.muni.pa165.entity.Service();
        newService.setName(serviceCreateDTO.getName());
        newService.setDescription(serviceCreateDTO.getDescription());
        newService.setPrice(serviceCreateDTO.getPrice());

        serviceService.create(newService);

        return newService.getId();
    }

    @Override
    public void deleteService(Long serviceId) {
        if(serviceId == null){
            throw new IllegalArgumentException("Service ID cannot be null");
        }
        log.debug("Facade - deleting service with ID {}",serviceId);
        serviceService.remove(serviceService.findById(serviceId));
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        log.debug("Facade - get all services");
        return beanMappingService.mapTo(serviceService.findAll(), ServiceDTO.class);
    }

    @Override
    public ServiceDTO getServiceWithId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Service ID cannot be null");
        }
        log.debug("Facade - get service with ID {}", id);
        Service service = serviceService.findById(id);
        return (service == null) ? null : beanMappingService.mapTo(serviceService.findById(id), ServiceDTO.class);
    }
}
