package cz.fi.muni.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Michal Klíma
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	@Inject
    private UserDao userDao;

	
	@Override
	public User findById(Long id) {
	     if(id == null){
	            throw new IllegalArgumentException("User ID cannot be null");
	        }

	        cz.fi.muni.pa165.entity.User user = null;

	        try {
	            user = userDao.getUserById(id);

	        }catch (DataAccessException ex){
	            throw new RuntimeException(ex);
	        }
	        log.debug("User service - find tire with ID {}", id);
	        return user;
	}

	@Override
	public List<User> findAll() {
        List<User> users = null;
        try {
            users = userDao.findAllUsers();
        } catch (DataAccessException ex){
            throw new RuntimeException(ex);
        }
        log.debug("User service - find all users");
        return users;
	}

	@Override
	public User findByLogin(String login) {
		if(login == null){
			throw new IllegalArgumentException("login cannot be null");
		}
		log.debug("User service - find all user by login");
		return userDao.getUserByLogin(login);
	}

	@Override
	public void create(User user) {
		if(user == null){
			throw new IllegalArgumentException("user cannot be null");
		}
		log.debug("User service - create user");
		user.setPassword(encryptPassword(user.getPassword()));
		userDao.createUser(user);
		
	}

	private String encryptPassword(String password) {
		if(password == null){
			throw new IllegalArgumentException("password cannot be null");
		}
		log.debug("User service - encrypt password");
		return DigestUtils.md2Hex(password).toUpperCase();
	}

	@Override
	public void remove(User user) {
		if(user == null){
			throw new IllegalArgumentException("user cannot be null");
		}
		log.debug("User service - remove user");
		userDao.removeUser(user);
		
	}

	@Override
	public void addCarToUser(Long userId, Long carId) {
		if(userId == null){
			throw new IllegalArgumentException("Cannot add car with null userid");
		}
		if(carId == null){
			throw new IllegalArgumentException("Cannot add car with null carid");
		}
		log.debug("User service - Add car");
		userDao.addCar(userId, carId);
	}

	@Override
	public void removeCarFromUser(Long userId, Long carId) {
		if(userId == null){
			throw new IllegalArgumentException("Cannot remove car with null userid");
		}
		if(carId == null){
			throw new IllegalArgumentException("Cannot remove car with null carid");
		}
		log.debug("User service - Remove car");
		userDao.removeCar(userId, carId);
	}

	@Override
	public boolean checkPassword(Long id, String password) {
		if (id != null && !password.isEmpty()) {
			log.error("CHECKING PASSWORDS");
			return DigestUtils.md2Hex(password).toUpperCase().equals(this.findById(id).getPassword().toUpperCase());
		}
		return false;
	}


}
