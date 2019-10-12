package com.jersey.crud.app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;

//http://localhost:8680/CustomerServiceCrud/rest/customer/post
@Path("/customer")
public class CustomerResource {
	private static final String DATA_FILE = "F:\\REST_Apps\\CustomerServiceCrud\\customer-data.txt";
	private static final String API_KEY = "012345678901234567890123456789";

	@POST
	@Path("/post")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addCustomer(InputStream customerData,
			@HeaderParam("X-My-API-Key-Token") String apiKey) {

		if (API_KEY.equals(apiKey)) {
			System.out.println(customerData);
			try {
				Customer customer = buildCustomer(null, customerData);
				long customerId = persist(customer, 0);
				System.out.println(URI.create("/" + customerId));
				ResponseBuilder builder = Response.created(URI.create("/"
						+ customerId));
				builder.status(Status.CREATED);
				builder.entity("Customer Created and customer id : "
						+ URI.create("/" + customerId));
				return builder.build();
			} catch (Exception e) {
				return Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.entity("Unable to store Customer details, please try after some time")
						.build();
			}
		} else {
			return Response
					.status(Status.UNAUTHORIZED)
					.entity("Unauthorrized access and api key not matched with server api key")
					.build();
		}
	}

	private long persist(Customer customer, long customerId) throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(DATA_FILE));

		if (customerId == 0) {
			customerId = System.currentTimeMillis();
		}

		properties.setProperty(String.valueOf(customerId),
				customer.getFirstName() + "," + customer.getLastName() + ","
						+ customer.getZipcode());
		properties.store(new FileOutputStream(DATA_FILE), null);
		return customerId;
	}

	private Customer buildCustomer(Customer customer, InputStream customerData)
			throws ParserConfigurationException, SAXException, IOException {
		if (customer == null) {
			customer = new Customer();
		}
		try {
			JAXBContext context = JAXBContext.newInstance(Customer.class);
			customer = (Customer) context.createUnmarshaller().unmarshal(
					customerData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	private Customer buildCustomerForUpdate(Customer customer,
			InputStream customerData) throws ParserConfigurationException,
			SAXException, IOException {
		if (customer == null) {
			customer = new Customer();
		}
		try {
			JAXBContext context = JAXBContext.newInstance(Customer.class);
			Customer customerFromInput = (Customer) context
					.createUnmarshaller().unmarshal(customerData);

			if (customerFromInput.getFirstName() != null) {
				customer.setFirstName(customerFromInput.getFirstName());
			}
			if (customerFromInput.getLastName() != null) {
				customer.setLastName(customerFromInput.getLastName());
			}
			if (customerFromInput.getZipcode() != null) {
				customer.setZipcode(customerFromInput.getZipcode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCustomer(@QueryParam("id") final String customerId,@Context Request request) {
		try {
			String customerDetails = loadCustomer(customerId);
			System.out.println("customerDetails: " + customerDetails);
			if (customerDetails == null) {
				return Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.entity("Customer id " + customerId
								+ "not found, please try with different id")
						.build();
			}

			final String[] details = customerDetails.split(",");
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customer.setFirstName(details[0]);
			customer.setLastName(details[1]);
			customer.setZipcode(details[2]);

			Gson gson = new Gson();
			String custJson = gson.toJson(customer);

			CacheControl cc = new CacheControl();
		    cc.setMaxAge(86400);
		    EntityTag etag = new EntityTag(Integer.toString(customer.hashCode()));
		    ResponseBuilder builder = request.evaluatePreconditions(etag);
		    // cached resource did change -> serve updated content
		    if(builder == null){
		        builder = Response.ok(custJson);
		        builder.tag(etag);
		    }
		    builder.cacheControl(cc);
		    return builder.build();
			/*ResponseBuilder builder = Response.status(Status.OK);
			CacheControl cc = new CacheControl();
		    cc.setMaxAge(86400);
		    cc.setPrivate(true);
		    
			builder.entity(custJson);
			builder.cacheControl(cc);
			return builder.build();*/
		} catch (Exception e) {
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("server error")
					.build();
		}
	}

	@PUT
	@Path("/put/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateCustomer(@PathParam("id") String customerId,
			InputStream input, @HeaderParam("X-My-API-Key-Token") String apiKey) {
		if (API_KEY.equals(apiKey)) {
			try {
				String customerDetails = loadCustomer(customerId);
				if (customerDetails == null) {
					return Response
							.status(Status.INTERNAL_SERVER_ERROR)
							.entity("Customer id " + customerId
									+ "not found, please try with different id")
							.build();
				}
				String[] details = customerDetails.split(",");
				Customer customer = new Customer();
				customer.setFirstName(details[0]);
				customer.setLastName(details[1]);
				customer.setZipcode(details[2]);
				buildCustomerForUpdate(customer, input);
				persist(customer, Long.valueOf(customerId));
				return Response.status(Status.NO_CONTENT).entity("Updated Customer and customer id : "+ customerId).build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Unauthorrized access and api key not matched with server api key")
					.build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteCustomer(@PathParam("id") String customerId,
			@HeaderParam("X-My-API-Key-Token") String apiKey) {
		if (API_KEY.equals(apiKey)) {
			try {
				Properties properties = new Properties();
				properties.load(new FileInputStream(DATA_FILE));
				String customerDetails = properties.getProperty(customerId);
				if (customerDetails == null) {
					return Response
							.status(Status.INTERNAL_SERVER_ERROR)
							.entity("Customer id " + customerId
									+ "not found, please try with different id")
							.build();
				}
				properties.remove(customerId);
				properties.store(new FileOutputStream(DATA_FILE), null);
				return Response
						.status(Status.NO_CONTENT)
						.entity("Deleted Customer and customer id : "
								+ customerId).build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Unauthorrized access and api key not matched with server api key")
					.build();
		}
	}

	private String loadCustomer(String customerId) {
		Properties properties = new Properties();
		String value = "";
		try {
			properties.load(new FileInputStream(DATA_FILE));
			value = properties.getProperty(customerId);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}