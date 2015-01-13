package com.scholastic.slz.core.api.v3;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationExceptionHandler;
import com.scholastic.slz.core.api.v3.resources.AdminDetailsResource;
import com.scholastic.slz.core.api.v3.resources.ClassResource;
import com.scholastic.slz.core.api.v3.resources.GradeSystemResource;
import com.scholastic.slz.core.api.v3.resources.OfflineRegistrationRemoveMachineResource;
import com.scholastic.slz.core.api.v3.resources.OfflineRegistrationRemoveProductResource;
import com.scholastic.slz.core.api.v3.resources.OrganizationGradeResource;
import com.scholastic.slz.core.api.v3.resources.OrganizationGroupAdminResource;
import com.scholastic.slz.core.api.v3.resources.OrganizationGroupResource;
import com.scholastic.slz.core.api.v3.resources.OrganizationListResource;
import com.scholastic.slz.core.api.v3.resources.OrganizationResource;
import com.scholastic.slz.core.api.v3.resources.RegionResource;
import com.scholastic.slz.core.api.v3.resources.SchoolCalenderResource;
import com.scholastic.slz.core.api.v3.resources.SearchResource;
import com.scholastic.slz.core.api.v3.resources.StudentLexileUpdateResource;
import com.scholastic.slz.core.api.v3.resources.StudentResource;
import com.scholastic.slz.core.api.v3.resources.TeacherResource;

/**
 * @author snehalata.raulo
 * 
 */
@ApplicationPath(LegacyApiConstants.SLZAPI_V3_BASE_PATH)
public class LegacyApiApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		// Resources

		// These are alphabetical. please try to keep them that way
		classes.add(ApplicationExceptionHandler.class);
		classes.add(AdminDetailsResource.class);
		classes.add(ClassResource.class);
		classes.add(GradeSystemResource.class);
		classes.add(OfflineRegistrationRemoveMachineResource.class);
		classes.add(OfflineRegistrationRemoveProductResource.class);
		classes.add(OrganizationGradeResource.class);
		classes.add(OrganizationGroupAdminResource.class);
		classes.add(OrganizationGroupResource.class);
		classes.add(OrganizationListResource.class);
		classes.add(OrganizationResource.class);
		classes.add(RegionResource.class);
		classes.add(SchoolCalenderResource.class);
		classes.add(SearchResource.class);
		classes.add(StudentResource.class);
		classes.add(StudentLexileUpdateResource.class);
		classes.add(TeacherResource.class);

		return classes;
	}

}
