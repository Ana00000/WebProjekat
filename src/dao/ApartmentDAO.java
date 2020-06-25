package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import beans.Amenity;
import beans.Apartment;
import beans.Comment;
import beans.Host;
import beans.Location;
import beans.Reservation;
import beans.StatusApartment;
import beans.Type;

public class ApartmentDAO {

	private Map<Integer, Apartment> apartments = new HashMap<Integer, Apartment>();
	
	public ApartmentDAO() {
		
	}
	
	public ApartmentDAO(String contextPath) {
		loadApartments(contextPath);
	}
	
	public Apartment find(Apartment a) {
		for(Apartment apartment: apartments.values())
		{
			if(a.getId() == (apartment.getId()))
				return apartment;
		}
		
		return null;
	}
	
	public Collection<Apartment> findAll() {
		return apartments.values();
	}

	@SuppressWarnings("unchecked")
	public void loadApartments(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/json/apartments.json") )
        {
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
 
            JSONArray apartmentsList = (JSONArray) obj.get("apartments");
            Iterator<JSONObject> iterator = apartmentsList.iterator();
            while (iterator.hasNext()) {
            	parseApartmentsObject(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public Apartment parseApartmentsObject(JSONObject apartmentObject) {
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		
		int id = Integer.parseInt(jsonToStr(apartmentObject, "id"));    
		
		Type type = Type.valueOf(jsonToStr(apartmentObject, "type"));
		
		int nbrRooms = Integer.parseInt(jsonToStr(apartmentObject, "nbrRooms"));  
		
		int nbrGuests = Integer.parseInt(jsonToStr(apartmentObject, "nbrGuests"));  
		
		Location location = null;  		//SRECNO
		
		List<Date> forRent = new ArrayList<Date>();
		JSONArray forRentList = (JSONArray) apartmentObject.get("forRent");
        dateIteration(format, forRent, forRentList, "date");
        
		List<Date> availability = new ArrayList<Date>();
		JSONArray availabilityList = (JSONArray) apartmentObject.get("availability");
		dateIteration(format, availability, availabilityList, "date");
		
		Host host = null; 				//SRECNO
		Comment comment = null; 		//SRECNO
		
		List<String> pictures = new ArrayList<String>();
		JSONArray picturesList = (JSONArray) apartmentObject.get("pictures");
		Iterator<JSONObject> it = picturesList.iterator();
        while (it.hasNext()) {
			pictures.add((jsonToStr(it.next(), "pic")));
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
		
		List<Amenity> amenities = null; 		//SRECNO
		List<Reservation> reservations = null;		//SRECNO
		
		Apartment a=new Apartment(id, type, nbrRooms, nbrGuests, location , forRent, availability, host, comment,
				pictures, pricePerNight, forLogIn, forLogOff, status, amenities, reservations);
		
		apartments.put(id, a);
		return a;
	}

	private String jsonToStr(JSONObject apartmentObject, String par) {
		String conversation = apartmentObject.get(par).toString();
		return conversation;
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
}
