package dao;

public class gla {

	public static void main(String[] args) {
		
		String path="WebContent/";
		
		//AmenityDAO a = new AmenityDAO();
		//a.loadAmenities(path);
		
		//UserDAO u=new UserDAO();
		//u.loadUsers(path);
		
		//CommentDAO c=new CommentDAO();
		//c.loadComments(path);
		
		ApartmentDAO apar = new ApartmentDAO();
		apar.loadApartments(path);
	}

}
