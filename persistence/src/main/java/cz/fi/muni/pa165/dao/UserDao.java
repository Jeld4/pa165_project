package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.User;

import java.util.List;

/**
 * @author Michal Klíma
 */

public interface UserDao {
	

    /**
     * Method to create a new User.
     * @param user
     */
	void createUser(User user);

    /**
     * Method to find a User by id.
     * @param id
     */
	User getUserById(Long id);
	
	
    /**
     * Method to remove a User.
     * @param user
     */
	void removeUser(User user);

	
	 /**
     * Method to find all Users
     */
	List<User> findAllUsers();
	
	 /**
     * Method to update User
     */
	void updateUser(User user);
	
	 /**
     * Get User by email
     */
	User getUserByEmail(String email);
	
	 /**
     * Get user by Login
     */
	User getUserByLogin(String login);
	
	/**
	 * Add car to User
	 * @param o
	 */
	void addCar(Long userId, Long carId); 

	/**
	 * Remove car from User
	 * @param o
	 */
	void removeCar(Long userId, Long carId);
	
}