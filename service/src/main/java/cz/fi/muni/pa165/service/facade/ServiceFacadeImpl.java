package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.ServiceCreateDTO;
import cz.fi.muni.pa165.dto.ServiceDTO;
import cz.fi.muni.pa165.entity.Service;
import cz.fi.muni.pa165.facade.ServiceFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
@org.springframework.stereotype.Service
@Transactional
public class ServiceFacadeImpl implements ServiceFacade {

    @Inject
    private ServiceService serviceService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long crateService(ServiceCreateDTO serviceCreateDTO) {
        Service newService = new cz.fi.muni.pa165.entity.Service();
        newService.setName(serviceCreateDTO.getName());
        newService.setDescription(serviceCreateDTO.getDescription());
        newService.setPrice(serviceCreateDTO.getPrice());

        serviceService.create(newService);

        return newService.getId();
    }

    @Override
    public void deleteService(Long serviceId) {
        serviceService.remove(serviceService.findById(serviceId));
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        return beanMappingService.mapTo(serviceService.findAll(), ServiceDTO.class);
    }

    @Override
    public ServiceDTO getServiceWithId(Long id) {
        return beanMappingService.mapTo(serviceService.findById(id), ServiceDTO.class);
    }
}
