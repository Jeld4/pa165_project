package cz.fi.muni.pa165.facade;

import java.util.List;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;

public interface UserFacade {
    
	/**
	 * creates a new User and return its Id
	 * @param user
	 * @return
	 */
	Long createUser(UserCreateDTO user);
    
	/**
	 * Delete User By given ID
	 * @param userId
	 */
	void deleteUser(Long userId);
    
	
	/**
	 * Get All Users
	 * @return
	 */
	List<UserDTO> getAllUsers();
    
	/**
	 * Gets user with given ID
	 * @param id
	 * @return
	 */
	UserDTO getUserWithId(Long id);
    
	/**
	 * Gets user with given login
	 * @param login uesrs login
	 * @return user by login
	 */
	UserDTO getUserWithLogin(String login);
	
	/**
	 * Adds order to user
	 * @param userId
	 * @param orderId
	 */
	void addOrderToUser(Long userId, Long orderId);
	

	/**
	 * Remove order to user
	 * @param userId
	 * @param orderId
	 */
	void removeOrderFromUser(Long userId, Long orderId);
}
