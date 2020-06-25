package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import beans.Apartment;
import beans.Comment;
import beans.Gender;
import beans.Guest;
import beans.Reservation;
import beans.Roles;

public class CommentDAO {
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private ApartmentDAO apartmentDAO = new ApartmentDAO();
	
	public CommentDAO() {
		
	}
	
	public CommentDAO(String contextPath) {
		loadComments(contextPath);
	}

	public Comment find(Comment c) {
		for(Comment comment: comments.values())
		{
			if(c.getId() == (comment.getId()))
				return comment;
		}
		
		return null;
	}
	
	public Collection<Comment> findAll() {
		return comments.values();
	}
	
	@SuppressWarnings("unchecked")
	public void loadComments(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/json/comments.json") )
        {
			JSONObject obj =(JSONObject) jsonParser.parse(reader);
 
            JSONArray commentsList = (JSONArray) obj.get("comments");
            Iterator<JSONObject> iterator = commentsList.iterator();
            while (iterator.hasNext()) {
      
            	parseCommentsObject(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

	public Comment parseCommentsObject(JSONObject commentObject) {

		int id = Integer.parseInt(jsonToStr(commentObject, "id"));    
		
		Guest guest = parseGuestObject((JSONObject)commentObject.get("guest"));
        
        Apartment apartment = apartmentDAO.parseApartmentsObject((JSONObject)commentObject.get("apartment"));
        
		String text = jsonToStr(commentObject, "text");
		
		double grade = Double.parseDouble(jsonToStr(commentObject, "grade")); 

		Comment comment = new Comment(id, guest, apartment, text, grade);
		comments.put(id, comment);
		
		return comment;
	}
	
	private String jsonToStr(JSONObject commentObject, String par) {
		String conversation = commentObject.get(par).toString();
		return conversation;
	}
	
	@SuppressWarnings("unchecked")
	public Guest parseGuestObject(JSONObject guestObject ) {
		
		String username = jsonToStr(guestObject, "username");
		String password = jsonToStr(guestObject, "password");
		String name = jsonToStr(guestObject, "name");
		String surname = jsonToStr(guestObject, "surname");
		Gender gender = Gender.valueOf(jsonToStr(guestObject, "gender"));
		Roles role = Roles.valueOf(jsonToStr(guestObject, "role"));
		
		List<Apartment> rented = new ArrayList<Apartment>();
		JSONArray apartmentsList = (JSONArray) guestObject.get("rented");
		Iterator<JSONObject> it = apartmentsList.iterator();
        while (it.hasNext()) {
        	JSONObject json = (JSONObject)it.next();
        	rented.add(apartmentDAO.parseApartmentsObject((JSONObject) json.get("apartments")));
        }
        
        List<Reservation> reservations=null;		//SRECNO
		
		return new Guest(username, password, name, surname, gender, role, rented, reservations);
	}

}