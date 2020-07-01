package beans;

import java.time.Instant;
import java.util.Date;

public class Reservation {
	private int id;
	private Apartment rented;
	private Date startReservation;
	private int overnightStay;
	private int fullPrice;
	private String welcomeMessage;
	private Guest guest;
	private StatusReservation status;
	
	public Reservation() {
		super();
		this.id = 0;
		this.rented =new Apartment();
		this.startReservation = Date.from(Instant.now());
		this.overnightStay = 15;
		this.fullPrice = 150;
		this.welcomeMessage = "";
		this.guest = new Guest();
		this.status = StatusReservation.CREATED;
	}
	
	public Reservation(int id, Apartment rented, Date startReservation, int overnightStay, int fullPrice, String welcomeMessage,
			Guest guest, StatusReservation status) {
		super();
		this.id = id;
		this.rented = rented;
		this.startReservation = startReservation;
		this.overnightStay = overnightStay;
		this.fullPrice = fullPrice;
		this.welcomeMessage = welcomeMessage;
		this.guest = guest;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", rented=" + rented + ", startReservation=" + startReservation
				+ ", overnightStay=" + overnightStay + ", fullPrice=" + fullPrice + ", welcomeMessage=" + welcomeMessage
				+ ", guest=" + guest + ", status=" + status + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Apartment getRented() {
		return rented;
	}

	public void setRented(Apartment rented) {
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
}