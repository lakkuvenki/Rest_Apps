package co.ind.message.reader.writer.intercept;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JSONClient {
	public static void main(String[] args) {

		MyBean bean = new MyBean();
		bean.anyNumber = 10;
		bean.anyString = "Hi";

		StringWriter writer = new StringWriter();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(MyBean.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(bean, writer);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}

		HttpPost postMethod = new HttpPost("http://localhost:8680/RestRederWriterApp/rest/resource");
		StringEntity entity = null;
		try {
			entity = new StringEntity(writer.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		postMethod.setHeader("Content-Type", "application/xml");
		postMethod.setHeader("Accept", "application/xml");

		postMethod.setEntity(entity);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		try {
			try {
				response = client.execute(postMethod);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// now start
				// invocation of
				// server
			StatusLine line = response.getStatusLine();
			HttpEntity httpEntity = response.getEntity();
			try {
				String name = EntityUtils.toString(httpEntity);
				System.out.println(name);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Response status code: " + line.getStatusCode());
			System.out.println("Response headers:");

			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i].toString());
			}

		} finally {
			postMethod.releaseConnection();
		}//syzybium jamvolinum1x (schwabee)
	}
}
