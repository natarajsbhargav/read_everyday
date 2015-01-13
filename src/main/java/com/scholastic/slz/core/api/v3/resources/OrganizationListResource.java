package com.scholastic.slz.core.api.v3.resources;

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
import com.scholastic.slz.core.api.v3.helpers.OrganizationHelper;
import com.scholastic.slz.core.api.v3.vo.OrganizationResultVO;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.services.OrganizationService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.ORGS_PATH)
public class OrganizationListResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationListResource.class);

	/**
	 * Injected OrganizationService to retrieve the Organization details.
	 */
	@Inject
	private transient OrganizationService orgService;

	/**
	 * NEW GET method to retrieve organization details depending on the
	 * organization identifier
	 */
	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrganizationByIdentifier(
			@PathParam(LegacyApiConstants.IDENTIFIER) final String identifier)
			throws ApplicationException {
		Response response = null;
		if (identifier != null) {
			LOGGER.debug("The Organization Identifier is : {}", identifier);
			final String[] arrIdentifiers = identifier
					.split(LegacyApiConstants.COLON);
			if (arrIdentifiers.length == LegacyApiConstants.IDENTIFIER_LENGTH
					&& !LegacyApiUtils.hasNull(arrIdentifiers)) {
				LOGGER.info(
						"Organization GUID : {} and Organization UUID : {}",
						arrIdentifiers);
				Organization org = orgService
						.getOrganizationByIdentifier(arrIdentifiers);
				if (org != null) {
					LOGGER.debug("Organization Name : {}", org.getName());
					org = orgService.getOrgDetails(org);
					if (org != null) {
						final OrganizationResultVO schoolResultVO = OrganizationHelper
								.getOrganizationreseltVO(identifier, org);
						final ResponseBuilder responseBuilder = Response
								.ok(schoolResultVO);
						response = responseBuilder.build();
					}
				}
			}
		}
		if (response == null) {
			LOGGER.debug("Could not find an organization with identifier {}",
					identifier);

			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_NOT_FOUND,
					"Could not find a school with identifier " + identifier);
		}
		return response;
	}

}
