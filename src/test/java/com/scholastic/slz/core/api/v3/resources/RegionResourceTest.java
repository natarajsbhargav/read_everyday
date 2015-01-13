package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.Country;
import com.scholastic.slz.core.data.model.Region;
import com.scholastic.slz.core.services.RegionService;

/**
 * @author snehalata.raulo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class RegionResourceTest {

	/** the Region service */
	@Mock
	private transient RegionService regionService;

	/** the Region Resource */
	@InjectMocks
	private transient final RegionResource regionResource = new RegionResource();

	/** the identifier */
	public static final String IDENTIFIER = "1";

	/**
	 * Success Test for the region resource returns a list of regions.
	 */
	@Test
	public void testGetAllRegionsSuccess() {
		final List<Region> regions = new ArrayList<Region>();
		final Region region = new Region();
		region.setId(1);
		region.setName("Australia/New Zealand");
		regions.add(region);

		Mockito.when(regionService.getAllRegions()).thenReturn(regions);
		final Response response = regionResource.getAllRegions();
		assertEquals("getAllRegions success test has cleared",
				Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Failure Test for the region resource returns a list of regions.
	 */
	@Test(expected = ApplicationException.class)
	public void testGetAllRegionsFailure() {
		Mockito.when(regionService.getAllRegions()).thenReturn(null);
		regionResource.getAllRegions();
	}

	/**
	 * Success Test for the region resource returns a list of countries for a
	 * given region.
	 */
	@Test
	public void testGetCountriesByRegionSuccess() {
		final Region region = new Region();
		final Set<Country> countries = new HashSet<Country>();
		Country country = new Country();
		country.setCode("NZL");
		country.setName("New Zealand");
		countries.add(country);
		country = new Country();
		country.setCode("TKL");
		country.setName("Tokelau");
		countries.add(country);
		region.setId(1);
		region.setName("Australia/New Zealand");
		region.setCountries(countries);

		Mockito.when(regionService.getCountriesByRegionId(1))
				.thenReturn(region);
		final Response response = regionResource
				.getCountriesByRegion(IDENTIFIER);
		assertEquals("getCountriesByRegion success test has cleared",
				Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Failure Test for the region resource returns a list of countries for a
	 * given region.
	 */
	@Test(expected = ApplicationException.class)
	public void testGetCountriesByRegionFailure() {
		Mockito.when(regionService.getCountriesByRegionId(1)).thenReturn(null);
		regionResource.getCountriesByRegion(IDENTIFIER);
	}

}
