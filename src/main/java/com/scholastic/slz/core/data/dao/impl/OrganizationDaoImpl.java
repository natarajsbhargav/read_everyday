package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholastic.slz.core.data.dao.OrganizationDao;
import com.scholastic.slz.core.data.model.Organization;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public class OrganizationDaoImpl extends GenericDaoImpl<Organization> implements
		OrganizationDao {

	/**
	 * Map to add parameters.
	 */
	private Map<String, Object> parameters;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization findOrganizationByCode(final String code) {
		parameters = new HashMap<String, Object>();
		parameters.put("code", code);
		return findSingleResultWithNamedQuery(Organization.QUERY_FIND_BY_CODE,
				parameters);
	}

	/**
	 * Find Organization by identifier(org_guid:org_uuid).
	 */
	@Override
	public Organization findByIdentifier(final String[] arrIdentifiers) {
		Organization organization = null;
		final String orgGuid = arrIdentifiers[0];
		final String orgUuid = arrIdentifiers[1];
		parameters = new HashMap<String, Object>();
		parameters.put("orgGuid", orgGuid);
		parameters.put("orgUuid", orgUuid);
		final List<Organization> organizations = findWithNamedQuery(
				Organization.FIND_BY_IDENTIFIERS, parameters);
		if (organizations != null && organizations.size() == 1) {
			organization = organizations.get(0);
		}
		// }
		return organization;
	}

	@Override
	public Organization findOrgDetails(final Organization org) {
		Organization organization = null;
		parameters = new HashMap<String, Object>();
		parameters.put("org", org);
		final List<Organization> organizations = findWithNamedQuery(
				Organization.FIND_ORG_DETAILS, parameters);
		if (organizations != null && organizations.size() == 1) {
			organization = organizations.get(0);
		}
		return organization;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization findOrganizationByNameAndId(final String name,
			final String guid, final String uuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("name", "%" + name + "%");
		parameters.put("guid", guid);
		parameters.put("uuid", uuid);
		return findSingleResultWithNamedQuery(
				Organization.QUERY_FIND_BY_ORG_NAME_AND_ID, parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Organization> findOrganizationsByName(final String name) {
		parameters = new HashMap<String, Object>();
		parameters.put("name", "%" + name + "%");
		return findWithNamedQuery(Organization.QUERY_FIND_BY_ORG_NAME,
				parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization findOrganizationByIdAndGroupName(final String groupName,
			final String guid, final String uuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("name", "%" + groupName + "%");
		parameters.put("guid", guid);
		parameters.put("uuid", uuid);
		return findSingleResultWithNamedQuery(
				Organization.QUERY_FIND_BY_ORG_ID_AND_GROUP_NAME, parameters);
	}

}