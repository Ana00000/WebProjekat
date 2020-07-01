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
	private void writeInFile(Comment c) {
		ObjectMapper mapper = new ObjectMapper();
		  
		JSONObject comment = parserToJSON.commentToJSONObject(c);
		JSONArray root = null;
		try {
			root = mapper.readValue(new File(contPath+"/json/comments.json"), JSONArray.class);
		} catch (JsonParseException e2) {
			e2.printStackTrace();
		} catch (JsonMappingException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		root.add(comment);
		
		try (FileWriter file = new FileWriter(contPath+"/json/comments.json")) 
        {
            try {
				file.write(root.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println(contPath+"/json/comments.json");
            System.out.println("Successfully updated json object to file...!!");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadComments(String contextPath) {
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(contextPath+"/json/comments.json") )
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