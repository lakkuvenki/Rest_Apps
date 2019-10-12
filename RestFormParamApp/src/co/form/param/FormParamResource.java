package co.form.param;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/form")
public class FormParamResource {
	@Context
	HttpServletResponse res;
	
	@Context
	HttpServletRequest req;

	@POST
	@Path("/param")
	public void processFormParam(@FormParam("uname") String uname,@FormParam("pwd") String pwd) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//String pwd = req.getParameter("pwd");
		out.println("User Name @ " + uname);
		out.println("User Name @ " + pwd);
		out.flush();
	}

}
