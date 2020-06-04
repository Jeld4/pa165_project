package cz.fi.muni.pa165.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Michal Klíma
 */
public class UserCreateDTO {
	
	@NotBlank
    @Size(min = 2, max=32)	
	private String name;
    
	@NotBlank
    @Size(min = 3, max=32)
	private String login;

    
	@NotBlank
    @Size(min = 3, max=32)
	private String password;

	private String userAddress;

	private String telephone;

    
	private Boolean isAdmin;
	private List<CarDTO> cars;

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
    public List<CarDTO> getCars() {
		return cars;
	}

	/**
	 * Sets User's cars
	 * @param cars of the user
	 */
	public void setCars(List<CarDTO> cars) {
		this.cars = cars;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserCreateDTO that = (UserCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(login, that.login) &&
				Objects.equals(password, that.password) &&
				Objects.equals(isAdmin, that.isAdmin) &&
				Objects.equals(cars, that.cars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, login, password, isAdmin, cars);
	}

	@Override
	public String toString() {
		return "UserCreateDTO{" +
				"name='" + name + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", isAdmin=" + isAdmin +
				", cars=" + cars +
				'}';
	}
}
