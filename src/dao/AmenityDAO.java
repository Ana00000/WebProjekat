package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import beans.Amenity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AmenityDAO {
	
	private Map<Integer, Amenity> amenities = new HashMap<Integer, Amenity>();
	private String contPath;
	private ParserFromJSONObject parserFromJSON= new ParserFromJSONObject();
	private ParserToJSONObject parserToJSON= new ParserToJSONObject();
	
	public AmenityDAO() {
		
	}
	
	public AmenityDAO(String contextPath) {
		loadAmenities(contextPath);
		contPath = contextPath;
	}

	public Amenity find(int id) {
		
		if (!amenities.containsKey(id)) {
			return null;
		}
		
		Amenity a = amenities.get(id);
		
        
		return a;
	}
	
	public Amenity find(Amenity a) {
		for(Amenity amenity: amenities.values())
		{
			if(a.getName().equals(amenity.getName()))
				if(a.getId() == amenity.getId())
				{
					return amenity;
				}
		}
		
		return null;
	}
	
	public void add(int id, String name) {
		Amenity a = amenities.get(id);
		if(a == null) {
			a = new Amenity(id, name);
			amenities.put(id, a);
			writeInFile();
		}
	}
	
	public Collection<Amenity> findAll() {
		return amenities.values();
	}

	@SuppressWarnings("unchecked")
	private void writeInFile() {
		JSONArray root = new JSONArray();
		for(Amenity a : findAll())
		{
			JSONObject amenity = parserToJSON.amenityToJSONObject(a);
			root.add(amenity);
			
		}
		try (FileWriter file = new FileWriter(contPath+"/amenities.json")) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/amenities.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadAmenities(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/amenities.json") )
        {
            JSONArray amenityList = (JSONArray) jsonParser.parse(reader);
            
			if(amenityList == null)
				return;
            
            Iterator<JSONObject> iterator = amenityList.iterator();
            while (iterator.hasNext()) {
      
            	Amenity a=parserFromJSON.parseAmenitiesObject(iterator.next());
                amenities.put(a.getId(), a);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
       
	public void set(int id, String name) {
		Amenity newAmenity = new Amenity(id, name);
		amenities.put(id, newAmenity);
		writeInFile();
	}

	public void remove(Amenity amenity) {
		amenity.setAlive(false);
		amenities.put(amenity.getId(), amenity);
		writeInFile();
	}
}