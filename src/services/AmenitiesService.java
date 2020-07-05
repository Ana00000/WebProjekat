package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Amenity;
import dao.AmenityDAO;

@Path("/amenities")
public class AmenitiesService {

	@Context
	ServletContext ctx;

	public AmenitiesService() {

	}

	@PostConstruct
	public void init() {
		if(ctx.getAttribute("amenities") == null) {
			String contextPath = ctx.getRealPath("");
			AmenityDAO amenities = new AmenityDAO(contextPath);
			ctx.setAttribute("amenities", amenities);
			System.out.println(amenities);
		}
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