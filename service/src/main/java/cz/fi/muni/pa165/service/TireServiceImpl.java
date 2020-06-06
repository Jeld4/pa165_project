package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Tire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * @author Jakub Malý, 456389
 */
@Service
public class TireServiceImpl implements TireService {

    private final static Logger log = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Autowired
    private TireDao tireDao;

    @Override
    public Tire findById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Tire ID cannot be null");
        }

        cz.fi.muni.pa165.entity.Tire tire = null;

        try {
            tire = tireDao.findById(id);

        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("Tire service - find tire with ID {}", id);
        return tire;
    }

    @Override
    public List<Tire> findByManufacturer(String manufacturer) {
        log.debug("Tire service - find tire with manufacturer {}", manufacturer);
        return tireDao.findByManufacturer(manufacturer);
    }

    @Override
    public List<Tire> findAll() {
        List<Tire> tires = null;
        try {
            tires = tireDao.findAll();
        } catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("Tire service - find all tires");
        return tires;
    }

    @Override
    public void create(Tire tire) {
        if(tire == null){
            throw new IllegalArgumentException("Tire object cannot be null");
        }
        try {
            tireDao.create(tire);
            log.debug("Tire service - Create tire");
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void remove(Tire tire) {
        if(tire == null){
            throw new IllegalArgumentException("Tire object cannot be null");
        }
        try {
            tireDao.remove(tire);
            log.debug("Tire service - Remove tire");
        }catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
    }

}
