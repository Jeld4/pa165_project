package cz.fi.muni.pa165.dao;

import java.util.List;

import cz.fi.muni.pa165.entity.Customer;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.User;

/**
 * @author Michal Klíma
 */

public interface UserDao {
	
    /**
     * Method to create a new Customer.
     * @param customer
     */
	void createCustomer(Customer customer);
	
    /**
     * Method to create a new Employee.
     * @param employee
     */
	void createEmployee(Employee employee);
	
    /**
     * Method to create a new User.
     * @param employee
     */
	void createUser(User user);
	
	
    /**
     * Method to find a Customer by id.
     * @param id
     */
	Customer findCustomer(long id);
	
    /**
     * Method to find a Employee by id.
     * @param id
     */
	Employee findEmployee(long id);
	
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
     * Method to find all Customers
     */
	List<Customer> findAllCustomers();
	 
	/**
     * Method to find all Employees
     */
	List<Employee> findAllEmployees();
	
	 /**
     * Method to find all Users
     */
	List<User> findAllUsers();
}
