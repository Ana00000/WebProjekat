package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Amenity;
import beans.Apartment;
import beans.Comment;
import beans.Guest;
import beans.Host;
import beans.Reservation;
import beans.User;

public class ParserToJSONObject {
	
	
	DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	
	@SuppressWarnings("unchecked")
	public JSONObject userToJSONObject(User u) {
		if(u==null)
			return new JSONObject();
        JSONObject user = new JSONObject();
        user.put("username", u.getUsername());
        user.put("password", u.getPassword());
        user.put("name", u.getName());
        user.put("surname", u.getSurname());
        user.put("gender", u.getGender().toString());
        user.put("role", u.getRole().toString());
        return user;
    }

	@SuppressWarnings("unchecked")
	public JSONObject amenityToJSONObject(Amenity a) {
		if(a==null)
			return new JSONObject();
        JSONObject amenity = new JSONObject();
        amenity.put("id", a.getId());
        amenity.put("name", a.getName());
        amenity.put("alive", a.getAlive().toString());
		return amenity;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject apartmentToJSONObject(Apartment a) {
		if(a==null)
			return new JSONObject();
		JSONObject apartment = new JSONObject();
		apartment.put("id", a.getId());
		apartment.put("type", a.getType().toString() );
		apartment.put("nbrRooms", a.getNbrRooms() );
		apartment.put("nbrGuests", a.getNbrGuests());
		
		JSONObject location =new JSONObject();
		JSONObject address =new JSONObject();
		location.put("width", a.getLocation().getWidth());
		location.put("height",a.getLocation().getHeight());
		address.put("street", a.getLocation().getAddress().getStreet());
		address.put("place", a.getLocation().getAddress().getPlace());
		address.put("postalCode", a.getLocation().getAddress().getPostalCode());
		address.put("country", a.getLocation().getAddress().getCountry());
		location.put("address", address);
			
		apartment.put("location", location);
		
		JSONArray forRent=new JSONArray();
		for(Date date : a.getForRent())
			{
				JSONObject object=new JSONObject();
				object.put("date", format.format(date));
				forRent.add(object);
			}
		
		apartment.put("forRent", forRent);
		
		JSONArray availability=new JSONArray();
			for(Date date : a.getAvailability() )
				{
					JSONObject object=new JSONObject();
					object.put("date", format.format(date));
					availability.add(object);
				}
		apartment.put("availability", availability);
		apartment.put("host", hostToJSONObject(a.getHost()));	
		
		
		JSONArray pictures=new JSONArray();
		for(String picture : a.getPictures())
			{
				JSONObject object=new JSONObject();
				object.put("pic", picture);
				pictures.add(object);
			}	
		
		apartment.put("pictures", pictures);
		
		
		JSONArray comments=new JSONArray();
		for(String com : a.getComments())
			{
				JSONObject object=new JSONObject();
				object.put("id", com);
				comments.add(object);
			}	
		
		apartment.put("comments", comments);
		
		apartment.put("pricePerNight", a.getPricePerNight());
		apartment.put("forLogIn", simpleDateFormat.format(a.getForLogIn()));
		apartment.put("forLogOff", simpleDateFormat.format(a.getForLogOff()));
		apartment.put("status", a.getStatus().toString());
		
		JSONArray amenities=new JSONArray();
		for(Amenity amenity : a.getAmenities())
		{
			amenities.add(amenityToJSONObject(amenity));
		}	
		

		JSONArray reservations=new JSONArray();
		for(String reservation : a.getReservations())
		{
			JSONObject object=new JSONObject();
			object.put("id", reservation);
			reservations.add(object);
		}	
		
		apartment.put("reservations", reservations);
		apartment.put("alive", a.getAlive().toString());
		
		return apartment;
	}

	@SuppressWarnings("unchecked")
	public JSONObject commentToJSONObject(Comment c) {
		if(c==null)
			return new JSONObject();
		JSONObject comment = new JSONObject();
		comment.put("id", c.getId());
		comment.put("guest", guestToJSONObject(c.getGuest()));
		comment.put("apartment",c.getApartment());
		comment.put("id", c.getId());
		comment.put("text", c.getText());
		comment.put("grade", c.getGrade());
		comment.put("alive", c.getAlive());
		comment.put("visible", c.getVisible());
		
		return comment;
	}

	@SuppressWarnings("unchecked")
	public JSONObject reservationToJSONObject(Reservation r) {
		
		if(r==null)
			return new JSONObject();
		JSONObject reservation = new JSONObject();
		reservation.put("id", r.getId());
		reservation.put("rented", r.getRented());
		reservation.put("startReservation", format.format(r.getStartReservation().toString()));
		reservation.put("overnightStay", r.getOvernightStay());
		reservation.put("fullPrice", r.getFullPrice());
		reservation.put("welcomeMessage", r.getWelcomeMessage());
		reservation.put("guest", guestToJSONObject(r.getGuest()));
		reservation.put("status", r.getStatus().toString());
		reservation.put("alive", r.getAlive().toString());
		
		return reservation;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject guestToJSONObject(Guest g) {
		if(g==null)
			return new JSONObject();
        JSONObject guest = new JSONObject();
        guest.put("username", g.getUsername());
        guest.put("password", g.getPassword());
        guest.put("name", g.getName());
        guest.put("surname", g.getSurname());
        guest.put("gender", g.getGender().toString());
        guest.put("role", g.getRole().toString());
        
		JSONArray apartments=new JSONArray();
		for(String	apartment: g.getRented())
		{
			JSONObject object=new JSONObject();
			object.put("id", apartment);
			apartments.add(object);
		}
		guest.put("rented", apartments);
		
		JSONArray reservations=new JSONArray();
		for(String reservation : g.getReservations())
		{
			JSONObject object=new JSONObject();
			object.put("id", reservation);
			apartments.add(object);
		}
		
		guest.put("reservations", reservations);
        return guest;
    }
	
	@SuppressWarnings("unchecked")
	public JSONObject hostToJSONObject(Host h) {
		if(h==null)
			return new JSONObject();
        JSONObject host = new JSONObject();
        host.put("username", h.getUsername());
        host.put("password", h.getPassword());
        host.put("name", h.getName());
        host.put("surname", h.getSurname());
        host.put("gender", h.getGender().toString());
        host.put("role", h.getRole().toString());
        
		JSONArray apartments=new JSONArray();
		for(String	apartment: h.getForRent())
		{
			JSONObject object=new JSONObject();
			object.put("id", apartment);
			apartments.add(object);
		}
		host.put("forRent", apartments);

        return host;
    }
}
