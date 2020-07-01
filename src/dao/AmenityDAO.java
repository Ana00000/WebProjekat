package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import beans.Amenity;
import beans.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private void writeInFile(Amenity a) {
		ObjectMapper mapper = new ObjectMapper();
		  
		JSONObject amenity = parserToJSON.amenityToJSONObject(a);
		JSONArray root = null;
		try {
			root = mapper.readValue(new File(contPath+"/json/amenities.json"), JSONArray.class);
		} catch (JsonParseException e2) {
			e2.printStackTrace();
		} catch (JsonMappingException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		root.add(amenity);
		
		try (FileWriter file = new FileWriter(contPath+"/json/amenities.json")) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/json/amenities.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
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
       

}