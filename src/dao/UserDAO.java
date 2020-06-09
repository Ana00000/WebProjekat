package dao;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import beans.Gender;
import beans.Roles;
import beans.User;

public class UserDAO {

	private Map<String, User> users = new HashMap<String, User>();
	
	
	public UserDAO() {
		
	}
	
	public UserDAO(String contextPath) {
		loadUsers(contextPath);
	}
	
	public User find(User u) {
		for(User user: users.values())
		{
			if(u.getUsername().equals(user.getUsername()))
				return user;
		}
		
		return null;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}


	@SuppressWarnings("unchecked")
	public void loadUsers(String contextPath) {
			JSONParser jsonParser = new JSONParser();
			try (FileReader reader = new FileReader(contextPath+"/users.json") )
	        {
				JSONObject obj =(JSONObject) jsonParser.parse(reader);
	 
	            JSONArray usersList =(JSONArray) obj.get("user");
	            Iterator<JSONObject> iterator = usersList.iterator();
	            while (iterator.hasNext()) {
	      
	            	parseUsersObject(iterator.next());
	            }
	            
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
		
		private void parseUsersObject(JSONObject userObject) {
			
	        String username = jsonToStr(userObject, "username");
			String password = jsonToStr(userObject, "password");
			String name = jsonToStr(userObject, "name");
			String surname = jsonToStr(userObject, "surname");
			Gender gender = Gender.valueOf(jsonToStr(userObject, "gender"));
			Roles role = Roles.valueOf(jsonToStr(userObject, "role"));
			
			users.put(username, new User(username, password, name, surname, gender, role));
		}
		
		private String jsonToStr(JSONObject userObject, String par) {
			String conversation = userObject.get(par).toString();
			return conversation;
		}
}