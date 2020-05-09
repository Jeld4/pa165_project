package cz.fi.muni.pa165.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Michal Klima
 */
@Entity
@Table(name = "\"User\"")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String login;
	private String password;
	private Boolean isAdmin;
	
    @OneToMany
	private List<Order> orders;

	public User() {
	}

	public User(String n, String l, String p) {
		name = n;
		login = l;
		password = p;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String l) {
		login = l;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setOrders (List<Order> o) {
		orders = o;
	}
	
	public List<Order> getOrders () {
		return orders;
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
				Objects.equals(orders, user.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password, isAdmin, orders);
	}
}
