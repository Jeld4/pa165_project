package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.facade.UserFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michal Klíma, 456234	
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    private final static Logger log = LoggerFactory.getLogger(ServiceFacadeImpl.class);

	@Override
	public Long createUser(UserCreateDTO user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        } if (user.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Users login cannot be empty.");
        } if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Users name cannot be empty.");
        } if (user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Users password cannot be empty.");
        } if (user.getUserAddress().isEmpty()) {
            throw new IllegalArgumentException("Users address cannot be empty.");
        } if (user.getTelephone().isEmpty()) {
            throw new IllegalArgumentException("Users telephone cannot be empty.");
        }
		
        User newUser = new User();

        log.debug("Facade - Create User");

        newUser.setIsAdmin(user.getIsAdmin());
        newUser.setLogin(user.getLogin());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setUserAddress(user.getUserAddress());
        newUser.setTelephone(user.getTelephone());
        
        userService.create(newUser);
        return  newUser.getId();
	}

	@Override
	public void deleteUser(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        log.debug("Facade - deleting user with ID {}", userId);
	    userService.remove(userService.findById(userId));
	}

	@Override
	public List<UserDTO> getAllUsers() {
        log.debug("Facade - get all users");
	    return beanMappingService.mapTo(userService.findAll(), UserDTO.class);
	}

	@Override
	public UserDTO getUserWithId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        log.debug("Facade - get user with ID {}", id);
        User foundUser = userService.findById(id);
        return (foundUser == null) ? null : beanMappingService.mapTo(foundUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserWithLogin(String login) {
        if(login == null){
            throw new IllegalArgumentException("User login cannot be null");
        }
        log.debug("Facade - get user with login {}", login);
        User foundUser = userService.findByLogin(login);
        return (foundUser == null) ? null : beanMappingService.mapTo(foundUser, UserDTO.class);
	}

	@Override
	public void addCarToUser(Long userId, Long carId) {
        if(userId == null){
            throw new IllegalArgumentException("Cannot add car with null userid");
        }
        if(carId == null){
            throw new IllegalArgumentException("Cannot add car with null carid");
        }
        log.debug("Facade - Add car");
	    userService.addCarToUser(userId, carId);
	}

	@Override
	public void removeCarFromUser(Long userId, Long carId) {
        if(userId == null){
            throw new IllegalArgumentException("Cannot remove car with null userid");
        }
        if(carId == null){
            throw new IllegalArgumentException("Cannot remove car with null carid");
        }
        log.debug("Facade - Remove car");
	    userService.removeCarFromUser(userId, carId);
	}

	@Override
	public boolean checkPassword(Long id, String password) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(password == null){
            throw new IllegalArgumentException("password cannot be null");
        }
        log.debug("Facade - Check password");
		return userService.checkPassword(id, password);
	}


} 
