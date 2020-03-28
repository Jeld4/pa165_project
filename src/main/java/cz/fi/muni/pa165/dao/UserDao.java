package cz.fi.muni.pa165.dao;

import java.util.List;

import cz.fi.muni.pa165.entity.Customer;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.User;

public interface UserDao {
	Customer createCustomer(Customer customer);
	Employee createEmployee(Employee employee);
	
	Customer findCustomer(long id);
	Employee findEmployee(long id);
	User findUser(long id);
	
	void removeUser(long id);
	
	List<Customer> getAllCustomers();
	List<Employee> getAllEmployees();
	List<User> getAllUsers();
}
