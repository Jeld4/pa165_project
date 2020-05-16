package cz.fi.muni.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @author Michal KLíma
 *
 */
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

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
	public void addOrderToUser(Long userId, Long orderId) {
		userDao.addOrder(userId, orderId);
		
	}

	@Override
	public void removeOrderFromUser(Long userId, Long orderId) {
		userDao.removeOrder(userId, orderId);
		
	}
	
	@Override
	public void addCarToUser(Long userId, Long carId) {
		userDao.addCar(userId, carId);
		
	}

	@Override
	public void removeCarFromUser(Long userId, Long carId) {
		userDao.removeCar(userId, carId);
		
	}
	
	

}
