package beans;

import java.util.ArrayList;
import java.util.List;

public class Guest extends User{
	private List<Apartment> rented = new ArrayList<Apartment>();
	private List<Reservation> reservations= new ArrayList<Reservation>();
	
	public Guest() {
		super();
	}
	
	public Guest(String username, String password, String name, String surname, Gender gender, Roles role, List<Apartment> rented, List<Reservation> reservations) {
		super(username, password, name, surname, gender, role);
		this.rented = rented;
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Guest [rented=" + rented + ", reservations=" + reservations + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getName()=" + getName() + ", getSurname()=" + getSurname()
				+ ", getGender()=" + getGender() + ", getRoles()=" + getRole() + "]";
	}

	public List<Apartment> getRented() {
		return rented;
	}

	public void setRented(List<Apartment> rented) {
		this.rented = rented;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
}