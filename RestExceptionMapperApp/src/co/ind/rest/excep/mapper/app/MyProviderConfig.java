package co.ind.rest.excep.mapper.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//zero web.xml configuration
@ApplicationPath("/rest")
public class MyProviderConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> empty = new HashSet<Class<?>>();
		empty.add(EmpResource.class);
		empty.add(EmpNotfoundExceptionMapper.class);
		return empty;
	}
	// http://localhost:8190/ConfigurationManagement/network-management/configurations
}
