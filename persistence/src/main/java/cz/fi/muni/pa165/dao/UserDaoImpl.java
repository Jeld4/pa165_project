package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Car;
import cz.fi.muni.pa165.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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

	private final static Logger log = LoggerFactory.getLogger(CarDaoImpl.class);

	@PersistenceContext
    private EntityManager em;

    @Override
	public void createUser(User user) {
        if (user == null) {
            throw new DataAccessException("Attempting to create null user."){};
        }
		em.persist(user);
	}

    @Override
	public User getUserById(Long id) {
        if (id == null) {
            throw new DataAccessException("Cannot find user, because id is null."){};
        }
		return em.find(User.class, id);
	}
	
    @Override
	public void removeUser(User user) {
        if (user == null) {
            throw new DataAccessException("Attempting to remove null user."){};
        }
		em.remove(user);
	}

    @Override
	public List<User> findAllUsers() {
		log.debug("DAO - Find all users");
        Query query = em.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
	}
    
    @Override
    public void updateUser (User user) {
		if(user == null){
			throw new IllegalArgumentException("Cannot update null user");
		}
		log.debug("DAO - Update user");
    	em.merge(user);
    }

	@Override
	public User getUserByEmail(String email) {
		if(email == null){
			throw new IllegalArgumentException("Cannot find user by null email");
		}
		log.debug("DAO - Get user by email");
    	return em.find(User.class, email);
	}

	@Override
	public User getUserByLogin(String login) {
		if(login == null){
			throw new IllegalArgumentException("Cannot user by null login");
		}
		log.debug("DAO - Find user by login");
		return em.createQuery("SELECT u FROM User u WHERE u.login=:login", User.class)
				.setParameter("login", login)
				.getSingleResult();
	}

	@Override
	public void addCar(Long userId, Long carId) {
		if(userId == null){
			throw new IllegalArgumentException("Cannot add car with null userid");
		}
		if(carId == null){
			throw new IllegalArgumentException("Cannot add car with null carid");
		}
		log.debug("DAO - Add car");
        User user = getUserById(userId);
        Car car = em.find(Car.class, carId);
        user.getCars().add(car);
        updateUser(user);
		
	}

	@Override
	public void removeCar(Long userId, Long carId) {
		if(userId == null){
			throw new IllegalArgumentException("Cannot remove car with null userid");
		}
		if(carId == null){
			throw new IllegalArgumentException("Cannot remove car with null carid");
		}
		log.debug("DAO - Remove car");
		User user = getUserById(userId);
        Car car = em.find(Car.class, carId);
        user.getCars().remove(car);
        updateUser(user);
	}
	

}