package beans;

public class Admin extends User{

	public Admin() {
		super();
	}

	public Admin(String username, String password, String name, String surname, Gender gender, Roles roles) {
		super(username, password, name, surname, gender, roles);
	}

	@Override
	public String toString() {
		return "Admin [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getName()=" + getName()
				+ ", getSurname()=" + getSurname() + ", getGender()=" + getGender() + ", getRoles()=" + getRoles()
				+ "]";
	}
}
