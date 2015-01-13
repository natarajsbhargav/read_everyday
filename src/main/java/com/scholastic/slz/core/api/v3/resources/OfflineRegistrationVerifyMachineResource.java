package com.scholastic.slz.core.api.v3.resources;

import java.util.List;

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
import com.scholastic.slz.core.api.v3.vo.VerifyMachineResultVO;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.OrderEntitlementService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.OFFLINE)
public class OfflineRegistrationVerifyMachineResource {
	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineRegistrationVerifyMachineResource.class);

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
	 * NEW GET method to verify registrations by machine uuid
	 * 
	 * @param machineUuid
	 */
	@GET
	@Path(LegacyApiConstants.VERIFY_MACHINE)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response verifyRegistrationByMachine(
			@QueryParam(LegacyApiConstants.UUID) final String machineUuid)
			throws ApplicationException {
		Response response = null;
		if (machineUuid != null) {
			LOGGER.debug("The machine uuid: {}", machineUuid);
			List<OfflineRegistration> offlineRegs = offlineService
					.getByMachineUuid(machineUuid);
			if (offlineRegs != null && !offlineRegs.isEmpty()) {
				LOGGER.debug(
						"The Offline Registration size for the provided Machine uuid is : {}",
						offlineRegs.size());
				offlineRegs = offlineService.verifyRegistration(offlineRegs);
				response = getVerifyMachineResponse(machineUuid, offlineRegs);
			}

		}
		if (response == null) {
			LOGGER.debug(
					"Unable to verify the offline registration for the machine uuid {}",
					machineUuid);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.CLIENT_ERROR_GENERIC,
					"Unable to verify the offline registration for the machine uuid "
							+ machineUuid);
		}
		return response;
	}

	/**
	 * @param machineUuid
	 * @param offlineRegs
	 *            The list of OfflineRegistration
	 * @return the response for the Verified Machine
	 */
	private Response getVerifyMachineResponse(final String machineUuid,
			final List<OfflineRegistration> offlineRegs) {
		Response response = null;
		if (offlineRegs != null && !offlineRegs.isEmpty()) {
			LOGGER.debug(
					"Registrations have been verified for the machine uuid {}",
					machineUuid);
			final List<OrderEntitlement> orderEntitlements = entitlementService
					.getByOfflineMachineUuid(machineUuid);
			final VerifyMachineResultVO regResultVO = OfflineRegistrationHelper
					.getVerifyRegistrationResultVO(orderEntitlements);
			final ResponseBuilder responseBuilder = Response.ok(regResultVO);
			response = responseBuilder.build();
		}
		return response;
	}
}
