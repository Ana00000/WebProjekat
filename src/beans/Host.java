package beans;

import java.util.ArrayList;
import java.util.List;

public class Host extends User{
	private List<Integer> forRent = new ArrayList<Integer>();

	public Host() {
		super();
	}

	public Host(String username, String password, String name, String surname, Gender gender, Roles role, List<Integer> forRent) {
		super(username, password, name, surname, gender, role);
		this.forRent = forRent;
	}
    
	@Override
	public String toString() {
		return "Host [forRent=" + forRent + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getGender()=" + getGender()
				+ ", getRoles()=" + getRole() + "]";
	}

	public List<Integer> getForRent() {
		return forRent;
	}

	public void setForRent(List<Integer> forRent) {
		this.forRent = forRent;
	}
}