package beans;

public class Address {
	private String street;
	private String place;
	private int postalCode;
	
	 // Ulica i broj, Naseljeno mesto, Poštanski broj mesta (npr. Sutjeska 3, Novi Sad, 21000) 
	
	public Address() {
		super();
	}

	public Address(String street, String place, int postalCode) {
		super();
		this.street = street;
		this.place = place;
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", place=" + place + ", postalCode=" + postalCode + "]";
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
}