package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Comment;
import dao.CommentDAO;

@Path("/comments")
public class CommentsService {

	@Context
	ServletContext ctx;

	public CommentsService() {

	}

	@PostConstruct
	public void init() {
		if(ctx.getAttribute("comments") == null) {
			String contextPath = ctx.getRealPath("");
			CommentDAO comments = new CommentDAO(contextPath);
			ctx.setAttribute("comments", comments);
		}
	}
	
	@PUT
	@Path("/addComment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(Comment comment, @Context HttpServletRequest request) {
		CommentDAO comments = (CommentDAO) ctx.getAttribute("comments");
		
		Comment c = comments.find(comment);
		
		if(c != null) {
			return Response.status(400).entity("Comment already exists!").build();
		}
		comments.add(comment.getId() , comment.getGuest(), comment.getApartment(), comment.getText(),comment.getGrade());
		
		return Response.status(200).build();
	}
}