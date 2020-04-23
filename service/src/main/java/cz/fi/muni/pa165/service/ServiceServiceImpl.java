package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.ServiceDao;
import cz.fi.muni.pa165.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;


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
        return serviceDao.findById(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceDao.findAll();
    }

    @Override
    public void create(Service service) {
        serviceDao.create(service);
    }

    @Override
    public void remove(Service service) {
        serviceDao.remove(service);
    }

}
