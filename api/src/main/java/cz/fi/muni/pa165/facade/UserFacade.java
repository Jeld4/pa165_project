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
	 * Adds car to user
	 * @param userId
	 * @param carId
	 */
	void addCarToUser(Long userId, Long carId);
	

	/**
	 * Remove car to user
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
