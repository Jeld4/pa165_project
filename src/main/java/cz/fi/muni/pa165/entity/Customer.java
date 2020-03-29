package cz.fi.muni.pa165.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Klima
 */
public class Customer extends User {

	
	public Customer() {
		super();
	}
	

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((listOfOrders == null) ? 0 : listOfOrders.hashCode());
		result = prime * result + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (listOfOrders == null) {
			if (other.listOfOrders != null)
				return false;
		} else if (!listOfOrders.equals(other.listOfOrders))
			return false;
		if (telephoneNumber == null) {
			if (other.telephoneNumber != null)
				return false;
		} else if (!telephoneNumber.equals(other.telephoneNumber))
			return false;
		return true;
	}
	
}
