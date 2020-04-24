package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.TireDao;
import cz.fi.muni.pa165.entity.Tire;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * @author Jakub Malý, 456389
 */
@org.springframework.stereotype.Service
public class TireServiceImpl implements TireService {

    @Autowired
    private TireDao tireDao;

    @Override
    public Tire findById(Long id) {
        return tireDao.findById(id);
    }

    @Override
    public Tire findByManufacturer(String manufacturer) {
        return tireDao.findByManufacturer(manufacturer);
    }

    @Override
    public List<Tire> findAll() {
        return tireDao.findAll();
    }

    @Override
    public void create(Tire tire) {
        tireDao.create(tire);
    }

    @Override
    public void remove(Tire tire) {
        tireDao.remove(tire);
    }

}
