package co.ind.rest.excep.mapper.client.app;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import co.ind.rest.excep.mapper.app.EmpRequest;
import co.ind.rest.excep.mapper.app.EmpResponse;
import co.ind.rest.excep.mapper.app.ErrorResponse;

public class ExcepMapperClient {

	public static void main(String[] args) throws Exception {
		// testAddCustomer();
		jerseyClient();
	}

	private static void testAddCustomer() throws IOException, HttpException {
		EmpRequest request = new EmpRequest();
		request.setId(2);
		request.setName("PK");
		StringWriter writer = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(EmpRequest.class);
			context.createMarshaller().marshal(request, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		PostMethod postMethod = new PostMethod(
				"http://localhost:8680/RestExceptionMapperApp/rest/emp/get");
		RequestEntity entity = new InputStreamRequestEntity(
				new ByteArrayInputStream(writer.toString().getBytes()),
				MediaType.APPLICATION_XML);
		postMethod.setRequestEntity(entity);
		HttpClient client = new HttpClient();
		try {
			int result = client.executeMethod(postMethod);
			
			System.out.println("Response status code: " + result);
			System.out.println("Response headers:");
			HttpState httpState = client.getState();
			System.out.println(httpState);
			Header[] headers = postMethod.getResponseHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i].toString());
			}

		} finally {
			postMethod.releaseConnection();
		}
	}

	public static void jerseyClient() {
		String uri = "http://localhost:8680/RestExceptionMapperApp/rest/emp/get";
		EmpRequest request = new EmpRequest();
		// set id as 1 for OK response
		request.setId(2);
		request.setName("PK");
		try {

			Client client = Client.create();//
			WebResource r = client.resource(uri);
			ClientResponse response = r.type(MediaType.APPLICATION_XML).post(
					ClientResponse.class, request);// builder design pattern to
													// invoke rest service
			System.out.println(response.getStatus());
			if (response.getStatus() == 200) {
				EmpResponse empResponse = response.getEntity(EmpResponse.class);
				System.out.println(empResponse.getId() + "::"
						+ empResponse.getName());
			} else {
				ErrorResponse exc = response.getEntity(ErrorResponse.class);
				System.out.println(exc.getErrorCode());
				System.out.println(exc.getErrorId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
