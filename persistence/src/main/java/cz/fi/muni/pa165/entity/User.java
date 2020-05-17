package cz.fi.muni.pa165.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

/**
 * class representing user
 * @author Michal Klima
 */
@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String login;
	private String password;
	private Boolean isAdmin;

    @OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
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
	 */
	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
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
				Objects.equals(cars, user.cars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password, isAdmin, cars);
	}
}
