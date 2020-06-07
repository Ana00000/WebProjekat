package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.Comment;

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

	private void loadComments(String contextPath) {
		/*
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/comments.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String idStr = st.nextToken().trim();
					int id = Integer.parseInt(idStr);
					String guestStr = st.nextToken().trim();
					Guest guest = Guest.valueOf(guestStr);
					String apartmentStr = st.nextToken().trim();
					Apartment apartment = Apartment.valueOf(apartmentStr);
					String text;
					double grade;
					String idStr = st.nextToken().trim();
					int id = Integer.parseInt(idStr);
					String name = st.nextToken().trim();
					amenities.put(id, new Amenity(id, name));
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