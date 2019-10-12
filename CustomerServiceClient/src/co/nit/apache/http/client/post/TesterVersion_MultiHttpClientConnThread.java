package co.nit.apache.http.client.post;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class TesterVersion_MultiHttpClientConnThread extends Thread {
	

	private final CloseableHttpClient client;
	private final HttpGet get;
	private PoolingHttpClientConnectionManager connManager;

	public TesterVersion_MultiHttpClientConnThread(final CloseableHttpClient client,
			final HttpGet get,
			final PoolingHttpClientConnectionManager connManager) {
		this.client = client;
		this.get = get;
		this.connManager = connManager;
	}

	//

	@Override
	public final void run() {
		try {
			System.out.println("Thread Running: " + getName());

			System.out.println("Before - Leased Connections = "
					+ connManager.getTotalStats().getLeased());
			System.out.println("Before - Available Connections = "
					+ connManager.getTotalStats().getAvailable());

			client.execute(get);

			System.out.println("After - Leased Connections = "
					+ connManager.getTotalStats().getLeased());
			System.out.println("After - Available Connections = "
					+ connManager.getTotalStats().getAvailable());
		} catch (final IOException ex) {
			System.out.println("" + ex);
		}
	}

}
