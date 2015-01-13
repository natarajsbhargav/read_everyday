package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholastic.slz.core.data.dao.OrganizationGroupDao;
import com.scholastic.slz.core.data.model.OrganizationGroup;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public class OrganizationGroupDaoImpl extends GenericDaoImpl<OrganizationGroup>
		implements OrganizationGroupDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationGroup findOrganizationGroupByUuid(final String uuid) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("uuid", uuid);
		return findSingleResultWithNamedQuery(
				OrganizationGroup.QUERY_FIND_BY_UUID, parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrganizationGroup findOrganizationGroupFullByUuid(final String uuid) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("uuid", uuid);
		return findSingleResultWithNamedQuery(
				OrganizationGroup.QUERY_FIND_BY_UUID_FULL, parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OrganizationGroup> findOrganizationGroupsByName(final String name) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", "%" + name + "%");
		return findWithNamedQuery(OrganizationGroup.QUERY_FIND_BY_NAME,
				parameters);
	}
}