package beans;

import java.util.ArrayList;
import java.util.List;

public class Guest extends User{
	private List<Integer> rented = new ArrayList<Integer>();
	private List<Integer> reservations= new ArrayList<Integer>();
	
	public Guest() {
		super();
	}
	
	public Guest(String username, String password, String name, String surname, Gender gender, Roles role, List<Integer> rented, List<Integer> reservations) {
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

	public List<Integer> getRented() {
		return rented;
	}

	public void setRented(List<Integer> rented) {
		this.rented = rented;
	}

	public List<Integer> getReservations() {
		return reservations;
	}

	public void setReservations(List<Integer> reservations) {
		this.reservations = reservations;
	}
}