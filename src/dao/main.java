package dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Address;
import beans.Amenity;
import beans.Apartment;
import beans.Comment;
import beans.Gender;
import beans.Guest;
import beans.Host;
import beans.Location;
import beans.Reservation;
import beans.Roles;
import beans.StatusApartment;
import beans.StatusReservation;
import beans.Type;

public class main {

	public static void main(String[] args) {
		
		Location l= new Location(10.5,15.2,new Address("Neka ulica 123","Novi Sad",21000));
		Amenity amenity1=new Amenity(123,"free Wifi");
		Amenity amenity2=new Amenity(124,"free parking");
		List<Amenity> amenityList=new ArrayList<Amenity>();
		amenityList.add(amenity1);
		amenityList.add(amenity2);
		List<String> pictures=new ArrayList<String>();
		pictures.add("kfejf");
		pictures.add("jfodhfoa");
		Date date=Date.from(Instant.now());
		List<Date> dates=new ArrayList<Date>();
		dates.add(date);
		dates.add(date);
		
		List<Reservation> reserlist=new ArrayList<Reservation>();
		List<Apartment> apartmentList=new ArrayList<Apartment>();
		reserlist.add(new Reservation());
		apartmentList.add(new Apartment());
		
		Host h= new Host("host","1234567","Marko","Markovic",Gender.MALE,Roles.HOST,apartmentList);
		Guest g= new Guest("guest","123567","Milos","Markovic",Gender.MALE,Roles.GUEST,apartmentList,reserlist);
		Comment com=new Comment(123456,g,new Apartment(1234,Type.WHOLE_APARTMENT,2,3,l,dates,dates,h,new Comment(),pictures,235.15,date,date,StatusApartment.ACTIVE,amenityList,reserlist),"Good time.Had fun",7.53);
		
		Apartment apartmrnt1=new Apartment(1234,Type.WHOLE_APARTMENT,2,3,l,dates,dates,h,com,pictures,235.15,date,date,StatusApartment.ACTIVE,amenityList,reserlist);
		Apartment apartmrnt2=new Apartment(1234,Type.WHOLE_APARTMENT,2,3,l,dates,dates,h,com,pictures,235.15,date,date,StatusApartment.ACTIVE,amenityList,reserlist);
		
		
		apartmentList.add(apartmrnt1);
		apartmentList.add(apartmrnt2);
		
		Reservation r=new Reservation(1234567,new Apartment(1234,Type.WHOLE_APARTMENT,2,3,l,dates,dates,h,com,pictures,235.15,date,date,StatusApartment.ACTIVE,amenityList,reserlist),date,12,1500,"Dobrodosli",g,StatusReservation.CREATED);
		
		
		
		String contextPath="WebContent";
		
		UserDAO u=new UserDAO();
		u.loadUsers(contextPath);
		
		ReservationDAO reservation= new ReservationDAO();
		reservation.loadReservations(contextPath);
		reservation.writeInFile(r);
		
		CommentDAO comment= new CommentDAO();
		comment.loadComments(contextPath);
		
		ApartmentDAO apartment= new ApartmentDAO();
		apartment.loadApartments(contextPath);
		
		AmenityDAO amenity= new AmenityDAO();
		amenity.loadAmenities(contextPath);
	}

}
