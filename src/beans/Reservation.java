package beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Reservation {
	private String id;
	private String rented;
	private Date startReservation;
	private int overnightStay;
	private int fullPrice;
	private String welcomeMessage;
	private Guest guest;
	private StatusReservation status;
	private Boolean alive;
	
	private DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	public Reservation() {
		super();
		this.id = "";
		this.rented = "";
		this.startReservation = Date.from(Instant.now());
		this.overnightStay = 15;
		this.fullPrice = 150;
		this.welcomeMessage = "";
		this.guest = new Guest();
		this.status = StatusReservation.CREATED;
		this.alive = true;
	}
	
	public Reservation(String id, String rented, Date startReservation, int overnightStay, String welcomeMessage,
			Guest guest, StatusReservation status) {
		super();
		this.id = id;
		this.rented = rented;
		this.startReservation = startReservation;
		this.overnightStay = overnightStay;
		this.fullPrice = 0;
		this.welcomeMessage = welcomeMessage;
		this.guest = guest;
		this.status = status;
		this.alive = true;
	}

	public Reservation(String id, String rented, String startReservation, int overnightStay, int fullPrice, String welcomeMessage,
			Guest guest, StatusReservation status) throws ParseException {
		super();
		this.id = id;
		this.rented = rented;
		this.startReservation = format.parse(startReservation);
		this.overnightStay = overnightStay;
		this.fullPrice = fullPrice;
		this.welcomeMessage = welcomeMessage;
		this.guest = guest;
		this.status = status;
		this.alive = true;
	}
	
	public Reservation(String id, String rented, Date startReservation, int overnightStay, int fullPrice, String welcomeMessage,
			Guest guest, StatusReservation status, Boolean alive) {
		super();
		this.id = id;
		this.rented = rented;
		this.startReservation = startReservation;
		this.overnightStay = overnightStay;
		this.fullPrice = fullPrice;
		this.welcomeMessage = welcomeMessage;
		this.guest = guest;
		this.status = status;
		this.alive = alive;
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", rented=" + rented + ", startReservation=" + startReservation
				+ ", overnightStay=" + overnightStay + ", fullPrice=" + fullPrice + ", welcomeMessage=" + welcomeMessage
				+ ", guest=" + guest + ", status=" + status + ", alive=" + alive + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRented() {
		return rented;
	}

	public void setRented(String rented) {
		this.rented = rented;
	}

	public Date getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}

	public int getOvernightStay() {
		return overnightStay;
	}

	public void setOvernightStay(int overnightStay) {
		this.overnightStay = overnightStay;
	}

	public int getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(int fullPrice) {
		this.fullPrice = fullPrice;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public StatusReservation getStatus() {
		return status;
	}

	public void setStatus(StatusReservation status) {
		this.status = status;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
}