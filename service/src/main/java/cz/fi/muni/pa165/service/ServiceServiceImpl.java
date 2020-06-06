package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.ServiceDao;
import cz.fi.muni.pa165.entity.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
/**
 * @author Radim Sasinka. 456315
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    private final static Logger log = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Override
    public Service findById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Service ID cannot be null");
        }

        Service service = null;

        try {
            service = serviceDao.findById(id);

        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("Service - find service with ID {}", id);
        return service;
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = null;
        try {
            services = serviceDao.findAll();
        } catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("Service - find all cars");
        return services;
    }
    @Override
    public void create(Service service) {

        if(service == null){
            throw new IllegalArgumentException("Service object cannot be null");
        }

        try {
            serviceDao.create(service);
            log.debug("Service - Create car");
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Service service) {
        if(service == null){
            throw new IllegalArgumentException("Service object cannot be null");
        }
        try {
            serviceDao.remove(service);
            log.debug("Service - Remove car");
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

}
