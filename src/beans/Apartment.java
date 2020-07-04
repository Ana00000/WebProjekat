package beans;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Apartment {
	private int id;
	private  Type type; 
	private int nbrRooms;
	private int nbrGuests;
	private Location location;
	private List<Date> forRent = new ArrayList<Date>();
	private List<Date> availability = new ArrayList<Date>();
	private Host host;
	private List<Integer> comments;
	private List<String> pictures = new ArrayList<String>();
	private double pricePerNight;
	private Date forLogIn;
	private Date forLogOff;
	private StatusApartment status;
	private List<Amenity> amenities = new ArrayList<Amenity>();
	private List<Integer> reservations = new ArrayList<Integer>();
	
	public Apartment() {
		super();
		this.id=0;
		this.type = Type.ROOM;
		this.nbrRooms = 0;
		this.nbrGuests = 0;
		this.location = new Location();
		this.host = new Host();
		this.comments = new ArrayList<Integer>();
		this.pricePerNight = 0;
		this.forLogIn = Date.from(Instant.now());
		this.forLogOff = Date.from(Instant.now());
		this.status = StatusApartment.INACTIVE ;
	}
	
	public Apartment(int id, Type type, int nbrRooms, int nbrGuests, Location location, List<Date> forRent,
			List<Date> availability, Host host, List<Integer> comment, List<String> pictures, double pricePerNight,
			Date forLogIn, Date forLogOff, StatusApartment status, List<Amenity> amenities,
			List<Integer> reservations) {
		super();
		this.id = id;
		this.type = type;
		this.nbrRooms = nbrRooms;
		this.nbrGuests = nbrGuests;
		this.location = location;
		this.forRent = forRent;
		this.availability = availability;
		this.host = host;
		this.comments = comment;
		this.pictures = pictures;
		this.pricePerNight = pricePerNight;
		this.forLogIn = forLogIn;
		this.forLogOff = forLogOff;
		this.status = status;
		this.amenities = amenities;
		this.reservations = reservations;
	}
	
	
	public Apartment(int id, Type type, int nbrRooms, int nbrGuests, Location location,
			 Host host,  double pricePerNight,
			Date forLogIn, Date forLogOff, StatusApartment status
			) {
		super();
		this.id = id;
		this.type = type;
		this.nbrRooms = nbrRooms;
		this.nbrGuests = nbrGuests;
		this.location = location;
		this.forRent = new ArrayList<Date>();
		this.availability =  new ArrayList<Date>();
		this.host = host;
		this.comments =  new ArrayList<Integer>();
		this.pictures = new ArrayList<String>();
		this.pricePerNight = pricePerNight;
		this.forLogIn = forLogIn;
		this.forLogOff = forLogOff;
		this.status = status;
		this.amenities = new ArrayList<Amenity>();
		this.reservations =  new ArrayList<Integer>();
	}
	
	@Override
	public String toString() {
		return "Apartment [id=" + id + ", type=" + type + ", nbrRooms=" + nbrRooms + ", nbrGuests=" + nbrGuests
				+ ", location=" + location + ", forRent=" + forRent + ", availability=" + availability + ", host="
				+ host + ", comment=" + comments + ", pictures=" + pictures + ", pricePerNight=" + pricePerNight
				+ ", forLogIn=" + forLogIn + ", forLogOff=" + forLogOff + ", status=" + status + ", amenities="
				+ amenities + ", reservations=" + reservations + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public List<Integer> getComment() {
		return comments;
	}
	
	public void setComment(List<Integer> comment) {
		this.comments = comment;
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
	
	public Date getForLogIn() {
		return forLogIn;
	}
	
	public void setForLogIn(Date forLogIn) {
		this.forLogIn = forLogIn;
	}
	
	public Date getForLogOff() {
		return forLogOff;
	}
	
	public void setForLogOff(Date forLogOff) {
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
	
	public List<Integer> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Integer> reservations) {
		this.reservations = reservations;
	}
}