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
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.helpers.RegionHelper;
import com.scholastic.slz.core.api.v3.vo.RegionListResultVO;
import com.scholastic.slz.core.api.v3.vo.RegionResultVO;
import com.scholastic.slz.core.data.model.Region;
import com.scholastic.slz.core.services.RegionService;

/**
 * @author snehalata.raulo
 * 
 */
@Path(LegacyApiConstants.REGION_ROOT_PATH)
public class RegionResource {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegionResource.class);

	/**
	 * Injected RegionService to retrieve the Region details.
	 */
	@Inject
	private transient RegionService regionService;

	/**
	 * NEW GET method to retrieve list of regions
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllRegions() throws ApplicationException {
		Response response = null;
		final List<Region> regions = regionService.getAllRegions();
		if (regions != null && !regions.isEmpty()) {
			LOGGER.debug("Region size : {}", regions.size());
			final RegionListResultVO regionResultVO = RegionHelper
					.getRegionListVO(regions);
			final ResponseBuilder responseBuilder = Response.ok(regionResultVO);
			response = responseBuilder.build();
		}
		if (response == null) {
			LOGGER.debug("Could not find the regions");
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.REGIONS_NOT_FOUND,
					"Could not find the regions");
		}
		return response;
	}

	/**
	 * NEW GET method to retrieve list of countries depending on the region
	 * identifier as a parameter
	 */
	@GET
	@Path(LegacyApiConstants.IDENTIFIER_PATH)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCountriesByRegion(
			@PathParam(LegacyApiConstants.IDENTIFIER) final String identifier) {
		Response response = null;
		final Region region = regionService.getCountriesByRegionId(Integer
				.parseInt(identifier));
		if (region != null) {
			LOGGER.debug("Region Name : {}", region.getName());
			final RegionResultVO regionVO = RegionHelper
					.getRegionResultVO(region);
			final ResponseBuilder responseBuilder = Response.ok(regionVO);
			response = responseBuilder.build();
		}
		if (response == null) {
			LOGGER.debug("Could not find the region for id {}", identifier);
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.COUNTRIES_REGION_NOT_FOUND,
					"Could not find the region for id " + identifier);
		}
		return response;
	}

}
