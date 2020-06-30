package dao;

public class main {

	public static void main(String[] args) {
		
		String contextPath="WebContent";
		
		UserDAO u=new UserDAO();
		u.loadUsers(contextPath);
		
		ReservationDAO reservation= new ReservationDAO();
		reservation.loadReservations(contextPath);
		
		CommentDAO comment= new CommentDAO();
		comment.loadComments(contextPath);
		
		ApartmentDAO apartment= new ApartmentDAO();
		apartment.loadApartments(contextPath);
		
		AmenityDAO amenity= new AmenityDAO();
		amenity.loadAmenities(contextPath);
	}

}
