package dao;

import java.util.List;

import entity.Customer;
import entity.Employee;
import entity.User;

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
