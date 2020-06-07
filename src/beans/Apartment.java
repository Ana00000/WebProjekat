package beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.jni.Time;

public class Apartment {
	private  Type type; 
	private int nbrRooms;
	private int nbrGuests;
	private Location location;
	private List<Date> forRent = new ArrayList<Date>();;
	private List<Date> availability= new ArrayList<Date>();;
	private Host host;
	private Comment comment;
	private List<String> pictures= new ArrayList<String>();;
	private double pricePerNight;
	private Time forLogIn;
	private Time forLogOff;
	private StatusApartment status;
	private List<Amenity> amenities= new ArrayList<Amenity>();;
	private List<Reservation> reservations= new ArrayList<Reservation>();;
	
	public Apartment() {
		super();
	}
	
	public Apartment(Type type, int nbrRooms, int nbrGuests, Location location, List<Date> forRent,
			List<Date> availability, Host host, Comment comment, List<String> pictures, double pricePerNight,
			Time forLogIn, Time forLogOff, StatusApartment status, List<Amenity> amenities,
			List<Reservation> reservations) {
		super();
		this.type = type;
		this.nbrRooms = nbrRooms;
		this.nbrGuests = nbrGuests;
		this.location = location;
		this.forRent = forRent;
		this.availability = availability;
		this.host = host;
		this.comment = comment;
		this.pictures = pictures;
		this.pricePerNight = pricePerNight;
		this.forLogIn = forLogIn;
		this.forLogOff = forLogOff;
		this.status = status;
		this.amenities = amenities;
		this.reservations = reservations;
	}
	
	@Override
	public String toString() {
		return "Apartment [type=" + type + ", nbrRooms=" + nbrRooms + ", nbrGuests=" + nbrGuests + ", location="
				+ location + ", forRent=" + forRent + ", availability=" + availability + ", host=" + host + ", comment="
				+ comment + ", pictures=" + pictures + ", pricePerNight=" + pricePerNight + ", forLogIn=" + forLogIn
				+ ", forLogOff=" + forLogOff + ", status=" + status + ", amenities=" + amenities + ", reservations="
				+ reservations + "]";
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getNbrRooms() {
		return nbrRooms;
	}
	
	public void setNbrRooms(int nbrRooms) {
		this.nbrRooms = nbrRooms;
	}
	
	public int getNbrGuests() {
		return nbrGuests;
	}
	
	public void setNbrGuests(int nbrGuests) {
		this.nbrGuests = nbrGuests;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public List<Date> getForRent() {
		return forRent;
	}
	
	public void setForRent(List<Date> forRent) {
		this.forRent = forRent;
	}
	
	public List<Date> getAvailability() {
		return availability;
	}
	
	public void setAvailability(List<Date> availability) {
		this.availability = availability;
	}
	
	public Host getHost() {
		return host;
	}
	
	public void setHost(Host host) {
		this.host = host;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public List<String> getPictures() {
		return pictures;
	}
	
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	public double getPricePerNight() {
		return pricePerNight;
	}
	
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	public Time getForLogIn() {
		return forLogIn;
	}
	
	public void setForLogIn(Time forLogIn) {
		this.forLogIn = forLogIn;
	}
	
	public Time getForLogOff() {
		return forLogOff;
	}
	
	public void setForLogOff(Time forLogOff) {
		this.forLogOff = forLogOff;
	}
	
	public StatusApartment getStatus() {
		return status;
	}
	
	public void setStatus(StatusApartment status) {
		this.status = status;
	}
	
	public List<Amenity> getAmenities() {
		return amenities;
	}
	
	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
}