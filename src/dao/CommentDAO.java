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

import beans.Comment;


public class CommentDAO {
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private ParserToJSONObject parserJSON= new ParserToJSONObject();
	
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
			
            JSONArray commentsList = (JSONArray) jsonParser.parse(reader);
            if(commentsList==null)
            	return;
            Iterator<JSONObject> iterator = commentsList.iterator();
            while (iterator.hasNext()) {
      
            	Comment comment= parserJSON.parseCommentsObject(iterator.next());
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