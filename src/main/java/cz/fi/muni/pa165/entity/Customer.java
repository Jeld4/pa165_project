package cz.fi.muni.pa165.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Klima
 */
public class Customer extends User {

	public Customer(String n, String l, String p, String a, String tn) {
		super(n, l, p);
		listOfOrders = new ArrayList<Order>();
		address = a;
		telephoneNumber = tn;
	}

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
