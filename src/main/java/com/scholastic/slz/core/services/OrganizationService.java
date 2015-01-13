package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.OrganizationDao;
import com.scholastic.slz.core.data.model.Organization;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public class OrganizationService {

	/** The Organization DAO */
	@Inject
	private OrganizationDao organizationDao;

	/**
	 * Returns the organization for a given code/site id
	 * 
	 * @param code
	 *            the organization code
	 * @return the organization
	 */
	public Organization getOrganizationByCode(final String code) {
		return organizationDao.findOrganizationByCode(code);
	}

	/**
	 * Get Organization details for a given identifier(org_guid:org_uuid)
	 * 
	 * @param arrIdentifiers
	 *            the array of identifiers
	 * @return the organization
	 */
	public Organization getOrganizationByIdentifier(
			final String[] arrIdentifiers) {
		return organizationDao.findByIdentifier(arrIdentifiers);
	}

	/**
	 * Get Organization details for a given organization
	 * 
	 * @param organization
	 * @return the organization
	 */
	public Organization getOrgDetails(final Organization org) {
		return organizationDao.findOrgDetails(org);
	}

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
	public Organization getOrganizationByNameAndId(final String name,
			final String guid, final String uuid) {
		return organizationDao.findOrganizationByNameAndId(name, guid, uuid);
	}

	/**
	 * Returns the list of organizations for a given name
	 * 
	 * @param name
	 *            name of the organization
	 * @return the list of organizations
	 */
	public List<Organization> getOrganizationsByName(final String name) {
		return organizationDao.findOrganizationsByName(name);
	}

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
	public Organization getOrganizationByIdAndGroupName(final String groupName,
			final String guid, final String uuid) {
		return organizationDao.findOrganizationByIdAndGroupName(groupName,
				guid, uuid);
	}
}