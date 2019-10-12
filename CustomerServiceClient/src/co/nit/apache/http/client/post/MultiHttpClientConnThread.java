package co.nit.apache.http.client.post;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class MultiHttpClientConnThread extends Thread {

	private final CloseableHttpClient client;
	private final HttpGet get;

	private PoolingHttpClientConnectionManager connManager;
	private int leasedConn;

	public MultiHttpClientConnThread(final CloseableHttpClient client,
			final HttpGet get,
			final PoolingHttpClientConnectionManager connManager) {
		this.client = client;
		this.get = get;
		this.connManager = connManager;
		leasedConn = 0;
	}

	public MultiHttpClientConnThread(final CloseableHttpClient client,
			final HttpGet get) {
		this.client = client;
		this.get = get;
	}

	// API

	public final int getLeasedConn() {
		return leasedConn;
	}

	//

	@Override
	public final void run() {
		try {

			if (connManager != null) {
				System.out.println("Before - Leased Connections = "
						+ connManager.getTotalStats().getLeased());
				System.out.println("Before - Available Connections = "
						+ connManager.getTotalStats().getAvailable());
			}

			final HttpResponse response = client.execute(get);

			if (connManager != null) {
				leasedConn = connManager.getTotalStats().getLeased();
				System.out.println("After - Leased Connections = "
						+ connManager.getTotalStats().getLeased());
				System.out.println("After - Available Connections = "
						+ connManager.getTotalStats().getAvailable());
			}

			EntityUtils.consume(response.getEntity());
		} catch (final IOException ex) {

		}
	}

}
