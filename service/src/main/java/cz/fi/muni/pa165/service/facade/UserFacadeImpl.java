package cz.fi.muni.pa165.service.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.UserService;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;
	
	@Override
	public Long createUser(UserCreateDTO user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        } if (user.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Users login cannot be empty.");
        } if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Users name cannot be empty.");
        } if (user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Users passwor cannot be empty.");
        }
		
        User newUser = new User();
        
        newUser.setIsAdmin(user.getIsAdmin());
        newUser.setLogin(user.getLogin());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        
        userService.create(newUser);
        return  newUser.getId();
	}

	@Override
	public void deleteUser(Long userId) {
		userService.remove(userService.findById(userId));
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return beanMappingService.mapTo(userService.findAll(), UserDTO.class);
	}

	@Override
	public UserDTO getUserWithId(Long id) {
        User foundUser = userService.findById(id);
        return (foundUser == null) ? null : beanMappingService.mapTo(foundUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserWithLogin(String login) {
        User foundUser = userService.findByLogin(login);
        return (foundUser == null) ? null : beanMappingService.mapTo(foundUser, UserDTO.class);
	}

	@Override
	public void addCarToUser(Long userId, Long carId) {
		userService.addCarToUser(userId, carId);	
	}

	@Override
	public void removeCarFromUser(Long userId, Long carId) {
		userService.removeCarFromUser(userId, carId);
	}

	@Override
	public boolean checkPassword(Long id, String password) {
		return userService.checkPassword(id, password);
	}


} 
