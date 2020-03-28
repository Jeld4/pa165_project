package cz.fi.muni.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cz.fi.muni.pa165.entity.Customer;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Michal Klíma
 */

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
	public void createCustomer(Customer customer) {
		em.persist(customer);
	}

    @Override
	public void createEmployee(Employee employee) {
		em.persist(employee);
	}
    
    @Override
	public void createUser(User user) {
		em.persist(user);
	}

    @Override
	public Customer findCustomer(long id) {
		return em.find(Customer.class, id);
	}

    @Override
	public Employee findEmployee(long id) {
		return em.find(Employee.class, id);
	}
	
    @Override
	public User findUser(long id) {
		return em.find(User.class, id);
	}
	
    @Override
	public void removeUser(User user) {
		em.remove(user);
	}

    @Override
	public List<Customer> findAllCustomers() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        return (List<Customer>) query.getResultList();
	}

    @Override
	public List<Employee> findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (List<Employee>) query.getResultList();
	}

    @Override
	public List<User> findAllUsers() {
        Query query = em.createQuery("SELECT u FROM Users u");
        return (List<User>) query.getResultList();
	}

}
