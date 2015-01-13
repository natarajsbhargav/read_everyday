package com.scholastic.slz.core.data.dao;

import java.util.List;

import com.scholastic.slz.core.data.model.Organization;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public interface OrganizationDao extends GenericDao<Organization> {

	/**
	 * Returns the organization for a given code
	 * 
	 * @param code
	 *            the code/site id of the organization
	 * @return the organization
	 */
	Organization findOrganizationByCode(String code);

	/**
	 * Returns the organization for given identifiers
	 * 
	 * @param arrIdentifiers
	 *            org guid and uuid of the organization
	 * @return the organization
	 */
	Organization findByIdentifier(String[] arrIdentifiers);

	/**
	 * Returns the organization for given identifiers
	 * 
	 * @param org
	 *            the organization
	 * @return the organization
	 */
	Organization findOrgDetails(Organization org);

	/**
	 * Returns the organization for a given name and identifier (guid:uuid)
	 * 
	 * @param name
	 *            name of the organization
	 * @param guid
	 *            the GUID of the organization
	 * @param uuid
	 *            the UUID of the organization
	 * @return the organization
	 */
	Organization findOrganizationByNameAndId(String name, String guid,
			String uuid);

	/**
	 * Returns the list of organizations for a given name
	 * 
	 * @param name
	 *            name of the organization
	 * @return the list of organizations
	 */
	List<Organization> findOrganizationsByName(String name);

	/**
	 * Returns the organization for a given identifier (guid:uuid) and
	 * organization group name
	 * 
	 * @param groupName
	 *            name of the organization group
	 * @param guid
	 *            the GUID of the organization
	 * @param uuid
	 *            the UUID of the organization
	 * @return the organization
	 */
	Organization findOrganizationByIdAndGroupName(String groupName,
			String guid, String uuid);
}
