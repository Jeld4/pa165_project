package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Michal Kl�ma
 */

@Entity
@Table(name = "Employee")
public class Employee extends User {

	public Employee(String n, String l, String p) {
		super(n, l, p);
	}
	// ToDo think what Employee will do
}
