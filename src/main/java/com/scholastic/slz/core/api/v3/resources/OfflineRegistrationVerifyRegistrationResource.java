package com.scholastic.slz.core.api.v3.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.OfflineRegistrationHelper;
import com.scholastic.slz.core.api.v3.vo.VerifyRegistrationVO;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.OrderEntitlementService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.OFFLINE)
public class OfflineRegistrationVerifyRegistrationResource {
	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineRegistrationVerifyRegistrationResource.class);

	/**
	 * Injected OfflineService to retrieve offline registrations.
	 */
	@Inject
	private transient OfflineService offlineService;

	/**
	 * Injected OrderEntitlementService to retrieve Order Entitlements.
	 */
	@Inject
	private transient OrderEntitlementService entitlementService;

	/**
	 * NEW GET method to verify registrations by registration uuid
	 * 
	 * @param registrationUuid
	 */
	@GET
	@Path(LegacyApiConstants.VERIFY_PRODUCT)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response verifyRegistrationByUuid(
			@QueryParam(LegacyApiConstants.UUID) final String registrationUuid)
			throws ApplicationException {
		Response response = null;
		if (registrationUuid != null) {
			LOGGER.debug("The Registration uuid: {}", registrationUuid);
			OfflineRegistration offlineReg = offlineService
					.getByRegistrationUuid(registrationUuid);
			if (offlineReg != null) {
				LOGGER.debug("Verify the Offline Registration for the provided Registration uuid.");
				offlineService.verifyRegistration(offlineReg);
				response = getVerifyRegistrationResponse(registrationUuid);
			}

		}
		if (response == null) {
			LOGGER.debug(
					"Unable to verify the offline registration for the Registration uuid {}",
					registrationUuid);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.CLIENT_ERROR_GENERIC,
					"Unable to verify the offline registration for the Registration uuid "
							+ registrationUuid);
		}
		return response;
	}

	/**
	 * @param registrationUuid
	 *            The Offline Registration uuid
	 * @return the response for the Verified Registration
	 */
	private Response getVerifyRegistrationResponse(final String registrationUuid) {
		Response response = null;
		LOGGER.debug(
				"Registration has been verified for the Registration uuid {}",
				registrationUuid);
		final OrderEntitlement orderEntitlement = entitlementService
				.getByOfflineRegistrationUuid(registrationUuid);
		final VerifyRegistrationVO regResultVO = OfflineRegistrationHelper
				.getVerifyRegistrationResultVO(orderEntitlement);
		final ResponseBuilder responseBuilder = Response.ok(regResultVO);
		response = responseBuilder.build();
		return response;
	}
}
