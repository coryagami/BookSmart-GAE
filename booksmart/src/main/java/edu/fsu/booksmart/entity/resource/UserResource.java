package edu.fsu.booksmart.entity.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api/user")
@Produces("application/json")
public class UserResource {
	
	@GET
	@Path("/id/{user_id}")
	public Response getUserById(@FormParam("user_id") Integer user_id) {
		return Response.ok().build();
	}

}
