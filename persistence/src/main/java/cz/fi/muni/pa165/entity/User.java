package cz.fi.muni.pa165.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class representing user
 * @author Michal Klima
 */
@Entity
@Table(name = "Users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min = 2, max=32)
	private String name;

	@NotEmpty
	@Size(min = 5, max=32)
	private String login;

	@NotEmpty
	@Size(min = 5, max=32)
	private String password;

	private Boolean isAdmin;

	@NotEmpty
	private String userAddress;

	@NotEmpty
	private String telephone;

    @OneToMany
	private List<Car> cars;


	/**
	 * user nonparametric constructor
	 */
	public User() {}

	/**
	 * user parametric constructor
	 * @param name
	 * @param login
	 * @param password
	 * @param userAddress
	 * @param telephone
	 */
	public User(String name, String login, String password, String userAddress, String telephone) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.userAddress = userAddress;
		this.telephone = telephone;
	}

	/**
	 * get ID
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * set ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get login
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * set login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * get password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get isAdmin
	 * @return isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * set isAdmin
	 * @param isAdmin
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * get cars
	 * @return orders
	 */
	public List<Car> getCars() {
		return cars;
	}


	/**
	 * set cars
	 * @param cars
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
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
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(name, user.name) &&
				Objects.equals(login, user.login) &&
				Objects.equals(password, user.password) &&
				Objects.equals(isAdmin, user.isAdmin) &&
				Objects.equals(userAddress, user.userAddress) &&
				Objects.equals(telephone, user.telephone) &&
				Objects.equals(cars, user.cars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password, isAdmin, userAddress, telephone, cars);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", isAdmin=" + isAdmin +
				", userAddress='" + userAddress + '\'' +
				", telephone='" + telephone + '\'' +
				", cars=" + cars +
				'}';
	}
}
