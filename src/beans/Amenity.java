package beans;

public class Amenity {
	private int id;
	private String name;
	
	// sadrzaj apartmana
	
	public Amenity() {
		super();
	}
	
	public Amenity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Amenity [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}