package com.scholastic.slz.core.api.v3.resources;

import java.util.List;

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
import com.scholastic.slz.core.api.LegacyApiUtils;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.OrganizationGradeHelper;
import com.scholastic.slz.core.api.v3.vo.OrganizationGradeResultVO;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.ORGS_PATH)
public class OrganizationGradeResource {

	/** The logger for organization group resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationGradeResource.class);

	/**
	 * Injected OrganizationService to retrieve the Organization details.
	 */
	@Inject
	private transient OrganizationService orgService;

	/**
	 * Injected UserService to retrieve the User details.
	 */
	@Inject
	private transient UserService userService;

	/**
	 * NEW GET method to retrieve organization details depending on the
	 * organization identifier
	 */
	@GET
	@Path(LegacyApiConstants.ORG_GRADE_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrgGradeInfo(
			@PathParam(LegacyApiConstants.ORG_IDENTIFIER) final String orgIdentifier,
			@PathParam(LegacyApiConstants.SCHOLASTIC_GRADE_CODE) final String gradeCode)
			throws ApplicationException {
		
		Response response = null;
		if (gradeCode == null || gradeCode.trim().length() < 1) {
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SC_USER_NOT_FOUND,
					"Could not find the scholastic grade code ");
		}

		final String[] arrIdentifiers = orgIdentifier
				.split(LegacyApiConstants.COLON);
		if (arrIdentifiers.length == LegacyApiConstants.IDENTIFIER_LENGTH
				&& !LegacyApiUtils.hasNull(arrIdentifiers)) {
			final String appCode = "SRII";
			final Organization org = orgService
					.getOrganizationByIdentifier(arrIdentifiers);
			if (org != null) {
				LOGGER.debug("Organization Name : {}", org.getName());
				final List<UserAccount> users = userService
						.getUsersByOrgAndGrade(org, gradeCode, appCode);
				LOGGER.debug("users size : {}", users.size());
				final OrganizationGradeResultVO orgGradeVO = OrganizationGradeHelper
						.getOrgGradeResultVO(users);
				final ResponseBuilder responseBuilder = Response.ok(orgGradeVO);
				response = responseBuilder.build();
			}

		}

		if (response == null) {
			LOGGER.debug("Could not find an organization with identifier {}",
					orgIdentifier);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_NOT_FOUND,
					"Could not find an organization with identifier "
							+ orgIdentifier);
		}
		return response;
	}

}
