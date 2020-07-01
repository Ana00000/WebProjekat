package beans;

public class Location {
	private double width;
	private double height;
	private Address address;
	
	public Location() {
		super();
		this.width =0.0;
		this.height =0.0;
		this.address = new Address();
	}
	
	public Location(double width, double height, Address address) {
		super();
		this.width = width;
		this.height = height;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Location [width=" + width + ", height=" + height + ", address=" + address + "]";
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}