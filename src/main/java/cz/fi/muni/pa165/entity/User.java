package cz.fi.muni.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
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
}
