package beans;

public abstract class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Roles roles;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String name, String surname, Gender gender, Roles roles) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", gender=" + gender + ", roles=" + roles + "]";
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
	
	public Roles getRoles() {
		return roles;
	}
	
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
}
