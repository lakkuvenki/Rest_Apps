package co.nit.data.flow.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

@Path("/dataflow")
public class DataFlowResource {

	@Path("/createDf")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createDataFlow(DataFlow dataFlow) {

		String resStr = "Data flow created success fully with dataflow id "
				+ dataFlow.getDataflowId();
		if (true) {
			try {
				ResponseBuilder builder = Response.status(Status.CREATED);// 201
				builder.entity(resStr);
				return builder.build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.entity("unable to create data flow object "
								+ e.getMessage()).build();
			}
		} else {
			return Response
					.status(Status.UNAUTHORIZED)
					.entity("user name and  password incorrect , try with good credentials")
					.build();
		}
	}
}
