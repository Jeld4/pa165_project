package cz.fi.muni.pa165.service;

import java.util.List;

import javax.inject.Inject;

import cz.fi.muni.pa165.dao.UserDao;
import cz.fi.muni.pa165.entity.User;

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
	public User findByEmail(String email) {
	     return userDao.getUserByEmail(email);
	}

	@Override
	public User findByLogin(String login) {
	     return userDao.getUserByLogin(login);
	}

	@Override
	public void create(User user) {
		userDao.createUser(user);
		
	}

	@Override
	public void remove(User user) {
		userDao.removeUser(user);
		
	}

}
