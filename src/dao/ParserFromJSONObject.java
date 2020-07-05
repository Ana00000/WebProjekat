package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
import beans.User;

public class ParserFromJSONObject {

	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

	public User parseUsersObject(JSONObject userObject) {
		
        String username = jsonToStr(userObject, "username");
		String password = jsonToStr(userObject, "password");
		String name = jsonToStr(userObject, "name");
		String surname = jsonToStr(userObject, "surname");
		Gender gender = Gender.valueOf(jsonToStr(userObject, "gender"));
		Roles role = Roles.valueOf(jsonToStr(userObject, "role"));
		
		User user = new User(username, password, name, surname, gender, role);
		return user;
	}
	
	public Reservation parseReservationsObject(JSONObject reservationObject) {	
		String id = jsonToStr(reservationObject, "id");
		String rented=jsonToStr(reservationObject, "rented");
		Date startReservation = new Date();
		try {
			startReservation = format.parse(jsonToStr(reservationObject, "startReservation"));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		int overnightStay = Integer.parseInt(jsonToStr(reservationObject, "overnightStay"));
		int fullPrice = Integer.parseInt(jsonToStr(reservationObject, "fullPrice"));
		String welcomeMessage = jsonToStr(reservationObject, "welcomeMessage");
		Guest guest =new Guest();
		if(reservationObject.get("guest")!=null)
			guest = parseGuestObject((JSONObject)reservationObject.get("guest"));
		StatusReservation status = StatusReservation.valueOf(jsonToStr(reservationObject, "status"));
		Boolean alive = Boolean.parseBoolean(jsonToStr(reservationObject, "alive"));
		
		Reservation reservation = new Reservation(id, rented, startReservation, overnightStay, fullPrice, welcomeMessage, guest, status, alive);
		return reservation;
	}
	
	public Comment parseCommentsObject(JSONObject commentObject) {
		String id = jsonToStr(commentObject, "id");    
		Guest guest =  new Guest();
		if(commentObject.get("guest")!=null)
			guest = parseGuestObject((JSONObject)commentObject.get("guest"));
		String apartment = jsonToStr(commentObject, "apartment");   
		String text = jsonToStr(commentObject, "text");
		double grade = Double.parseDouble(jsonToStr(commentObject, "grade")); 
		Boolean alive = Boolean.parseBoolean(jsonToStr(commentObject, "alive"));
		Boolean visible = Boolean.parseBoolean(jsonToStr(commentObject, "visible"));
		
		Comment comment = new Comment(id, guest, apartment, text, grade, alive, visible);
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public Guest parseGuestObject(JSONObject guestObject ) {
		String username = jsonToStr(guestObject, "username");
		String password = jsonToStr(guestObject, "password");
		String name = jsonToStr(guestObject, "name");
		String surname = jsonToStr(guestObject, "surname");
		Gender gender = Gender.valueOf(jsonToStr(guestObject, "gender"));
		Roles role = Roles.valueOf(jsonToStr(guestObject, "role"));
		List<String> rented = new ArrayList<String>();
		JSONArray apartmentsList = (JSONArray) guestObject.get("rented");
		if(apartmentsList!=null) {
			Iterator<JSONObject> rentedIt = apartmentsList.iterator();
        	while (rentedIt.hasNext()) {
        		rented.add(jsonToStr(rentedIt.next(), "id"));
        	}
        }
        List<String> reservations=new ArrayList<String>() ;	
		JSONArray reservationsList = (JSONArray) guestObject.get("reservations");
		if(reservationsList!=null) {
			Iterator<JSONObject> reservationsIt = reservationsList.iterator();
        	while (reservationsIt.hasNext()) {
        		reservations.add(jsonToStr(reservationsIt.next(), "id"));
        	}
		}
		return new Guest(username, password, name, surname, gender, role, rented, reservations);
	}
	
	@SuppressWarnings("unchecked")
	public Apartment parseApartmentsObject(JSONObject apartmentObject) {
		String id = jsonToStr(apartmentObject, "id");    
		Type type = Type.valueOf(jsonToStr(apartmentObject, "type"));
		int nbrRooms = Integer.parseInt(jsonToStr(apartmentObject, "nbrRooms"));  
		int nbrGuests = Integer.parseInt(jsonToStr(apartmentObject, "nbrGuests"));  
		Location location=new Location();
		if(apartmentObject.get("location")!=null)
			location = parseLocationsObject((JSONObject)apartmentObject.get("location"));
		List<Date> forRent = new ArrayList<Date>();
		JSONArray forRentList = (JSONArray) apartmentObject.get("forRent");
        dateIteration(format, forRent, forRentList, "date");
		List<Date> availability = new ArrayList<Date>();
		JSONArray availabilityList = (JSONArray) apartmentObject.get("availability");
		dateIteration(format, availability, availabilityList, "date");
		Host host = new Host();
		if(apartmentObject.get("host")!=null)
			host = parseHostObject((JSONObject)apartmentObject.get("host"));
		
		List<String> pictures = new ArrayList<String>();
		JSONArray picturesList = (JSONArray) apartmentObject.get("pictures");
		Iterator<JSONObject> it = picturesList.iterator();
        while (it.hasNext()) {
			pictures.add((jsonToStr(it.next(), "pic")));
        }
        
        
        List<String> comments = new ArrayList<String>();
		JSONArray commentsList = (JSONArray) apartmentObject.get("comments");
		Iterator<JSONObject> commentsIt = commentsList.iterator();
        while (commentsIt.hasNext()) {
        	comments.add((jsonToStr(commentsIt.next(), "id")));
        }

		double pricePerNight = Double.parseDouble(jsonToStr(apartmentObject, "pricePerNight")); 
		Date forLogIn = new Date();
		try {
			forLogIn = simpleDateFormat.parse(jsonToStr(apartmentObject, "forLogIn"));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		Date forLogOff = new Date();
		try {
			forLogOff = simpleDateFormat.parse(jsonToStr(apartmentObject, "forLogOff"));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		StatusApartment status = StatusApartment.valueOf(jsonToStr(apartmentObject, "status"));
		List<Amenity> amenities=new ArrayList<Amenity>() ;	
		JSONArray amenitiesList = (JSONArray) apartmentObject.get("amenities");
		if(amenitiesList != null)
			{	
				Iterator<JSONObject> amenitiesIt = amenitiesList.iterator();
				while (amenitiesIt.hasNext()) {
					amenities.add(parseAmenitiesObject((JSONObject) amenitiesIt.next()));
	        			}
					}
		List<String> reservations=new ArrayList<String>() ;	
		JSONArray reservationsList = (JSONArray) apartmentObject.get("reservations");
		if(reservationsList != null)
		{
			Iterator<JSONObject> reservationsIt = reservationsList.iterator();
	        while (reservationsIt.hasNext()) {
	        	reservations.add(jsonToStr(reservationsIt.next(), "id")); 
	        }
		}
		
		Boolean alive = Boolean.parseBoolean(jsonToStr(apartmentObject, "alive"));
		
		Apartment a=new Apartment(id, type, nbrRooms, nbrGuests, location , forRent, availability, host,
				pictures, comments , pricePerNight, forLogIn, forLogOff, status, amenities, reservations, alive);
		
		return a;
	}

	@SuppressWarnings("unchecked")
	private void dateIteration(DateFormat format, List<Date> date, JSONArray json, String par) {
		Date dateRent;
		Iterator<JSONObject> it = json.iterator();
        while (it.hasNext()) {
			try {
				dateRent = format.parse(jsonToStr(it.next(), par));
				date.add(dateRent);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
        }
	}
	
	public Location parseLocationsObject(JSONObject locationObject) {
		
		double width = Double.parseDouble(jsonToStr(locationObject, "width"));
		double height =  Double.parseDouble(jsonToStr(locationObject, "height"));
		Address address= parseAddressesObject((JSONObject)locationObject.get("address"));
		
		return new Location(width, height, address);
	}
	
	public Address parseAddressesObject(JSONObject addressObject) {
		String street=jsonToStr(addressObject, "street");
		String place=jsonToStr(addressObject, "place");
		int postalCode=Integer.parseInt(jsonToStr(addressObject, "postalCode"));
		String country=jsonToStr(addressObject, "country");
		
		return new Address(street, place, postalCode, country);
	}
	
	@SuppressWarnings("unchecked")
	public Host parseHostObject(JSONObject hostObject ) {
		
		String username = jsonToStr(hostObject, "username");
		String password = jsonToStr(hostObject, "password");
		String name = jsonToStr(hostObject, "name");
		String surname = jsonToStr(hostObject, "surname");
		Gender gender = Gender.valueOf(jsonToStr(hostObject, "gender"));
		Roles role = Roles.valueOf(jsonToStr(hostObject, "role"));
		
		List<String> forRent = new ArrayList<String>();
		JSONArray apartmentsList = (JSONArray) hostObject.get("forRent");
		if(apartmentsList != null)
		{
			Iterator<JSONObject> rentedIt = apartmentsList.iterator();
	        while (rentedIt.hasNext()) {
	        	JSONObject o=(JSONObject) rentedIt.next();
	        	if(o != null)
	        	{
	        		forRent.add(jsonToStr(o, "id"));
	        	}
	        }
		}
       
		return new Host(username, password, name, surname, gender, role, forRent);
	}
	
	public Amenity parseAmenitiesObject(JSONObject amenity) {
		int id = Integer.parseInt(jsonToStr(amenity, "id"));    
        String name = jsonToStr(amenity, "name"); 
        Boolean alive = Boolean.parseBoolean(jsonToStr(amenity, "alive"));
        Amenity a = new Amenity(id, name, alive);
        
        return a;
	}
	
	
	private String jsonToStr(JSONObject userObject, String par) {
		String conversation = userObject.get(par).toString();
		return conversation;
	}
	
}
