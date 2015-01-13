package com.scholastic.slz.core.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.OrganizationGroupDao;
import com.scholastic.slz.core.data.model.OrganizationGroup;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationGroupServiceTest {

	/** the organization group DAO */
	@Mock
	private OrganizationGroupDao orgGroupDao;

	/** the organization group service */
	@InjectMocks
	private OrganizationGroupService organizationGroupService = new OrganizationGroupService();

	/**
	 * Tests that the service returns the organization group for a given UUID
	 */
	@Test
	public void testGetOrganizationGroupByUuid() {
		String uuid = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9";
		OrganizationGroup organizationGroup = createOrganizationGroup(1,
				"organizationgroup-1");
		Mockito.when(orgGroupDao.findOrganizationGroupByUuid(uuid)).thenReturn(
				organizationGroup);

		OrganizationGroup result = organizationGroupService
				.getOrganizationGroupByUuid(uuid);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the organization group (complete) for a
	 * given UUID
	 */
	@Test
	public void testGetOrganizationGroupFullByUuid() {
		String uuid = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9";
		OrganizationGroup organizationGroup = createOrganizationGroup(1,
				"organizationgroup-1");
		addChildOrgGroups(organizationGroup);
		Mockito.when(orgGroupDao.findOrganizationGroupFullByUuid(uuid))
				.thenReturn(organizationGroup);

		OrganizationGroup result = organizationGroupService
				.getOrganizationGroupFullByUuid(uuid);
		assertNotNull(result);
		assertFalse(result.getChildOrgGroups().isEmpty());
	}

	/**
	 * Tests that the service returns a list of organization groups for a given
	 * name
	 */
	@Test
	public void testGetOrganizationGroupsByName() {
		String name = "organizationgroup-1";
		List<OrganizationGroup> orgGroups = getOrganizationGroups();
		Mockito.when(orgGroupDao.findOrganizationGroupsByName(name))
				.thenReturn(orgGroups);

		List<OrganizationGroup> result = organizationGroupService
				.getOrganizationGroupsByName(name);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	/**
	 * Returns a list of organization groups
	 * 
	 * @return the list of organization groups
	 */
	private List<OrganizationGroup> getOrganizationGroups() {
		final List<OrganizationGroup> orgGroups = new ArrayList<OrganizationGroup>();
		orgGroups.add(createOrganizationGroup(1, "organizationgroup-1"));
		return orgGroups;
	}

	/**
	 * Creates a organization group for a given identifier and name
	 * 
	 * @param identifier
	 *            the organization group identifier
	 * @param name
	 *            the organization group name
	 * @return the organization group
	 */
	private OrganizationGroup createOrganizationGroup(final Integer identifier,
			final String name) {
		final OrganizationGroup organizationgroup = new OrganizationGroup();
		organizationgroup.setId(identifier);
		organizationgroup.setName(name);
		return organizationgroup;
	}

	/**
	 * Adds child organization groups for a given organization group
	 * 
	 * @param orgGroup
	 *            the organization group to which the child organization groups
	 *            will be added
	 */
	private void addChildOrgGroups(final OrganizationGroup orgGroup) {
		final Set<OrganizationGroup> childOrgGroups = new HashSet<OrganizationGroup>();
		OrganizationGroup childOrgGroup = createOrganizationGroup(2,
				"organizationgroup-2");
		childOrgGroups.add(childOrgGroup);
		orgGroup.setChildOrgGroups(childOrgGroups);
	}
}