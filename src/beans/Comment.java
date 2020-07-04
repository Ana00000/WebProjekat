package beans;

public class Comment {
	private int id;
	private Guest guest;
	private int apartment;
	private String text;
	private double grade;
	
	public Comment() {
		super();
		this.id = 123;
		this.guest = new Guest();
		this.apartment = 0;
		this.text = "";
		this.grade = 0.0;
	}
	
	public Comment(int id, Guest guest, int apartment, String text, double grade) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", guest=" + guest + ", apartment=" + apartment + ", text=" + text + ", grade="
				+ grade + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Guest getGuest() {
		return guest;
	}
	
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public int getApartment() {
		return apartment;
	}
	
	public void setApartment(int apartment) {
		this.apartment = apartment;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
}