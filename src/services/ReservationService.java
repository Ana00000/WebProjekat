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

import beans.Reservation;
import dao.ReservationDAO;

@Path("/reservations")
public class ReservationService {

	@Context
	ServletContext ctx;

	public ReservationService() {

	}

	@PostConstruct
	public void init() {
		if(ctx.getAttribute("reservations") == null) {
			String contextPath = ctx.getRealPath("");
			ReservationDAO reservations = new ReservationDAO(contextPath);
			ctx.setAttribute("reservations", reservations);
		}
	}
	
	@PUT
	@Path("/addReservation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReservation(Reservation reservation, @Context HttpServletRequest request) {
		System.out.println("usao");
		ReservationDAO reservations = (ReservationDAO) ctx.getAttribute("reservations");
		
		Reservation r = reservations.find(reservation.getId());
		
		if(r != null) {
			return Response.status(400).entity("Reservation already h!").build();
		}
		reservations.add(reservation.getId(), reservation.getRented(), reservation.getStartReservation(), reservation.getOvernightStay(), 
				reservation.getWelcomeMessage(), reservation.getGuest(), reservation.getStatus());
		
		return Response.status(200).build();
	}
}