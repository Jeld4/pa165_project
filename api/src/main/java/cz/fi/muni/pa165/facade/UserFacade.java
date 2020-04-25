package cz.fi.muni.pa165.facade;

import java.util.List;

import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;

public interface UserFacade {
    
	Long createUser(UserCreateDTO user);
    
	void deleteUser(Long userId);
    
	List<UserDTO> getAllUsers();
    
	UserDTO getUserWithId(Long id);
    
	UserDTO getUserWithLogin(String login);

	UserDTO getUserWithEmail(String email);
    
}
