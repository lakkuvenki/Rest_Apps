package co.ind.message.reader.writer.intercept;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/resource")
public class MyResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MyBean getMyBean() {
		return new MyBean("Hello World!", 42);
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postMyBean(MyBean myBean) {
		System.out.println(myBean);
		return Response.status(Status.OK).entity(myBean.anyString).build();
	}
}
