package beans;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Apartment {
	private String id;
	private Type type; 
	private int nbrRooms;
	private int nbrGuests;
	private Location location;
	private List<Date> forRent = new ArrayList<Date>();
	private List<Date> availability = new ArrayList<Date>();
	private Host host;
	private List<String> pictures = new ArrayList<String>();
	private List<String> comments = new ArrayList<String>();
	private double pricePerNight;
	private Date forLogIn;
	private Date forLogOff;
	private StatusApartment status;
	private List<Amenity> amenities = new ArrayList<Amenity>();
	private List<String> reservations = new ArrayList<String>();
	private Boolean alive;
	
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	
	public Apartment() {
		super();
		this.id="";
		this.type = Type.ROOM;
		this.nbrRooms = 0;
		this.nbrGuests = 0;
		this.location = new Location();
		this.host = new Host();
		this.pictures= new ArrayList<String>();
		this.comments = new ArrayList<String>();
		this.pricePerNight = 0;
		this.forLogIn = Date.from(Instant.now());
		this.forLogOff = Date.from(Instant.now());
		this.status = StatusApartment.INACTIVE ;
		this.alive = true;
	}
	
	public Apartment(String id) {
		super();
		this.id = id;
	}

	
	public Apartment(String id, List<String> comments) {
		super();
		this.id = id;
		this.comments = comments;
	}

	public Apartment(String id, Type type, int nbrRooms, int nbrGuests,double pricePerNight,StatusApartment status) throws ParseException {
		super();
		this.id = id;
		this.type = type;
		this.nbrRooms = nbrRooms;
		this.nbrGuests = nbrGuests;
		this.pricePerNight = pricePerNight;
		this.status = status;
		this.location = new Location();
		this.host = new Host();
		this.pictures= new ArrayList<String>();
		this.comments = new ArrayList<String>();
		this.pricePerNight = 0;
		this.forLogIn = Date.from(Instant.now()) ;
		this.forLogOff = Date.from(Instant.now());
		this.alive = true;
	}
	
	public Apartment(String id, Type type, int nbrRooms, int nbrGuests, Location location, List<Date> forRent,
			List<Date> availability, Host host, List<String> pictures , List<String> comments, double pricePerNight,
			Date forLogIn, Date forLogOff, StatusApartment status, List<Amenity> amenities,
			List<String> reservations, Boolean alive) {
			super();
			this.id = id;
			this.type = type;
			this.nbrRooms = nbrRooms;
			this.nbrGuests = nbrGuests;
			this.location = location;
			this.forRent = forRent;
			this.availability = availability;
			this.host = host;
			this.pictures = pictures;
			this.comments = comments;
			this.pricePerNight = pricePerNight;
			this.forLogIn = forLogIn;
			this.forLogOff = forLogOff;
			this.status = status;
			this.amenities = amenities;
			this.reservations = reservations;
			this.alive = alive;
	}
	
	
	public Apartment(String id, Type type, int nbrRooms, int nbrGuests, Location location,
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
		this.pictures = new ArrayList<String>();
		this.comments =  new ArrayList<String>();
		this.pricePerNight = pricePerNight;
		this.forLogIn = forLogIn;
		this.forLogOff = forLogOff;
		this.status = status;
		this.amenities = new ArrayList<Amenity>();
		this.reservations =  new ArrayList<String>();
		this.alive = true;
	}
	
	
	
	public Apartment(String id, Type type, int nbrRooms, int nbrGuests, Host host, double pricePerNight,
			StatusApartment status) {
		super();
		this.id = id;
		this.type = type;
		this.nbrRooms = nbrRooms;
		this.nbrGuests = nbrGuests;
		this.location = new Location();
		this.forRent = new ArrayList<Date>();
		this.availability =  new ArrayList<Date>();
		this.host = host;
		this.pictures = new ArrayList<String>();
		this.comments =  new ArrayList<String>();
		this.pricePerNight = pricePerNight;
		this.forLogIn =  Date.from(Instant.now());
		this.forLogOff =  Date.from(Instant.now());
		this.status = status;
		this.amenities = new ArrayList<Amenity>();
		this.reservations =  new ArrayList<String>();
		this.alive = true;
	}

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", type=" + type + ", nbrRooms=" + nbrRooms + ", nbrGuests=" + nbrGuests
				+ ", location=" + location + ", forRent=" + forRent + ", availability=" + availability + ", host="
				+ host + ", pictures=" + pictures + ", comments=" + comments + ", pricePerNight=" + pricePerNight
				+ ", forLogIn=" + forLogIn + ", forLogOff=" + forLogOff + ", status=" + status + ", amenities="
				+ amenities + ", reservations=" + reservations + ", alive=" + alive + "]";
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
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
	
	public List<String> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<String> reservations) {
		this.reservations = reservations;
	}


	public Boolean getAlive() {
		return alive;
	}


	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
}