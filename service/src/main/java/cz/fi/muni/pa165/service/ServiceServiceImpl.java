package cz.fi.muni.pa165.service;

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
        try {
            serviceDao.create(service);
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Service service) {
        try {
            serviceDao.remove(service);
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

}
