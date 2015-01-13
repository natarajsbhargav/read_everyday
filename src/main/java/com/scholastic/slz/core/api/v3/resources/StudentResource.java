package com.scholastic.slz.core.api.v3.resources;

import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.LegacyApiConstants.UserType;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.StudentHelper;
import com.scholastic.slz.core.api.v3.vo.StudentResultVO;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.STUDENTS_PATH)
public class StudentResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StudentResource.class);

	/**
	 * Injected UserService to retrieve user details.
	 */
	@Inject
	private transient UserService userService;

	/**
	 * NEW GET method to retrieve student details depending on the student
	 * identifier.
	 */
	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStudentDetailsByIdentifier(
			@PathParam(LegacyApiConstants.IDENTIFIER) final String identifier)
			throws ApplicationException {
		Response response = null;
		if (identifier != null) {
			LOGGER.debug("The student identifier is {}", identifier);
			final UserAccount student = userService.getUserByIdentifier(
					identifier,
					UserType.STUDENT.name().toLowerCase(Locale.getDefault()));
			if (student != null) {
				LOGGER.debug(
						"Student found for the identifier, Student user name: {}",
						student.getUsername());
				final StudentResultVO studentResultVO = StudentHelper
						.getStudentResultVO(student);
				final ResponseBuilder responseBuilder = Response
						.ok(studentResultVO);
				response = responseBuilder.build();
			}

		}
		if (response == null) {
			LOGGER.debug("Could not find a student with identifier: {}",
					identifier);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find a student with identifier " + identifier);

		}
		return response;

	}
}
