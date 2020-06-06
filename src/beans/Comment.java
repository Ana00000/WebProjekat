package beans;

public class Comment {
	private Guest guest;
	private Apartment apartment;
	private String text;
	private double grade;
	
	public Comment() {
		super();
	}
	
	public Comment(Guest guest, Apartment apartment, String text, double grade) {
		super();
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "Comment [guest=" + guest + ", apartment=" + apartment + ", text=" + text + ", grade=" + grade + "]";
	}
	
	public Guest getGuest() {
		return guest;
	}
	
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public Apartment getApartment() {
		return apartment;
	}
	
	public void setApartment(Apartment apartment) {
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
