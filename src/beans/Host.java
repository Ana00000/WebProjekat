package beans;

import java.util.ArrayList;
import java.util.List;

public class Host extends User{
	private List<Apartment> forRent = new ArrayList<Apartment>();

	public Host() {
		super();
	}

	public Host(String username, String password, String name, String surname, Gender gender, Roles roles, List<Apartment> forRent) {
		super(username, password, name, surname, gender, roles);
		this.forRent = forRent;
	}

	@Override
	public String toString() {
		return "Host [forRent=" + forRent + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getGender()=" + getGender()
				+ ", getRoles()=" + getRoles() + "]";
	}

	public List<Apartment> getForRent() {
		return forRent;
	}

	public void setForRent(List<Apartment> forRent) {
		this.forRent = forRent;
	}
}
