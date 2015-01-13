package com.scholastic.slz.core.data.dao;

import java.util.List;

import com.scholastic.slz.core.data.model.OrganizationGroup;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public interface OrganizationGroupDao extends GenericDao<OrganizationGroup> {

	/**
	 * Returns the organization group for a given UUID
	 * 
	 * @param uuid
	 *            the UUID of the organization group
	 * @return the organization group
	 */
	OrganizationGroup findOrganizationGroupByUuid(String uuid);

	/**
	 * Returns the organization group for a given UUID with additional details
	 * like country info
	 * 
	 * @param uuid
	 *            the UUID of the organization group
	 * @return the organization group
	 */
	OrganizationGroup findOrganizationGroupFullByUuid(String uuid);

	/**
	 * Returns the list of organization groups for a given name
	 * 
	 * @param name
	 *            name of the organization group
	 * @return the list of organization groups
	 */
	List<OrganizationGroup> findOrganizationGroupsByName(String name);
}