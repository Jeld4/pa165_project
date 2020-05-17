package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.Order;
import cz.fi.muni.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Michal Klíma
 */

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
	public void createUser(User user) {
		em.persist(user);
	}

    @Override
	public User getUserById(long id) {
		return em.find(User.class, id);
	}
	
    @Override
	public void removeUser(User user) {
		em.remove(user);
	}


    @Override
	public List<User> findAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
	}
    
    @Override
    public void updateUser (User user) {
        em.merge(user);
    }

	@Override
	public User getUserByEmail(String email) {
		return em.find(User.class, email);
	}

	@Override
	public User getUserByLogin(String login) {
		return em.createQuery("SELECT u FROM User u WHERE u.login=:login", User.class)
				.setParameter("login", login)
				.getSingleResult();
	}

	@Override
	public void addCar(Long userId, Long carId) {
        User user = getUserById(userId);
        Car car = em.find(Car.class, carId);
        user.getCars().add(car);
        updateUser(user);
		
	}

	@Override
	public void removeCar(Long userId, Long carId) {
		User user = getUserById(userId);
        Car car = em.find(Car.class, carId);
        updateUser(user);
	}
	

}