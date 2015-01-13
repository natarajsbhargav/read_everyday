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
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.UserHelper;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@Path(LegacyApiConstants.ORG_GROUP_ADMIN_PATH)
public class OrganizationGroupAdminResource {

	/** The logger for organization group administrator resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationGroupAdminResource.class);

	/** The user service */
	@Inject
	private UserService userService;

	/**
	 * GETs the organization group administrator details for a given group
	 * administrator identifier
	 * 
	 * @param identifier
	 *            the group administrator identifier
	 * @return response (success/failure)
	 * @throws ApplicationException
	 */
	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrganizationGroupAdminById(
			@PathParam("identifier") final String identifier)
			throws ApplicationException {
		LOGGER.debug(
				"Getting organization group administrator details with Id: {}",
				identifier);
		final UserAccount orgGroupAdmin = userService
				.getOrgGroupAdminById(identifier);
		if (orgGroupAdmin == null) {
			LOGGER.debug(
					"Could not find a org group administrator with identifier: {}",
					identifier);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SC_USER_NOT_FOUND,
					"Could not find a group admin user with identifier "
							+ identifier);
		} else {
			return Response.ok(UserHelper.getOrgGroupAdminVO(orgGroupAdmin))
					.build();
		}
	}
}