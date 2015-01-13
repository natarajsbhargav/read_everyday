package com.scholastic.slz.core.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.RegionDao;
import com.scholastic.slz.core.data.model.Country;
import com.scholastic.slz.core.data.model.Region;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class RegionServiceTest {

	/** the region DAO */
	@Mock
	private RegionDao regionDao;

	/** the region service */
	@InjectMocks
	private RegionService regionService = new RegionService();

	/**
	 * Tests that the service returns a list of regions
	 */
	@Test
	public void testGetAllRegions() {

		final List<Region> regions = getRegions();
		Mockito.when(regionDao.findWithNamedQuery(Region.QUERY_FIND_ALL, null))
				.thenReturn(regions);

		final List<Region> result = regionService.getAllRegions();
		assertNotNull(result);
	}
	
	/**
	 * Tests that the service returns region along with a list of countries for
	 * a given region identifier
	 */
	@Test
	public void testGetCountriesByRegionId() {
		Region region = createRegion(1, "Australia/New Zealand");
		addCountries(region);
		Mockito.when(regionDao.findCountriesById(1)).thenReturn(region);

		Region result = regionService.getCountriesByRegionId(1);
		assertNotNull(result);
		assertFalse(result.getCountries().isEmpty());
	}

	/**
	 * Returns a list of regions
	 * 
	 * @return the list of regions
	 */
	private List<Region> getRegions() {
		final List<Region> regions = new ArrayList<Region>();
		regions.add(createRegion(1, "Australia/New Zealand"));
		return regions;
	}

	/**
	 * Creates a region for a given identifier and name
	 * 
	 * @param identifier
	 *            the region identifier
	 * @param name
	 *            the region name
	 * @return the region
	 */
	private Region createRegion(final Integer identifier, final String name) {
		final Region region = new Region();
		region.setId(identifier);
		region.setName(name);
		return region;
	}
	
	/**
	 * Adds a list of countries to the region
	 * 
	 * @param region
	 *            the region to which the countries will be added
	 */
	private void addCountries(final Region region) {
		final Set<Country> countries = new HashSet<Country>();
		Country country = new Country();
		country.setId(1);
		country.setName("New Caledonia");
		countries.add(country);
		region.setCountries(countries);
	}
}