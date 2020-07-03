package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
			String contextPath = ctx.getRealPath("");
			UserDAO users = new UserDAO(contextPath);
			ctx.setAttribute("users", users);
		}
	}

	@PUT
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registration(User user, @Context HttpServletRequest request) {
		//prvi login je ustvari kao registracija jer se korisnici ne cuvaju ni u fajlu ni u bazi
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		
		User logUser = users.find(user.getUsername());
		if(logUser != null) {
			return Response.status(400).entity("User already exist!").build();
		}
		
		User u = users.add(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getRole());
		request.getSession().setAttribute("user", u);
		return Response.status(200).build();
	}
	
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user, @Context HttpServletRequest request) {
        UserDAO userDao = (UserDAO) ctx.getAttribute("users");
        User loggedUser = userDao.find(user.getUsername(), user.getPassword());
        if (loggedUser == null) {
            return Response.status(400).entity("Invalid username and/or password").build();
        }
        request.getSession().setAttribute("user", user);
        return Response.status(200).build();
    }
	
	@GET
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            session.invalidate();
        }
        return Response.status(200).build();
    }
	
	@GET
	@Path("/loggedUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User loggedUser(@Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		User foundUser = (User) request.getSession().getAttribute("user");
		User logUser = users.find(foundUser.getUsername());
		return logUser;
	}
	
	@PUT
	@Path("/changeData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeData(User user, @Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		
		User logUser = users.find(user.getUsername());
		
		User u = users.set(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getRole());
		request.getSession().setAttribute("user", u);
		return Response.status(200).build();
	}
	
	@POST
	@Path("/infoHost")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response infoHost(User user, @Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		User logUser = users.find(user.getUsername());
		
		if(!logUser.getRole().toString().equals("HOST"))
		{
			 return Response.status(403).entity("You are not a host!").build();
		}
		
        return Response.status(200).build();
	}
	
	@POST
	@Path("/infoAdmin")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response infoAdmin(User user, @Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		User logUser = users.find(user.getUsername());
		
		if(!logUser.getRole().toString().equals("ADMIN"))
		{
			 return Response.status(403).entity("You are not an admin!").build();
		}
		
        return Response.status(200).build();
	}
}
