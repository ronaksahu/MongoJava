package model;

public class Customer {

	public String cID;
	public String name;
	public String street;
	public String city;
	public String stateAb;
	public String zipcode;
	public Customer(String cID, String name, String street, String city, String stateAb, String zipcode) {
		this.cID = cID;
		this.name = name;
		this.street = street;
		this.city = city;
		this.stateAb = stateAb;
		this.zipcode = zipcode;
	}
	public Customer() {
	}
	
	public Customer(String name, String city, String stateAb) {
		this.name = name;
		this.city = city;
		this.stateAb = stateAb;
	}
	
	
	
}
