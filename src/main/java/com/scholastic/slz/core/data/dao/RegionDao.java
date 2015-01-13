package com.scholastic.slz.core.data.dao;

import com.scholastic.slz.core.data.model.Region;

/**
 * @author snehalata.raulo
 * 
 */
public interface RegionDao extends GenericDao<Region> {

	/**
	 * Get list of countries and Region details for a given region Id
	 * 
	 * @param regionId
	 * @return the Region with countries
	 */
	Region findCountriesById(Integer regionId);
	
}
