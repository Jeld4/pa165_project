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

    /**
     *  Controls passwords against database
     * @param password given password
     * @param id ID of user
     * @return True if passwords is ok, False otherwise
     */
    boolean checkPassword(Long id, String password);

}
