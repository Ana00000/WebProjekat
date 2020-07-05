package beans;

import java.util.ArrayList;
import java.util.List;

public class Guest extends User{
	private List<String> rented = new ArrayList<String>();
	private List<String> reservations= new ArrayList<String>();
	
	public Guest() {
		super();
	}
	
	public Guest(String username, String password, String name, String surname, Gender gender, Roles role, List<String> rented, List<String> reservations) {
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

	public List<String> getRented() {
		return rented;
	}

	public void setRented(List<String> rented) {
		this.rented = rented;
	}

	public List<String> getReservations() {
		return reservations;
	}

	public void setReservations(List<String> reservations) {
		this.reservations = reservations;
	}
}