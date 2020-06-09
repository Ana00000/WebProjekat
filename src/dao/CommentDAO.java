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

import beans.Apartment;
import beans.Comment;
import beans.Guest;

public class CommentDAO {
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	
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
		try (FileReader reader = new FileReader(contextPath+"/comments.json") )
        {
			JSONObject obj =(JSONObject) jsonParser.parse(reader);
 
            JSONArray commentsList =(JSONArray) obj.get("comments");
            Iterator<JSONObject> iterator = commentsList.iterator();
            while (iterator.hasNext()) {
      
            	parseCommentsObject(iterator.next());
            }
    		System.out.println(comments);
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}

	private void parseCommentsObject(JSONObject commentObject) {
		//PROVERAVAMO JOSSSSSS
		System.out.println(commentObject);

		int id = Integer.parseInt(jsonToStr(commentObject, "id"));    
		
		Guest guest = null;
        
        Apartment apartment = null;
        
		String text = jsonToStr(commentObject, "text");
		
		double grade = Double.parseDouble(jsonToStr(commentObject, "grade")); 

		comments.put(id, new Comment(id, guest, apartment, text, grade));
	}
	
	private String jsonToStr(JSONObject commentObject, String par) {
		String conversation = commentObject.get(par).toString();
		return conversation;
	}
}