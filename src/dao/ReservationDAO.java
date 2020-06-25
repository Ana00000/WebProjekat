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
	private ApartmentDAO apartmentDAO = new ApartmentDAO();
	private CommentDAO commentDAO = new CommentDAO();
	
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
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
 
            JSONArray reservationsList = (JSONArray) obj.get("reservations");
            Iterator<JSONObject> iterator = reservationsList.iterator();
            while (iterator.hasNext()) {
            	parseReservationsObject(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
		
	public Reservation parseReservationsObject(JSONObject reservationObject) {
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		int id = Integer.parseInt(jsonToStr(reservationObject, "id"));
		Apartment rented = apartmentDAO.parseApartmentsObject((JSONObject)reservationObject.get("rented"));
		
		Date startReservation = new Date();
		try {
			startReservation = format.parse(jsonToStr(reservationObject, "startReservation"));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		int overnightStay = Integer.parseInt(jsonToStr(reservationObject, "overnightStay"));
		int fullPrice = Integer.parseInt(jsonToStr(reservationObject, "fullPrice"));
		String welcomeMessage = jsonToStr(reservationObject, "welcomeMessage");
		
		Guest guest = commentDAO.parseGuestObject((JSONObject)reservationObject.get("guest"));
		StatusReservation status = StatusReservation.valueOf(jsonToStr(reservationObject, "status"));
		
		Reservation reservation = new Reservation(id, rented, startReservation, overnightStay, fullPrice, welcomeMessage, guest, status);
		reservations.put(id, reservation);
		return reservation;
	}
	
	private String jsonToStr(JSONObject reservationObject, String par) {
		String conversation = reservationObject.get(par).toString();
		return conversation;
	}
}
