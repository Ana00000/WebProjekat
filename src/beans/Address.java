package beans;

public class Address {
	private String street;
	private String place;
	private int postalCode;
	private String country
	;
	 // Ulica i broj, Naseljeno mesto, Poštanski broj mesta, Drzava (npr. Sutjeska 3, Novi Sad, 21000, Serbia) 
	
	public Address() {
		super();
		this.street="";
		this.place="";
		this.postalCode=0;
		this.country="";
	}

	public Address(String street, String place, int postalCode, String country) {
		super();
		this.street = street;
		this.place = place;
		this.postalCode = postalCode;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", place=" + place + ", postalCode=" + postalCode + ", country=" + country
				+ "]";
	}

	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public int getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}