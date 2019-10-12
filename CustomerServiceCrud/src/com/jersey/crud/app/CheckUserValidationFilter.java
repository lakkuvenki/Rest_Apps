package com.jersey.crud.app;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Provider
public class CheckUserValidationFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		System.out.println("@ In container request filter @@");
		String username = request.getHeaderValue("username");
		String pwd = request.getHeaderValue("password");
		if (username != null &&  pwd != null) {
			if (username.equals(pwd)) {
				System.out.println("Validation Suucess");
			} else {
				throw new WebApplicationException(new Exception(
						"@@ Authentication Failed @ "), Response
						.status(Response.Status.UNAUTHORIZED)
						.entity("@@ Authentication Failed @ ").build());
			}
		} else {
			throw new WebApplicationException(
					new Exception(
							"@@ user name and password should not be null/empty @ "),
					Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity("user name and password should not be null/empty")
							.build());

		}
		return request;
	}
}
