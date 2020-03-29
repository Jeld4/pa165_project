package cz.fi.muni.pa165.entity;


/**
 * @author Michal Klima
 */
public class Employee extends User {
	
	public Employee() {
		super();
	}
	
	public Employee(String n, String l, String p) {
		super(n, l, p);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
