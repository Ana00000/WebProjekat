package beans;

import java.util.Date;

public class Reservation {
	private Apartment rented;
	private Date startReservation;
	private int overnightStay;
	private int fullPrice;
	private String welcomeMessage;
	private Guest guest;
	private StatusReservation status;
	
	public Reservation() {
		super();
	}
	
	public Reservation(Apartment rented, Date startReservation, int overnightStay, int fullPrice, String welcomeMessage,
			Guest guest, StatusReservation status) {
		super();
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
		return "Reservation [rented=" + rented + ", startReservation=" + startReservation + ", overnightStay="
				+ overnightStay + ", fullPrice=" + fullPrice + ", welcomeMessage=" + welcomeMessage + ", guest=" + guest
				+ ", status=" + status + "]";
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
