package beans;

public class Comment {
    private String id;
    private Guest guest;
    private String apartment;
    private String text;
    private double grade;
    private Boolean alive;
    private Boolean visible;
    
    public Comment() {
        super();
        this.id = "";
        this.guest = new Guest();
        this.apartment = "";
        this.text = "";
        this.grade = 0.0;
        this.alive = true;
        this.visible = true;
    }
    
    public Comment(String id, Boolean visible) {
		super();
		this.id = id;
		this.visible = visible;
	}

	public Comment(String id, Guest guest, String apartment, String text, double grade) {
        super();
        this.id = id;
        this.guest = guest;
        this.apartment = apartment;
        this.text = text;
        this.grade = grade;
        this.alive = true;
        this.visible = true;
    }
  
    public Comment(String id, Guest guest, String apartment, String text, double grade, Boolean alive) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
		this.alive = alive;
		this.visible = true;
	}

    public Comment(String id, Guest guest, String apartment, String text, double grade, Boolean alive,
			Boolean visible) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
		this.alive = alive;
		this.visible = visible;
	}
    
	@Override
	public String toString() {
		return "Comment [id=" + id + ", guest=" + guest + ", apartment=" + apartment + ", text=" + text + ", grade="
				+ grade + ", alive=" + alive + ", visible=" + visible + "]";
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

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}