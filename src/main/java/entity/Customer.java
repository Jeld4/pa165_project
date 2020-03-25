package entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Michal Klíma
 */

@Entity
@Table(name = "Customer")
public class Customer extends User {
	
	public Customer(String n, String l, String p, String a, String tn) {
		super(n, l, p);
		listOfOrders = new ArrayList<Order>();
		address = a;
		telephoneNumber = tn;
	}

	
	@OneToMany(mappedBy="id", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	private List<Order> listOfOrders;


	private String address;
	
	private String telephoneNumber;
	// private List<Car> cars;
	
	public List<Order> getListOfOrders() {
		return listOfOrders;
	}
	
	public void setListOfOrders(List<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	/*
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	*/
	
	public void addOrder(Order order) {
		listOfOrders.add(order);
	}
	
	public void removeOrder(Order order) {
		listOfOrders.remove(order);
	}
	
}
