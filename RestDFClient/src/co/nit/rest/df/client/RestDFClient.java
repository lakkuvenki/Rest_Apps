package co.nit.rest.df.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class RestDFClient {
	public static void main(String[] args) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://localhost:8680/RestDataflowsApp/rest/dataflow/createDf");
			
			DataFlow dataFlow = new DataFlow();
			dataFlow.setDataflowId("DF-1");
			dataFlow.setDataflowName("SOAP");
			dataFlow.setDataflowSeg("AND");
			dataFlow.setSrcStepId(1001);
			dataFlow.setTrgtStepId(1002);
			
			Gson gson = new Gson();
			String dfStr = gson.toJson(dataFlow);
			
			StringEntity entity = new StringEntity(dfStr,ContentType.APPLICATION_JSON);
			post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			Header[] arrHeader = response.getAllHeaders();
			for(Header header : arrHeader) {
				System.out.println(header.getName() + " : " + header.getValue());
			}
			
			HttpEntity entity2 = response.getEntity();
			StatusLine line = response.getStatusLine();
			System.out.println(line.getStatusCode());
			String resEntity = EntityUtils.toString(entity2);
			System.out.println(resEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
