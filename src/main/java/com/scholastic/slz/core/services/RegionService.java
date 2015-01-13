package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.RegionDao;
import com.scholastic.slz.core.data.model.Region;

/**
 * @author snehalata.raulo
 * 
 */
public class RegionService {

	/**
	 * Injected RegionDao to get region details.
	 */
	@Inject
	private transient RegionDao regionDao;

	/**
	 * Get list of regions
	 * 
	 * @return the organization with countries
	 */
	public List<Region> getAllRegions() {
		return regionDao.findWithNamedQuery(Region.QUERY_FIND_ALL, null);
	}

	/**
	 * Get list of countries and Region details for a given region Id
	 * 
	 * @param regionId
	 * @return the Region with countries
	 */
	public Region getCountriesByRegionId(final Integer regionId) {
		return regionDao.findCountriesById(regionId);
	}

}
