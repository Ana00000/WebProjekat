package beans;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Roles role;
	
	public User() {
		super();
		this.username = "";
		this.password = "";
		this.name = "";
		this.surname = "";
		this.gender =Gender.FEMALE;
		this.role =  Roles.GUEST;
	}
	
	public User(String username, String password, String name, String surname, Gender gender, Roles role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", gender=" + gender + ", roles=" + role + "]";
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Roles getRole() {
		return role;
	}
	
	public void setRoles(Roles role) {
		this.role = role;
	}
}