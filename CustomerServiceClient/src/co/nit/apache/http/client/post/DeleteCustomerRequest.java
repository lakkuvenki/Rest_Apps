package co.nit.apache.http.client.post;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DeleteCustomerRequest {

	private static String DELELTE_URI = "http://localhost:8680/CustomerServiceCrud/rest/customer/delete";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//HttpClient getClient = new DefaultHttpClient();
		HttpClient getClient = HttpClients.createDefault();
		try {
			HttpDelete deleteRequest = new HttpDelete(DELELTE_URI +"/1536331973038");
			deleteRequest.addHeader("X-My-API-Key-Token", "012345678901234567890123456789");
			HttpResponse response = getClient.execute(deleteRequest);
			System.out.println(response.getStatusLine()); 
			String resEntity = EntityUtils.toString(response.getEntity());
			System.out.println(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClient.getConnectionManager().shutdown();
		}
	}

}
