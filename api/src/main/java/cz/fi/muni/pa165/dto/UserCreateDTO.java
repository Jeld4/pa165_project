package cz.fi.muni.pa165.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Michal Klíma
 */
public class UserCreateDTO {
	
	@NotNull
    @Size(min = 2, max=32)	
	private String name;
    
    @NotNull
    @Size(min = 8, max=32)
	private String login;

    
    @NotNull
    @Size(min = 8, max=32)
	private String password;
    
    
	private Boolean isAdmin;
	private List<OrderDTO> orders;

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
	 * Gets User's orders
	 * @return user's orders
	 */
    public List<OrderDTO> getOrders() {
		return orders;
	}

	/**
	 * Sets User's orders
	 * @param orders of the user
	 */
	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		UserCreateDTO other = (UserCreateDTO) obj;
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




}
