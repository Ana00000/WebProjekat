package beans;

public class Comment {
    private String id;
    private Guest guest;
    private String apartment;
    private String text;
    private double grade;

    public Comment() {
        super();
        this.id = "";
        this.guest = new Guest();
        this.apartment = "";
        this.text = "";
        this.grade = 0.0;
    }

    public Comment(String id, Guest guest, String apartment, String text, double grade) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getApartment() {
        return apartment;
    }
}