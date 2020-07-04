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

import beans.Guest;
import beans.Reservation;
import beans.StatusReservation;

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
	
	public void add(int id, int rented, Date startReservation, int overnightStay, int fullPrice, String welcomeMessage,
			Guest guest, StatusReservation status) {
		Reservation r = reservations.get(id);
		if(r == null) {
			r = new Reservation(id, rented, startReservation, overnightStay, fullPrice, welcomeMessage, guest, status);
			reservations.put(id, r);
			writeInFile();
		}
	}
	
	public Collection<Reservation> findAll() {
		return reservations.values();
	}

	@SuppressWarnings("unchecked")
	public void writeInFile() {
		JSONArray root = new JSONArray();
		for(Reservation r: findAll())
		{
			JSONObject reservation = parserToJSON.reservationToJSONObject(r);
			root.add(reservation);
			
		}
		
		try (FileWriter file = new FileWriter(contPath+"/reservations.json",false)) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/reservations.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadReservations(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/reservations.json") )
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
