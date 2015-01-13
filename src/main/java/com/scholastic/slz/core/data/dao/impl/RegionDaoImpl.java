package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.scholastic.slz.core.data.dao.RegionDao;
import com.scholastic.slz.core.data.model.Region;

/**
 * @author snehalata.raulo
 * 
 */
public class RegionDaoImpl extends GenericDaoImpl<Region> implements RegionDao {

	/**
	 * Map to add parameters.
	 */
	private Map<String, Object> params;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Region findCountriesById(final Integer regionId) {
		params = new HashMap<String, Object>();
		params.put("id", regionId);
		return findSingleResultWithNamedQuery(Region.QUERY_FIND_BY_ID, params);
	}

}
