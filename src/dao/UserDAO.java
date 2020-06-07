package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Amenity;
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

	private void loadUsers(String contextPath) {
		/*
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					String name = st.nextToken().trim();
					String surname = st.nextToken().trim();
					String genderStr = st.nextToken().trim();
					Gender gender = Gender.valueOf(genderStr);
					String roleStr = st.nextToken().trim();
					Roles role = Roles.valueOf(roleStr);
					users.put(username, new User(username, password, name, surname, gender, role));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		*/
	}

}