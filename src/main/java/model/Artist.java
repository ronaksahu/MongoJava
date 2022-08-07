package model;

public class Artist {
	public String aID;
	public String name;
	public String birthDate;
	public String deathDate;
	public String commission;
	public String street;
	public String city;
	public String stateAb;
	public String zipcode;
	public Artist(String aID, String name, String birthDate, String deathDate, String commission, String street,
			String city, String stateAb, String zipcode) {
		super();
		this.aID = aID;
		this.name = name;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.commission = commission;
		this.street = street;
		this.city = city;
		this.stateAb = stateAb;
		this.zipcode = zipcode;
	}
	public Artist() {
	}
	
	
	
}
