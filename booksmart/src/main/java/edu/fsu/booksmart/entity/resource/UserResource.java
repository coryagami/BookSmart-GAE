package edu.fsu.booksmart.entity.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.fsu.booksmart.entity.User;
import edu.fsu.booksmart.entity.UserDevice;
import edu.fsu.booksmart.entity.emf.UserEMF;

@Path("/api/user")
@Produces("application/json")
public class UserResource {
	
	@POST
	@Path("/add")
	public Response addNewUser(@FormParam("device_id") String deviceId, @FormParam("version") String version, @FormParam("operating_system") String os) {
		UserDevice device = new UserDevice();
		device.setDeviceId(deviceId);
		device.setVersion(version);
		device.setOperatingSystem(os);
		User user = UserEMF.getUserByDevice(device);
		if(user == null) {
			user = new User();
			user.setDevice(device);
			return Response.status(Status.CREATED).entity(user).build();
		} else {
			return Response.ok(user).build();
		}
	}

}
