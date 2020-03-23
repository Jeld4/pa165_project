package dao;

import entity.Service;

import java.util.List;

/**
 * @author Radim Sasinka, 456315
 */

public interface ServiceDao {

    void create(Service s);

    List<Service> findAll();

    Service findById(Long id);

    void remove(Service s);
}
