package com.scholastic.slz.core.api.v3.resources;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.LegacyApiConstants.UserType;
import com.scholastic.slz.core.api.LegacyApiUtils;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.ClassHelper;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.ClassService;
import com.scholastic.slz.core.services.UserService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@Path(LegacyApiConstants.CLASS_PATH)
public class ClassResource {

	/** The logger for class resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClassResource.class);

	/** The class service */
	@Inject
	private ClassService classService;

	/** The user service */
	@Inject
	private UserService userService;

	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getClassByIdentifier(
			@PathParam("identifier") final String identifier) {
		LOGGER.debug("Getting class with id: {}", identifier);
		Response response = null;
		if (identifier != null) {
			final String[] identifiers = identifier
					.split(LegacyApiConstants.COLON);
			if (identifiers.length == 3 && !LegacyApiUtils.hasNull(identifiers)) {
				final String classUuid = identifiers[2];
				LOGGER.debug("Class UUID: {}", classUuid);
				final ClassInfo classInfo = classService
						.getClassByUuid(classUuid);
				if (classInfo == null) {
					LOGGER.debug("Could not find a class with UUID: {}",
							classUuid);
					throw new ApplicationException(Status.NOT_FOUND,
							ESlzApiErrorReason.SCHOOL_CLASS_NOT_FOUND,
							"Could not find a school class with identifier "
									+ identifier);
				} else {
					LOGGER.debug("Class found for the UUID: {}", classUuid);
					final List<UserAccount> students = getUserDetails(
							classInfo, UserType.STUDENT.name().toLowerCase(Locale.getDefault()));
					final List<UserAccount> teachers = getUserDetails(
							classInfo, UserType.TEACHER.name().toLowerCase(Locale.getDefault()));
					response = Response
							.status(200)
							.entity(ClassHelper.getClassVO(classInfo, students,
									teachers)).build();
				}
			}
		}
		if (response == null) {
			LOGGER.debug("Invalid class Id: {}", identifier);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_CLASS_NOT_FOUND,
					"Invalid class Id: " + identifier);
		}
		return response;
	}

	/**
	 * Returns the list of users for a given class and user type
	 * 
	 * @param classInfo
	 *            the class for which the users has to be listed
	 * @param userType
	 *            the type of the users to be listed
	 * @return list of users
	 */
	private List<UserAccount> getUserDetails(final ClassInfo classInfo,
			final String userType) {
		List<UserAccount> userAccounts = null;
		if (classInfo != null && userType != null) {
			LOGGER.debug("Getting users of type: {} for the class: {}",
					userType, classInfo.getId());
			userAccounts = userService.getUsersByClassIdAndType(
					classInfo.getId(), userType, "EBB");
		}
		return userAccounts;
	}

}