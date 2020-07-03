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

import beans.Amenity;
import beans.Comment;
import beans.User;


public class CommentDAO {
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private String contPath;
	private ParserFromJSONObject parserFromJSON= new ParserFromJSONObject();
	private ParserToJSONObject parserToJSON= new ParserToJSONObject();
	
	public CommentDAO() {
		
	}
	
	public CommentDAO(String contextPath) {
		loadComments(contextPath);
		contPath = contextPath;
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
	private void writeInFile() {
		ObjectMapper mapper = new ObjectMapper();
		  
		JSONArray root = new JSONArray();
		for(Comment c : findAll())
		{
			JSONObject user = parserToJSON.commentToJSONObject(c);
			root.add(user);
			
		}
		try (FileWriter file = new FileWriter(contPath+"/comments.json",false)) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/comments.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadComments(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/comments.json") )
        {
			
            JSONArray commentsList = (JSONArray) jsonParser.parse(reader);
            if(commentsList==null)
            	return;
            Iterator<JSONObject> iterator = commentsList.iterator();
            while (iterator.hasNext()) {
      
            	Comment comment= parserFromJSON.parseCommentsObject(iterator.next());
            	comments.put(comment.getId(), comment);
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