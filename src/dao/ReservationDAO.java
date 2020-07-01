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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Reservation;


public class ReservationDAO {

	private Map<Integer, Reservation> reservations = new HashMap<Integer, Reservation>();
	private String contPath;
	private ParserFromJSONObject parserFromJSON= new ParserFromJSONObject();
	private ParserToJSONObject parserToJSON= new ParserToJSONObject();
	
	public ReservationDAO() {
		
	}
	
	public ReservationDAO(String contextPath) {
		loadReservations(contextPath);
		contPath = contextPath;
	}
	
	public Reservation find(Reservation r) {
		for(Reservation reservation: reservations.values())
		{
			if(r.getId() == (reservation.getId()))
				return reservation;
		}
		
		
		return null;
	}
	
	public Collection<Reservation> findAll() {
		return reservations.values();
	}

	@SuppressWarnings("unchecked")
	public void writeInFile(Reservation r) {
		ObjectMapper mapper = new ObjectMapper();
		  
		JSONObject reservation = parserToJSON.reservationToJSONObject(r);
		JSONArray root = null;
		try {
			root = mapper.readValue(new File(contPath+"/json/reservations.json"), JSONArray.class);
		} catch (JsonParseException e2) {
			e2.printStackTrace();
		} catch (JsonMappingException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		root.add(reservation);
		
		try (FileWriter file = new FileWriter(contPath+"/json/reservations.json")) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/json/reservations.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadReservations(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/json/reservations.json") )
        {
 
            JSONArray reservationsList = (JSONArray) jsonParser.parse(reader);
            if(reservationsList==null )
            	return;
            Iterator<JSONObject> iterator = reservationsList.iterator();
            while (iterator.hasNext()) {
            	Reservation reservation= parserFromJSON.parseReservationsObject(iterator.next());
            	reservations.put(reservation.getId(), reservation);
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
