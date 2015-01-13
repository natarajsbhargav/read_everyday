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
import com.scholastic.slz.core.api.v3.helpers.OrganizationHelper;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.services.OrganizationService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@Path(LegacyApiConstants.ORG_PATH)
public class OrganizationResource {

	/** The logger for organization resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationResource.class);

	/** The organization service */
	@Inject
	private OrganizationService orgService;

	@GET
	@Path(LegacyApiConstants.ORG_CODE_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrganizationByCode(@PathParam("code") final String code)
			throws ApplicationException {
		LOGGER.debug("Getting organization with code: {}", code);
		if (code != null && code.length() == LegacyApiConstants.ORG_CODE_LENGTH) {
			final Organization organization = orgService
					.getOrganizationByCode(code);
			if (organization == null) {
				LOGGER.debug(
						"Could not find a school with organization Id: {}",
						code);
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.SCHOOL_NOT_FOUND,
						"Could not find a School with Org Id " + code);
			} else {
				LOGGER.debug("Organization found for the code: {}", code);
				return Response.ok(
						OrganizationHelper.getOrganizationVO(organization))
						.build();
			}
		} else {
			LOGGER.debug("Invalid organization Id: {}", code);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_NOT_FOUND,
					"Please enter a valid Org Id " + code);
		}
	}

}