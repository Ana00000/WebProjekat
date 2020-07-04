package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Amenity;
import beans.User;
import dao.AmenityDAO;
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
		}else if(ctx.getAttribute("amenities") == null) {
			String contextPath = ctx.getRealPath("");
			AmenityDAO amenities = new AmenityDAO(contextPath);
			ctx.setAttribute("amenities", amenities);
		}
	}

	@PUT
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registration(User user, @Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		
		User logUser = users.find(user.getUsername());
		if(logUser != null) {
			return Response.status(400).entity("User already exist!").build();
		}
		User u = users.add(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getRole());
		if(!u.getRole().toString().equals("HOST"))
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
            return Response.status(400).entity("Invalid username and/or password.").build();
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
			 return Response.status(403).entity("Access denied, you are not a host!").build();
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
			 return Response.status(403).entity("Access denied, you are not an admin!").build();
		}
		
        return Response.status(200).build();
	}
	
	@POST
	@Path("/guestReservations")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response guestReservations(User user, @Context HttpServletRequest request) {
		UserDAO users = (UserDAO) ctx.getAttribute("users");
		User logUser = users.find(user.getUsername());
		
		if(!logUser.getRole().toString().equals("GUEST"))
		{
			 return Response.status(403).entity("Access denied,you are not a guest!").build();
		}
		
        return Response.status(200).build();
	}
	
	
	@PUT
	@Path("/addAmenity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAmenity(Amenity amenity, @Context HttpServletRequest request) {
		AmenityDAO amenities = (AmenityDAO) ctx.getAttribute("amenities");
		
		Amenity ame = amenities.find(amenity.getId());
		if(ame != null) {
			return Response.status(400).entity("Id of an amenity already exists!").build();
		}
		amenities.add(amenity.getId(), amenity.getName());
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/setAmenity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setAmenity(Amenity amenity, @Context HttpServletRequest request) {
		AmenityDAO amenities = (AmenityDAO) ctx.getAttribute("amenities");
		
		Amenity ame = amenities.find(amenity.getId());
		if(ame == null) 
			return Response.status(400).entity("Id shouldn't be changed!").build();
		
		amenities.set(amenity.getId(), amenity.getName());
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/deleteAmenity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAmenity(Amenity amenity, @Context HttpServletRequest request) {
		AmenityDAO amenities = (AmenityDAO) ctx.getAttribute("amenities");
		amenities.remove(amenity);
		return Response.status(200).build();
	}
}
