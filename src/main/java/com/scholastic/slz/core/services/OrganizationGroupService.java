package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.OrganizationGroupDao;
import com.scholastic.slz.core.data.model.OrganizationGroup;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public class OrganizationGroupService {

	/** The organization group DAO */
	@Inject
	private OrganizationGroupDao orgGroupDao;

	/**
	 * Returns the organization group for a given UUID
	 * 
	 * @param uuid
	 *            the UUID of the organization group
	 * @return the organization group
	 */
	public OrganizationGroup getOrganizationGroupByUuid(final String uuid) {
		return orgGroupDao.findOrganizationGroupByUuid(uuid);
	}

	/**
	 * Returns the organization group for a given UUID with additional details
	 * like country info
	 * 
	 * @param uuid
	 *            the UUID of the organization group
	 * @return the organization group
	 */
	public OrganizationGroup getOrganizationGroupFullByUuid(final String uuid) {
		return orgGroupDao.findOrganizationGroupFullByUuid(uuid);
	}

	/**
	 * Returns the list of organization groups for a given name
	 * 
	 * @param name
	 *            name of the organization group
	 * @return the list of organization groups
	 */
	public List<OrganizationGroup> getOrganizationGroupsByName(final String name) {
		return orgGroupDao.findOrganizationGroupsByName(name);
	}
}