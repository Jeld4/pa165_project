package cz.fi.muni.pa165.service;

import java.util.List;

import cz.fi.muni.pa165.entity.User;
/**
 * 
 * @author Michal KLíma
 *
 */
public interface UserService {
    /**
     * Return User by id
     * @param id
     */
	User findById(Long id);

	/**
	 * return all Users
	 * @return
	 */
    List<User> findAll();
    
    /**
     * Return User By login
     * @param login
     * @return
     */
    User findByLogin(String login);
    
    /**
     * Creates User
     * @param user
     */
    void create(User user);
    
    /**
     * remove User
     * @param user
     */
    void remove(User user);
    
    /**
     * add Order to user
     * @param userId
     * @param orderId
     */
    void addOrderToUser(Long userId, Long orderId);
  
    /**
     * add Order to user
     * @param userId
     * @param orderId
     */
    void removeOrderFromUser(Long userId, Long orderId);
    
    /**
     * add Car to user
     * @param userId
     * @param carId
     */
    void addCarToUser(Long userId, Long carId);
  
    /**
     * add Car to user
     * @param userId
     * @param carId
     */
    void removeCarFromUser(Long userId, Long carId);
}
