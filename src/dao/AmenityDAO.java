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
	private ParserToJSONObject parserJSON= new ParserToJSONObject();
	
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
		try (FileReader reader = new FileReader(contextPath+"/json/amenities.json") )
        {
            JSONArray amenityList = (JSONArray) jsonParser.parse(reader);
            
			if(amenityList == null)
				return;
            
            Iterator<JSONObject> iterator = amenityList.iterator();
            while (iterator.hasNext()) {
      
            	Amenity a=parserJSON.parseAmenitiesObject(iterator.next());
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
       

}