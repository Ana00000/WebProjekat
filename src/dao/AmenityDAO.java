package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	public AmenityDAO() {
		
	}
	
	public AmenityDAO(String contextPath) {
		loadAmenities(contextPath);
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
	
	public Collection<Amenity> findAll() {
		return amenities.values();
	}

	@SuppressWarnings("unchecked")
	public void loadAmenities(String contextPath) {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/amenities.json") )
        {
			JSONObject obj =(JSONObject) jsonParser.parse(reader);
            
            JSONArray amenityList = (JSONArray) obj.get("amenities");
            Iterator<JSONObject> iterator = amenityList.iterator();
            while (iterator.hasNext()) {
      
                parseAmenitiesObject(iterator.next());
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
            
            
	
	private void parseAmenitiesObject(JSONObject amenity) {
		String idStr = amenity.get("id").toString();
		int id = Integer.parseInt(idStr);    
        String name = (String) amenity.get("name");
        amenities.put(id, new Amenity(id, name));
	}
	
}