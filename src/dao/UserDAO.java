 package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Gender;
import beans.Roles;
import beans.User;

public class UserDAO {

	private HashMap<String, User> users = new HashMap<String, User>();
	private String contPath;
	private ParserFromJSONObject parserFromJSON= new ParserFromJSONObject();
	private ParserToJSONObject parserToJSON= new ParserToJSONObject();
	
	
	public UserDAO() {
	}
	
	public UserDAO(String contextPath) {
		loadUsers(contextPath);
		contPath = contextPath;
	}
	
	public User find(User u) {
		for(User user: users.values())
		{
			if(u.getUsername().equals(user.getUsername()))
				return user;
		}
		
		return null;
	}
	
	public User find(String username) {
		
		if (!users.containsKey(username)) {
			return null;
		}
		
		User user = users.get(username);
		
        
		return user;
	}
	
	public User find(String username, String password) {
        if (!users.containsKey(username)) {
            return null;
        }
        User user = users.get(username);
        if (!user.getPassword().equals(password)) {
            return null;
        }
        

        return user;
    }
	
	public User add(String username, String password, String name, String surname, Gender gender, Roles role) {
		User u = users.get(username);
		if(u == null) {
			u = new User(username, password, name, surname, gender, role);
			users.put(username, u);
			writeInFile();
		}
		
		return u;
	}
	

	public HashMap<String, User> getUsers() {
		return users;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}

	@SuppressWarnings("unchecked")
	private void writeInFile() {
		ObjectMapper mapper = new ObjectMapper();
		  
		
		JSONArray root = new JSONArray();
		for(User a : findAll())
		{
			JSONObject user = parserToJSON.userToJSONObject(a);
			root.add(user);
			
		}
		
		try (FileWriter file = new FileWriter(contPath+"/users.json",false)) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/users.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

	@SuppressWarnings("unchecked")
	public void loadUsers(String contextPath)  {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/users.json") )
        {
            JSONArray usersList = (JSONArray) jsonParser.parse(reader);
            if(usersList==null)
            	return;
            Iterator<JSONObject> iterator = usersList.iterator();
            while (iterator.hasNext()) {
      
            	User user=parserFromJSON.parseUsersObject(iterator.next());
        		users.put(user.getUsername(), user);

            }
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (ParseException e) {
			e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}

	public User set(String username, String password, String name, String surname, Gender gender, Roles role) {
		User newUser = users.get(username);
		newUser = new User(username, password, name, surname, gender, role);

//		users.remove(oldUser);
		users.put(username, newUser);
		writeInFile();
		
		return newUser;
	}
		

}