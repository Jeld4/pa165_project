package entity;

/**
 * @author Michal Klíma
 */
public class User {
	
	private Long id;
	private String name;
	private String login;
	private String password;
	
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
