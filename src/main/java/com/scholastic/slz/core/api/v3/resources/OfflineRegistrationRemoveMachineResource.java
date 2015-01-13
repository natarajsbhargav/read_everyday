/**
 * 
 */
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
import com.scholastic.slz.core.api.v3.vo.ResultVO;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.services.OfflineService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.OFFLINE)
public class OfflineRegistrationRemoveMachineResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineRegistrationRemoveMachineResource.class);

	/**
	 * Injected OfflineService to retrieve offline registrations.
	 */
	@Inject
	private transient OfflineService offlineService;

	/**
	 * NEW GET method to remove registrations by product code
	 * 
	 * @param productCode
	 * @param machineUuid
	 */
	@GET
	@Path(LegacyApiConstants.REMOVE_MACHINE)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeMachineRegistration(
			@QueryParam(LegacyApiConstants.UUID) final String machineUuid)
			throws ApplicationException {
		Response response = null;
		if (machineUuid != null) {
			LOGGER.debug("The machine uuid: {}", machineUuid);
			final List<OfflineRegistration> offlineRegs = offlineService
					.getByMachineUuid(machineUuid);
			final Boolean updateFlag = offlineService
					.removeRegistration(offlineRegs);
			if (updateFlag) {
				LOGGER.debug(
						"Licenses have been released successfully from the machine {}",
						machineUuid);
				final ResultVO resultVO = new ResultVO();
				resultVO.setMessage("Licenses have been released successfully from the machine "
						+ machineUuid);
				final ResponseBuilder responseBuilder = Response.ok(resultVO);
				response = responseBuilder.build();
			}
		}
		if (response == null) {
			LOGGER.debug("Unable to release the licenses from machine ",
					machineUuid);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.REGISTRATION_NOT_MODIFIED,
					"Unable to release the licenses from machine "
							+ machineUuid);
		}
		return response;
	}
}
