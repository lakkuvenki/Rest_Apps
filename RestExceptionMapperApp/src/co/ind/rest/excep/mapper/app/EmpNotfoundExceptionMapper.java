package co.ind.rest.excep.mapper.app;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmpNotfoundExceptionMapper implements  ExceptionMapper<EmpNotfoundException> {
 
    public EmpNotfoundExceptionMapper() {
    }
     
    @Override
    public Response toResponse(EmpNotfoundException empNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorId(empNotFoundException.getErrorId());
        errorResponse.setErrorCode(empNotFoundException.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).type(MediaType.APPLICATION_XML).build();
    }
 
}
