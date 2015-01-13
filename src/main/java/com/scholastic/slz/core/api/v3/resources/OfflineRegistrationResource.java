package com.scholastic.slz.core.api.v3.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.form.OfflineRegistrationForm;
import com.scholastic.slz.core.api.v3.vo.OfflineRegistrationResultVO;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.UserService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.OFFLINE)
public class OfflineRegistrationResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineRegistrationResource.class);

	/**
	 * Injected UserService to retrieve user details.
	 */
	@Inject
	private transient UserService userService;

	/**
	 * Injected OfflineService to retrieve Offline Registration details.
	 */
	@Inject
	private transient OfflineService offlineService;

	/**
	 * NEW POST method to register for the offline product
	 */
	@POST
	@Path(LegacyApiConstants.REGISTRATION)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getNewOfflineRegistration(
			@Form final OfflineRegistrationForm form)
			throws ApplicationException {
		Response response = null;
		if (form != null) {
			final String userIdentifier = form.getUserIdentifier();
			final String productCode = form.getProductCode();
			final String machineUuid = form.getMachineUuid();
			final String machineName = form.getMachineName();
			if (userIdentifier != null && productCode != null
					&& machineUuid != null && machineName != null) {
				final String[] regArray = { userIdentifier, productCode,
						machineUuid, machineName };
				LOGGER.debug(
						"The user Identifier : {}, The product code : {}, The Machine UUID : {}, The Machine Name : {} ",
						regArray);
				final UserAccount userEntitlement = userService
						.getUserEntitlementByProduct(userIdentifier,
								productCode);
				response = register(userEntitlement, form);
			}
		}
		if (response == null) {
			LOGGER.debug(
					"Not able to register a product with user identifier {}",
					form.getUserIdentifier());
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.CLIENT_ERROR_GENERIC,
					"Not able to register a product with user identifier "
							+ form.getUserIdentifier());
		}
		return response;
	}

	/**
	 * @param user
	 * @return Response
	 */
	private Response register(final UserAccount userEntitlement,
			final OfflineRegistrationForm form) {
		Response response = null;
		if (userEntitlement != null) {
			LOGGER.debug("The username is: {}", userEntitlement.getUsername());
			final String regUuid = java.util.UUID.randomUUID().toString();
			final Integer numOfSeats = offlineService
					.geNumOfSeats(userEntitlement);
			form.setRegistrationUuid(regUuid);
			if (numOfSeats != null) {
				LOGGER.debug("The number seats available for the order is: {}",
						numOfSeats);
				form.setNumOfSeats(numOfSeats);
			}
			final Boolean validate = offlineService
					.validateAndCreateRegistration(form, userEntitlement);
			if (validate) {
				LOGGER.debug("Validated and created the reigstration");
				final OfflineRegistrationResultVO regVo = new OfflineRegistrationResultVO();
				regVo.setOfflineNumDays(numOfSeats);
				regVo.setRegistrationUuid(regUuid);
				final ResponseBuilder responseBuilder = Response.ok(regVo);
				response = responseBuilder.build();
			}

		}
		return response;
	}
}
