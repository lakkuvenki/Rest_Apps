package co.ind.rest.excep.mapper.app;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

@Path("/emp")
public class EmpResource {

	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response getEmp(JAXBElement<EmpRequest> empRequest) throws EmpNotfoundException {
		EmpResponse empResponse = new EmpResponse();
		try {
			if (empRequest.getValue().getId() == 1) {
				empResponse.setId(empRequest.getValue().getId());
				empResponse.setName(empRequest.getValue().getName());
			} else {
				throw new EmpNotfoundException("Wrong ID", empRequest.getValue().getId());//using exception mapper
			}
		} catch (Exception e) {
			ErrorResponse response = new ErrorResponse();
			response.setErrorCode(e.getMessage());
			return Response.ok(response).build();
		}
		return Response.ok(empResponse).build();
	}
}
