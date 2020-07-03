package dao;

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
		location.put("address", address);
			
		apartment.put("location", location);
		
		JSONArray forRent=new JSONArray();
		for(Date date : a.getForRent())
			{
				JSONObject object=new JSONObject();
				object.put("date", date);
				forRent.add(object);
			}
		
		apartment.put("forRent", forRent);
		JSONArray availability=new JSONArray();
			for(Date date : a.getAvailability() )
				{
					JSONObject object=new JSONObject();
					object.put("date", date);
					availability.add(object);
				}
		apartment.put("availability", availability);
		apartment.put("host", hostToJSONObject(a.getHost()));	
		
		JSONArray comments=new JSONArray();
		for(Comment comment : a.getComment())
		{
			comments.add(commentToJSONObject(comment));
		}	
		apartment.put("comment", comments);
		
		
		JSONArray pictures=new JSONArray();
			for(String picture : a.getPictures())
				{
					JSONObject object=new JSONObject();
					object.put("pic", picture);
					pictures.add(object);
				}	
		
		apartment.put("pricePerNight", a.getPricePerNight());
		apartment.put("forLogIn", a.getForLogIn());
		apartment.put("forLogOff", a.getForLogOff());
		apartment.put("status", a.getStatus().toString());
		
		JSONArray amenities=new JSONArray();
		for(Amenity amenity : a.getAmenities())
		{
			amenities.add(amenityToJSONObject(amenity));
		}	
		
		
		apartment.put("amenities", amenities);
		
		
		JSONArray reservations=new JSONArray();
		for(Reservation reservation : a.getReservations())
		{
			amenities.add(reservationToJSONObject(reservation));
		}	
		
		apartment.put("reservations", reservations);
		
		return apartment;
	}

	@SuppressWarnings("unchecked")
	public JSONObject commentToJSONObject(Comment c) {
		if(c==null)
			return new JSONObject();
		JSONObject comment = new JSONObject();
		comment.put("id", c.getId());
		comment.put("guest", guestToJSONObject(c.getGuest()));
		comment.put("apartment",apartmentToJSONObject(c.getApartment()));
		comment.put("id", c.getId());
		comment.put("text", c.getText());
		comment.put("grade", c.getGrade());
		
		return comment;
	}

	@SuppressWarnings("unchecked")
	public JSONObject reservationToJSONObject(Reservation r) {
		
		if(r==null)
			return new JSONObject();
		JSONObject reservation = new JSONObject();
		reservation.put("id", r.getId());
		reservation.put("rented", apartmentToJSONObject(r.getRented()));
		reservation.put("startReservation",r.getStartReservation().toString());
		reservation.put("overnightStay", r.getOvernightStay());
		reservation.put("fullPrice", r.getFullPrice());
		reservation.put("welcomeMessage", r.getWelcomeMessage());
		reservation.put("guest", guestToJSONObject(r.getGuest()));
		reservation.put("status", r.getStatus().toString());
		
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
		for(Apartment	apartment: g.getRented())
		{
			apartments.add(apartmentToJSONObject(apartment));
		}
		guest.put("rented", apartments);
		
		JSONArray reservations=new JSONArray();
		for(Reservation reservation : g.getReservations())
		{
			reservations.add(reservationToJSONObject(reservation));
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
		for(Apartment	apartment: h.getForRent())
		{
			apartments.add(apartmentToJSONObject(apartment));
		}
		host.put("rented", apartments);

        return host;
    }
}
