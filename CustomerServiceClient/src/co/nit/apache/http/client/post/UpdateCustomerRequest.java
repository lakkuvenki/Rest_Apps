package co.nit.apache.http.client.post;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class UpdateCustomerRequest {

	private static String PUT_URI = "http://localhost:8680/CustomerServiceCrud/rest/customer/put";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//HttpClient postClient = new DefaultHttpClient();
		HttpClient postClient = HttpClients.createDefault();
		try {
			HttpPut putRequest = new HttpPut(PUT_URI+"/1525832964328");
			putRequest.addHeader("X-My-API-Key-Token", "012345678901234567890123456789");
			Customer customer = new Customer();
			//customer.setCustomerId("1520687589213");
			customer.setFirstName("Lakku Vijay Kumar");
			customer.setLastName("Reddy");
			customer.setZipcode("500016");
			
			JAXBContext context = JAXBContext.newInstance(Customer.class);
			StringWriter writer = new StringWriter();
			context.createMarshaller().marshal(customer, writer);
			
			HttpEntity entity = new ByteArrayEntity(writer.toString().getBytes());
			putRequest.setEntity(entity);
			
			HttpResponse response = postClient.execute(putRequest);
			System.out.println(response.getStatusLine()); 
			String resEntity = EntityUtils.toString(response.getEntity());
			System.out.println(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postClient.getConnectionManager().shutdown();
		}
	}
}