package com.scholastic.slz.core.api.v2;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationExceptionHandler;
import com.scholastic.slz.core.api.v2.resources.MyRegionResource;

/**
 * @author snehalata.raulo
 * 
 */
@ApplicationPath(LegacyApiConstants.SLZAPI_V2_BASE_PATH)
public class LegacyApiApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		// Resources

		// These are alphabetical. please try to keep them that way
		classes.add(ApplicationExceptionHandler.class);
		classes.add(MyRegionResource.class);

		return classes;
	}

}
