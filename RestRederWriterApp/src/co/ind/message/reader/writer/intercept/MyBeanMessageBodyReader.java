package co.ind.message.reader.writer.intercept;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
public class MyBeanMessageBodyReader implements MessageBodyReader<MyBean> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == MyBean.class;
	}

	@Override
	public MyBean readFrom(Class<MyBean> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MyBean.class);
			MyBean myBean = (MyBean) jaxbContext.createUnmarshaller()
					.unmarshal(entityStream);
			return myBean;
		} catch (JAXBException jaxbException) {
			throw new RuntimeException("Error deserializing a MyBean.", jaxbException);
		}
	}
}
