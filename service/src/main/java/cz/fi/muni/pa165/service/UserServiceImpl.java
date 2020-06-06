package cz.fi.muni.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Michal KLíma
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	@Inject
    private UserDao userDao;

	
	@Override
	public User findById(Long id) {
        return userDao.getUserById(id);
	}

	@Override
	public List<User> findAll() {
        return userDao.findAllUsers();
	}

	@Override
	public User findByLogin(String login) {
	     return userDao.getUserByLogin(login);
	}

	@Override
	public void create(User user) {
		user.setPassword(encryptPassword(user.getPassword()));
		userDao.createUser(user);
		
	}

	private String encryptPassword(String password) {
		return DigestUtils.md2Hex(password).toUpperCase();
	}

	@Override
	public void remove(User user) {
		userDao.removeUser(user);
		
	}

	@Override
	public void addCarToUser(Long userId, Long carId) {
		userDao.addCar(userId, carId);
		
	}

	@Override
	public void removeCarFromUser(Long userId, Long carId) {
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
