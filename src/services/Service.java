package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import dao.UserDAO;

@Path("")
public class Service {

	@Context
	ServletContext ctx;

	public Service() {

	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("users") == null) {
			UserDAO users = new UserDAO();
			ctx.setAttribute("users", users);
		}
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user, @Context HttpServletRequest request) {
		//prvi login je ustvari kao registracija jer se korisnici ne cuvaju ni u fajlu ni u bazi
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		
		User logUser = users.find(user.getUsername());
		if(logUser != null) {
			return Response.status(400).entity("Korisnik je vec registrovan!").build();
		}
		
		User u = users.add(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getRole());
		request.getSession().setAttribute("user", u);
		return Response.status(200).build();
	}
}
