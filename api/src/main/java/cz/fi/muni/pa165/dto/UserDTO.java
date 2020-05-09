package cz.fi.muni.pa165.dto;

import java.util.List;

import cz.fi.muni.pa165.entity.Order;

/**
 * @author Michal Klíma
 */
public class UserDTO {

	private Long id;
	private String name;
	private String login;
	private String password;
	private Boolean isAdmin;
	
	private List<Order> orders;
	
	/**
	 * Gets User's ID
	 * @return
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets User's ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets User's Name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets User's Name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets User's Login
	 * @return
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Sets User's Login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Gets User's Password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets User's Password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** Gets User's Admin's Assess
	 * 
	 * @return
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
	/**
	 * sets User Admin's Access
	 * @param isAdmin
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	/**
	 * Gets User's orders
	 * @return
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets User's orders
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAdmin == null) ? 0 : isAdmin.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAdmin == null) {
			if (other.isAdmin != null)
				return false;
		} else if (!isAdmin.equals(other.isAdmin))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}


}
