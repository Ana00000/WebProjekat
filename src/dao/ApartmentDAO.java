package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import beans.Apartment;
import beans.Host;
import beans.Location;
import beans.StatusApartment;
import beans.Type;

public class ApartmentDAO {

	private Map<Integer, Apartment> apartments = new HashMap<Integer, Apartment>();
	private String contPath;
	private ParserFromJSONObject parserFromJSON= new ParserFromJSONObject();
	private ParserToJSONObject parserToJSON= new ParserToJSONObject();

	public ApartmentDAO() {
		
	}
	
	public ApartmentDAO(String contextPath) {
		loadApartments(contextPath);
		contPath = contextPath;
	}
	
	public Apartment find(Apartment a) {
		for(Apartment apartment: apartments.values())
		{
			if(a.getId() == (apartment.getId()))
				return apartment;
		}
		
		return null;
	}
	
	
	public void add(int id, Type type, int nbrRooms, int nbrGuests, Location location,Host host,double pricePerNight,Date forLogIn, Date forLogOff, StatusApartment status) {
		Apartment a = apartments.get(id);
		if(a == null) {
			a = new Apartment(id,type,nbrRooms,nbrGuests,location,host,pricePerNight,forLogIn,forLogOff,status);
			apartments.put(id, a);
			writeInFile();
		}
	}
		
	
	public Collection<Apartment> findAll() {
		return apartments.values();
	}
	
	@SuppressWarnings("unchecked")
	private void writeInFile() {
		JSONArray root = new JSONArray();
		for(Apartment a : findAll())
		{
			JSONObject user = parserToJSON.apartmentToJSONObject(a);
			root.add(user);
			
		}
		
		try (FileWriter file = new FileWriter(contPath+"/apartments.json",false)) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/apartments.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadApartments(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/apartments.json") )
        {
            JSONArray apartmentsList = (JSONArray) jsonParser.parse(reader);
            
			if(apartmentsList == null)
				return;
            
            Iterator<JSONObject> iterator = apartmentsList.iterator();
            while (iterator.hasNext()) {
            	Apartment a=parserFromJSON.parseApartmentsObject(iterator.next());
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
