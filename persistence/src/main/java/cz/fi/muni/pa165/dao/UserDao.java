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
	User findUser(long id);
	
	
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
	void updateUser(User u);

}