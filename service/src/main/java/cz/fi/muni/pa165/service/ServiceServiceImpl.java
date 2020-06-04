package cz.fi.muni.pa165.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import cz.fi.muni.pa165.dao.ServiceDao;
import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Service;
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
        return services;
    }
    @Override
    public void create(Service service) {

        if(service == null){
            throw new IllegalArgumentException("Service object cannot be null");
        }

        try {
            serviceDao.create(service);
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
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

}
