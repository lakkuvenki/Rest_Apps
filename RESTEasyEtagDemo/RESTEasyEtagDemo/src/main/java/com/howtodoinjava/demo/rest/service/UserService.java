package com.howtodoinjava.demo.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.howtodoinjava.demo.rest.data.UserDatabase;

@Path("/user-service")
public class UserService 
{
	@GET
	@Path("/users/{id}")
	public Response getUserById(@PathParam("id") int id, @Context Request req) 
	{
		 CacheControl cc = new CacheControl();
	     cc.setMaxAge(86400);
	        
		Response.ResponseBuilder rb = null;
		/*
		 * Avoid trouble Avoid trouble: The granularity of dates used in HTTP headers is not as
		 *  precise as some dates used in data sources.  For example, the precision for a date in a
		 *   database row might be defined to the millisecond. However, the date in an HTTP header
		 *    field is only precise to seconds. When evaluating HTTP preconditions, if you compare a 
		 *    java.util.Date object to the date in an HTTP header, the difference in precision might 
		 *    produce unexpected results. To avoid this problem, normalize the java.util.Date object
		 *     before comparing to the date value in the HTTP header.
		 * */
		EntityTag etag = new EntityTag(UserDatabase.getLastModifiedById(id).getMinutes()+""); 
        rb = req.evaluatePreconditions(etag);
        
        if (rb != null) 
        {
            return rb.cacheControl(cc).tag(etag).build();
        }
        
        rb = Response.ok(UserDatabase.getUserById(id)).cacheControl(cc).tag(etag);
		return rb.build();
	}
	
	@PUT
	@Path("/users/{id}")
	public Response updateUserById(@PathParam("id") int id) 
	{
		UserDatabase.updateUser(id);
		return Response.status(200).build();
	}
}
