package com.scholastic.slz.core.api.v3.resources;

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
import com.scholastic.slz.core.api.LegacyApiUtils;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.StudentHelper;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

/**
 * 
 * @author vijay.abbigeri
 *
 */
@Path(LegacyApiConstants.ORGS_PATH)
public class StudentUsernameResource {

	/** The logger for student user name resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StudentUsernameResource.class);

	/** the organization service */
	@Inject
	private transient OrganizationService orgService;

	/** the user service */
	@Inject
	private transient UserService userService;

	/**
	 * GETs the student details for a given user name and organization
	 * identifier
	 * 
	 * @param orgId
	 *            the organization identifier
	 * @param username
	 *            the user name
	 * @return response (success/failure)
	 * @throws ApplicationException
	 */
	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH + LegacyApiConstants.STUDENTS_PATH
			+ LegacyApiConstants.USERNAMES_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStudentByUsername(
			@PathParam(LegacyApiConstants.IDENTIFIER) final String orgId,
			@PathParam(LegacyApiConstants.STUDENT_USERNAME) final String username)
			throws ApplicationException {
		LOGGER.debug("Getting user for the organization: {} and username: {}",
				orgId, username);
		Response response = null;
		final String[] identifiers = orgId.split(LegacyApiConstants.COLON);
		if (identifiers.length == LegacyApiConstants.IDENTIFIER_LENGTH
				&& !LegacyApiUtils.hasNull(identifiers)) {
			final Organization organization = orgService
					.getOrganizationByIdentifier(identifiers);
			if (organization != null) {
				final UserAccount student = userService.getStudentByOrgIdAndUsername(
						organization.getId(), username);
				if (student != null) {
					// TODO: Remove the hardcoded application code
					response = Response.ok(
							StudentHelper.getStudentResultVO(student, "EBB"))
							.build();
				}
			}
		}
		if (response == null) {
			LOGGER.debug(
					"Could not find a student with username: {} and school identifier: {}",
					username, orgId);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.AUTH_USER_NOT_FOUND,
					"Could not find a student with username '" + username
							+ "' and school identifier '" + orgId + "'");
		}
		return response;
	}
}