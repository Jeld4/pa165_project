package dao;

import entity.Order;

import java.util.List;

/**
 * @author Jan Jelínek
 */
public interface OrderDao {

    void create(Order p);

    List<Order> findAll();

    Order findById(Long id);

    void remove(Order p);
}