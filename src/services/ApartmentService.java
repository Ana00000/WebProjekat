package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Apartment;
import dao.ApartmentDAO;

@Path("/apartments")
public class ApartmentService {

	@Context
	ServletContext ctx;

	public ApartmentService() {

	}

	@PostConstruct
	public void init() {
		if(ctx.getAttribute("apartments") == null) {
			String contextPath = ctx.getRealPath("");
			ApartmentDAO apartments = new ApartmentDAO(contextPath);
			ctx.setAttribute("apartments", apartments);
		}
	}
	
	@PUT
	@Path("/selectedApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectedApartment(Apartment id, @Context HttpServletRequest request) {
		ApartmentDAO apartments = (ApartmentDAO) ctx.getAttribute("apartments");
		Apartment a = apartments.find(id.getId());

		if(a == null) 
			return Response.status(400).entity("Apartment with this id doesn't exists!").build();
		
		request.getSession().setAttribute("apartment", a);
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/deleteApartment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApartment(Apartment id, @Context HttpServletRequest request) {
		ApartmentDAO apartments = (ApartmentDAO) ctx.getAttribute("apartments");
		Apartment ap = apartments.find(id.getId());
		apartments.remove(ap);
		return Response.status(200).build();
	}
	
	
	@GET
	@Path("/currentApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Apartment currentApartment(@Context HttpServletRequest request) {
		Apartment apartmentFound = (Apartment) request.getSession().getAttribute("apartment");
		return apartmentFound;
	}
	
	@PUT
	@Path("/setApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setApartment(Apartment apartment, @Context HttpServletRequest request) {
		ApartmentDAO apartments = (ApartmentDAO) ctx.getAttribute("apartments");
		
		Apartment a = apartments.find(apartment.getId());
		if(a == null) 
			return Response.status(400).entity("Id shouldn't be changed!").build();
		
		apartments.set(apartment);
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/addApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addApartment(Apartment apartment, @Context HttpServletRequest request) {
		ApartmentDAO apartments = (ApartmentDAO) ctx.getAttribute("apartments");
		
		Apartment a = apartments.find(apartment.getId());
		if(a != null) {
			return Response.status(400).entity("Unsuccessful adding, id is not unique!").build();
		}
		apartments.add(apartment.getId(), apartment.getType(), apartment.getNbrRooms(), apartment.getNbrGuests(), 
				apartment.getHost(), apartment.getPricePerNight(), apartment.getStatus());
		
		return Response.status(200).build();
	}
}
