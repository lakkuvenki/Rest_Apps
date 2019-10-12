package co.nit.apache.http.client.post;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class AddCustomerRequest {
	//lakkuvenki@gmail.com
	private static String POST_URI = "http://localhost:8680/CustomerServiceCrud/rest/customer/post";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//HttpClient postClient = new DefaultHttpClient();
		//HttpClient postClient = HttpClients.createDefault();
		PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setDefaultMaxPerRoute(1);
        pool.setMaxTotal(1);
        
        BasicHttpClientConnectionManager singleThread = new BasicHttpClientConnectionManager();
        final CloseableHttpClient postClient = HttpClients.custom().setConnectionManager(singleThread).build();
		try {
			HttpPost postRequest = new HttpPost(POST_URI);
			postRequest.addHeader("X-My-API-Key-Token", "012345678901234567890123456789");
			postRequest.addHeader("username", "lakku");
			postRequest.addHeader("password", "lakku");
			//postRequest.addHeader("Content-Type","application/json");
			Customer customer = new Customer();
			customer.setFirstName("ABC1");
			customer.setLastName("BCD1");
			customer.setZipcode("5000321");
			
			JAXBContext context = JAXBContext.newInstance(Customer.class);
			StringWriter writer = new StringWriter();
			context.createMarshaller().marshal(customer, writer);
			System.out.println(writer.toString());
			HttpEntity entity = new ByteArrayEntity(writer.toString().getBytes(),ContentType.APPLICATION_JSON);
			postRequest.setEntity(entity);
			
			CloseableHttpResponse response = postClient.execute(postRequest);
			Header[] resHeaders = response.getAllHeaders();
			for(Header header : resHeaders) {
				System.out.println(header.getName() + ":" + header.getValue());
			}
			System.out.println(response.getStatusLine()); 
			String resEntity = EntityUtils.toString(response.getEntity());
			System.out.println(resEntity);
			//EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postClient.getConnectionManager().shutdown();
		}
	}
}