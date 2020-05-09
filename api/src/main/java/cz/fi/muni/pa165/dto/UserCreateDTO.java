package cz.fi.muni.pa165.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
    public List<OrderDTO> getOrders() {
		return orders;
	}


	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
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
				Objects.equals(orders, that.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, login, password, isAdmin, orders);
	}

	@Override
	public String toString() {
		return "UserCreateDTO{" +
				"name='" + name + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", isAdmin=" + isAdmin +
				", orders=" + orders +
				'}';
	}
}
