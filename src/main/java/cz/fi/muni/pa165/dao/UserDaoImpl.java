package cz.fi.muni.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.fi.muni.pa165.entity.Customer;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.User;

public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

	public Customer createCustomer(Customer customer) {
		em.persist(customer);
		return null;
	}

	public Employee createEmployee(Employee employee) {
		em.persist(employee);
		return null;
	}

	public Customer findCustomer(long id) {
		return em.find(Customer.class, id);
	}

	public Employee findEmployee(long id) {
		return em.find(Employee.class, id);
	}
	
	public User findUser(long id) {
		return em.find(User.class, id);
	}

	public void removeUser(long id) {
		em.remove(findUser(id));
	}
	
	public void removeUser(User user) {
		em.remove(user);
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
