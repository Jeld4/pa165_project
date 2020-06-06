package cz.fi.muni.pa165.dto;

import java.util.List;
import java.util.Objects;

import cz.fi.muni.pa165.entity.Car;

/**
 * @author Michal Klíma
 */
public class UserDTO {

	private Long id;
	private String name;
	private String login;
	private String password;
	private Boolean isAdmin;
	private String userAddress;
	private String telephone;
	
	private List<Car> cars;
	
	/**
	 * Gets User's ID
	 * @return id of user
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets User's ID
	 * @param id of user
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets User's Name
	 * @return user's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets User's Name
	 * @param name of user
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets User's Login
	 * @return login of user
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Sets User's Login
	 * @param login of user
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Gets User's Password
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets User's Password
	 * @param password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets User's Admin's Assess
	 * @return wheter the user is admin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
	/**
	 * sets User Admin's Access
	 * @param isAdmin wheter the user is admin
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * Gets User's cars
	 * @return user's cars
	 */
	public List<Car> getCars() {
		return cars;
	}

	/**
	 * Sets User's cars
	 * @param cars of the user
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	/**
	 * Gets User's Address
	 * @param cars of the user
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * Sets User's address
	 * @param cars of the user
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * Gets User's telephone
	 * @param cars of the user
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets User's telephone
	 * @param cars of the user
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(id, userDTO.id) &&
				Objects.equals(name, userDTO.name) &&
				Objects.equals(login, userDTO.login) &&
				Objects.equals(password, userDTO.password) &&
				Objects.equals(isAdmin, userDTO.isAdmin) &&
				Objects.equals(cars, userDTO.cars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password, isAdmin, cars);
	}

	@Override
	public String toString() {
		return name;
	}

	
	
}
