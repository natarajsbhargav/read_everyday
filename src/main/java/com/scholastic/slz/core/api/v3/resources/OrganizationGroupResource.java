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
import com.scholastic.slz.core.api.v3.helpers.OrganizationGroupHelper;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.services.OrganizationGroupService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@Path(LegacyApiConstants.ORG_GROUP_PATH)
public class OrganizationGroupResource {

	/** The logger for organization group resource */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationGroupResource.class);

	/** The organization group service */
	@Inject
	private OrganizationGroupService orgGroupService;

	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrganizationGroupById(
			@PathParam("identifier") final String identifier)
			throws ApplicationException {
		LOGGER.debug("Getting organization group with Id: {}", identifier);
		if (identifier == null) {
			LOGGER.debug("Organization group Id cannot be null");
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_GROUP_NOT_FOUND,
					"Please enter a valid school group identifier");
		} else {
			final OrganizationGroup organizationGroup = orgGroupService
					.getOrganizationGroupByUuid(identifier);
			if (organizationGroup == null) {
				LOGGER.debug(
						"Could not find a organization group with identifier: {}",
						identifier);
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.SCHOOL_GROUP_NOT_FOUND,
						"Could not find a school group with identifier "
								+ identifier);
			} else {
				LOGGER.debug("Organization group found for the identifier: {}",
						identifier);
				return Response.ok(
						OrganizationGroupHelper
								.getOrganizationGroupVO(organizationGroup))
						.build();
			}
		}
	}

	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH + LegacyApiConstants.FULL_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOrganizationGroupByIdFull(
			@PathParam("identifier") final String identifier)
			throws ApplicationException {
		LOGGER.debug("Getting full details for organization group with Id: {}",
				identifier);
		if (identifier == null) {
			LOGGER.debug("Organization group Id cannot be null");
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_GROUP_NOT_FOUND,
					"Please enter a valid school group identifier");
		} else {
			final OrganizationGroup organizationGroup = orgGroupService
					.getOrganizationGroupFullByUuid(identifier);
			if (organizationGroup == null) {
				LOGGER.debug(
						"Could not find a organization group with identifier: {}",
						identifier);
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.SCHOOL_GROUP_NOT_FOUND,
						"Could not find a school group with identifier "
								+ identifier);
			} else {
				LOGGER.debug("Organization group found for the identifier: {}",
						identifier);
				return Response.ok(
						OrganizationGroupHelper.getOrganizationGroupFullVO(
								organizationGroup, orgGroupService, true))
						.build();
			}
		}
	}

}
