package com.jersey.crud.app;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class UserValidationOut implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest request,
			ContainerResponse response) {
		System.out.println("@ In container response filter @@");
		response.getHttpHeaders().add("success",
				"validation success and added output to response object");
		return response;
	}

}
