package com.scholastic.slz.core.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.OrganizationDao;
import com.scholastic.slz.core.data.model.Organization;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceTest {

	/** the organization DAO */
	@Mock
	private OrganizationDao organizationDao;

	/** the organization service */
	@InjectMocks
	private OrganizationService organizationService = new OrganizationService();

	/**
	 * Tests that the service returns the organization for a given code/site id
	 */
	@Test
	public void testGetOrganizationByCode() {
		String code = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9";
		Organization organization = createOrganization(1, "TEST SCHOOL 1");
		Mockito.when(organizationDao.findOrganizationByCode(code)).thenReturn(
				organization);

		Organization result = organizationService.getOrganizationByCode(code);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the organization for a given
	 * identifier(org_guid:org_uuid)
	 */
	@Test
	public void testGetOrganizationByIdentifier() {
		String[] arrIdentifiers = { "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9",
				"0b9e058c-39c8-49d5-8c1c-dfd585fdaca10" };
		Organization organization = createOrganization(1, "TEST SCHOOL 1");
		Mockito.when(organizationDao.findByIdentifier(arrIdentifiers))
				.thenReturn(organization);

		Organization result = organizationService
				.getOrganizationByIdentifier(arrIdentifiers);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the organization details for a given
	 * organization
	 */
	@Test
	public void testGetOrgDetails() {
		Organization organization = createOrganization(1, "TEST SCHOOL 1");
		Mockito.when(organizationDao.findOrgDetails(organization)).thenReturn(
				organization);

		Organization result = organizationService.getOrgDetails(organization);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the organization for a given name and
	 * identifier (guid:uuid)
	 */
	@Test
	public void testGetOrganizationByNameAndId() {
		String guid = "088c9417413b487e773ca3bdec192cde";
		String uuid = "3b5272ff-1648-48b3-8708-a5946b183244";
		String name = "TEST SCHOOL 1";
		Organization organization = createOrganization(1, name);
		Mockito.when(
				organizationDao.findOrganizationByNameAndId(name, guid, uuid))
				.thenReturn(organization);

		Organization result = organizationService.getOrganizationByNameAndId(
				name, guid, uuid);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns a list of organizations for a given name
	 */
	@Test
	public void testGetOrganizationsByName() {
		String name = "TEST SCHOOL 1";
		List<Organization> organizations = getOrganizations();
		Mockito.when(organizationDao.findOrganizationsByName(name)).thenReturn(
				organizations);

		List<Organization> result = organizationService
				.getOrganizationsByName(name);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	/**
	 * Tests that the service returns the organization for a given identifier
	 * (guid:uuid) and organization group name
	 */
	@Test
	public void testGetOrganizationByIdAndGroupName() {
		String guid = "088c9417413b487e773ca3bdec192cde";
		String uuid = "3b5272ff-1648-48b3-8708-a5946b183244";
		String groupName = "organizationgroup-1";
		Organization organization = createOrganization(1, "TEST SCHOOL 1");
		Mockito.when(
				organizationDao.findOrganizationByIdAndGroupName(groupName,
						guid, uuid)).thenReturn(organization);

		Organization result = organizationService
				.getOrganizationByIdAndGroupName(groupName, guid, uuid);
		assertNotNull(result);
	}

	/**
	 * Returns a list of organizations
	 * 
	 * @return the list of organizations
	 */
	private List<Organization> getOrganizations() {
		final List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(createOrganization(1, "TEST SCHOOL 1"));
		return organizations;
	}

	/**
	 * Creates a organization for a given identifier and name
	 * 
	 * @param identifier
	 *            the organization identifier
	 * @param name
	 *            the organization name
	 * @return the organization
	 */
	private Organization createOrganization(final Integer identifier,
			final String name) {
		final Organization organization = new Organization();
		organization.setId(identifier);
		organization.setName(name);
		return organization;
	}
}