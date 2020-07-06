package dao;

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

import beans.Comment;
import beans.Guest;

public class CommentDAO {
	private Map<String, Comment> comments = new HashMap<String, Comment>();
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
		if (!comments.containsKey(c.getId())) {
			return null;
		}
		
		Comment comment = comments.get(c.getId());
		
		return comment;
	}
	
	public void add(String id, Guest guest, String apartment, String text, double grade) {
		Comment c = comments.get(id);
		if(c == null) {
			c = new Comment(id, guest, apartment, text, grade);
			comments.put(id, c);
			writeInFile();
		}
	}
	
	public Collection<Comment> findAll() {
		return comments.values();
	}
	
	@SuppressWarnings("unchecked")
	private void writeInFile() {
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

	public void approval(Comment comment) {
		Comment commentOld=comments.get(comment.getId());
		commentOld.setVisible(comment.getVisible());
		comments.put(comment.getId(), commentOld);
		writeInFile();
	}

}