package beans;

public class Amenity {
	private int id;
	private String name;
	private Boolean alive;
	
	// sadrzaj apartmana
	
	public Amenity() {
		super();
		this.id=0;
		this.name="";
		this.alive = true;
	}
	
	public Amenity(int id) {
		super();
		this.id = id;
		this.name = "";
		alive = true;
	}

	public Amenity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		alive = true;
	}

	public Amenity(int id, String name, Boolean alive) {
		super();
		this.id = id;
		this.name = name;
		this.alive = alive;
	}
	
	@Override
	public String toString() {
		return "Amenity [id=" + id + ", name=" + name + ", alive=" + alive + "]";
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

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
}