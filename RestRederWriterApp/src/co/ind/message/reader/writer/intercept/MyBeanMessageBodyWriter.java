package co.ind.message.reader.writer.intercept;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class MyBeanMessageBodyWriter implements MessageBodyWriter<MyBean> {
	 
    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return type == MyBean.class;
    }
 
    @Override
    public long getSize(MyBean myBean, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }
 
    @Override
    public void writeTo(MyBean myBean,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
                        throws IOException, WebApplicationException {
 
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MyBean.class);
 
            // serialize the entity myBean to the entity output stream
            jaxbContext.createMarshaller().marshal(myBean, entityStream);
            System.out.println(entityStream);
        } catch (JAXBException jaxbException) {
            throw new RuntimeException("Error serializing a MyBean to the output stream", jaxbException);
        }
    }
}
