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

import beans.Address;
import beans.Amenity;
import beans.Apartment;
import beans.Comment;
import beans.Gender;
import beans.Host;
import beans.Location;
import beans.Reservation;
import beans.Roles;
import beans.StatusApartment;
import beans.Type;

public class ApartmentDAO {

	private Map<Integer, Apartment> apartments = new HashMap<Integer, Apartment>();
	private ParserToJSONObject parserJSON= new ParserToJSONObject();

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
            JSONArray apartmentsList = (JSONArray) jsonParser.parse(reader);
            
			if(apartmentsList == null)
				return;
            
            Iterator<JSONObject> iterator = apartmentsList.iterator();
            while (iterator.hasNext()) {
            	Apartment a=parserJSON.parseApartmentsObject(iterator.next());
            	apartments.put(a.getId(), a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
}
