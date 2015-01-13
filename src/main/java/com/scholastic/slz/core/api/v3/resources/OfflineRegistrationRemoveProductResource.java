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
public class OfflineRegistrationRemoveProductResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineRegistrationRemoveProductResource.class);

	/**
	 * Injected OfflineService to retrieve offline registration details.
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
	@Path(LegacyApiConstants.REMOVE_PRODUCT)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeProductRegistration(
			@QueryParam(LegacyApiConstants.PRODUCT_CODE) final String productCode,
			@QueryParam(LegacyApiConstants.UUID) final String machineUuid)
			throws ApplicationException {
		Response response = null;
		String[] params;
		if (productCode != null && machineUuid != null) {
			params = new String[] { productCode, machineUuid };
			LOGGER.debug("The product code: {} and the machine uuid: {}",
					params);
			final List<OfflineRegistration> offlineRegs = offlineService
					.getByProductCode(productCode, machineUuid);
			final Boolean updateFlag = offlineService
					.removeRegistration(offlineRegs);
			if (updateFlag) {
				LOGGER.debug(
						"License has been released successfully for the product  {} on machine {}",
						params);
				final ResultVO resultVO = new ResultVO();
				resultVO.setMessage("License has been released successfully for the product "
						+ productCode + " on machine " + machineUuid);
				final ResponseBuilder responseBuilder = Response.ok(resultVO);
				response = responseBuilder.build();
			}
		}
		if (response == null) {
			params = new String[] { productCode, machineUuid };
			LOGGER.debug(
					"Unable to release the license for the product {} on machine {}",
					params);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.REGISTRATION_NOT_MODIFIED,
					"Unable to release the license for the product  "
							+ productCode + " on machine " + machineUuid);
		}
		return response;
	}
}
