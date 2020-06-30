package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import beans.Guest;
import beans.Reservation;
import beans.StatusReservation;

public class ReservationDAO {

	private Map<Integer, Reservation> reservations = new HashMap<Integer, Reservation>();
	private ParserToJSONObject parserJSON= new ParserToJSONObject();
	
	public ReservationDAO() {
		
	}
	
	public ReservationDAO(String contextPath) {
		loadReservations(contextPath);
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
	public void loadReservations(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/json/reservations.json") )
        {
 
            JSONArray reservationsList = (JSONArray) jsonParser.parse(reader);
            if(reservationsList==null )
            	return;
            Iterator<JSONObject> iterator = reservationsList.iterator();
            while (iterator.hasNext()) {
            	Reservation reservation= parserJSON.parseReservationsObject(iterator.next());
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
