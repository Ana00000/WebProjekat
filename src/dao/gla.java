package dao;

public class gla {

	public static void main(String[] args) {
		
		String path = "WebContent/";
		
		AmenityDAO amenity = new AmenityDAO();
		//amenity.loadAmenities(path);
		
		UserDAO user = new UserDAO();
		//user.loadUsers(path);
		
		CommentDAO comment = new CommentDAO();
		//comment.loadComments(path);
		
		ApartmentDAO apartment = new ApartmentDAO();
		//apartment.loadApartments(path);
		
		ReservationDAO reservation = new ReservationDAO();
		//reservation.loadReservations(path);
	}

}
