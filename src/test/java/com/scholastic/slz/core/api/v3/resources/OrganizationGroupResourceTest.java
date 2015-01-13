package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.services.OrganizationGroupService;

/**
 * Test class for organization group resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationGroupResourceTest {

	/** the organization group service */
	@Mock
	private transient OrganizationGroupService orgGroupService;

	/** the organization group resource */
	@InjectMocks
	private final transient OrganizationGroupResource orgGroupResource = new OrganizationGroupResource();
	
	/** the organization group UUID */
	public static final String UUID = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca9"; 

	/**
	 * Tests {@link OrganizationGroupResource#getOrganizationGroupById(String)}
	 * for successful scenario
	 */
	@Test
	public void testGetOrganizationGroupById() {
		final OrganizationGroup orgGroup = createOrganizationGroup(1,
				"Test Org Group");
		Mockito.when(orgGroupService.getOrganizationGroupByUuid(UUID))
				.thenReturn(orgGroup);

		final Response response = orgGroupResource
				.getOrganizationGroupById(UUID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests {@link OrganizationGroupResource#getOrganizationGroupById(String)}
	 * for failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationGroupByIdFailure() {
		Mockito.when(orgGroupService.getOrganizationGroupByUuid(UUID))
				.thenReturn(null);
		orgGroupResource.getOrganizationGroupById(UUID);
	}

	/**
	 * Tests that the
	 * {@link OrganizationGroupResource#getOrganizationGroupById(String)} throws
	 * application exception when organization group UUID is null
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationGroupByIdWithInvalidUuid() {
		orgGroupResource.getOrganizationGroupById(null);
	}

	/**
	 * Tests
	 * {@link OrganizationGroupResource#getOrganizationGroupByIdFull(String)}
	 * for successful scenario
	 */
	@Test
	public void testGetOrganizationGroupByIdFull() {
		final OrganizationGroup orgGroup = createOrganizationGroup(1,
				"Test Org Group");
		Mockito.when(orgGroupService.getOrganizationGroupFullByUuid(UUID))
				.thenReturn(orgGroup);

		final Response response = orgGroupResource
				.getOrganizationGroupByIdFull(UUID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests
	 * {@link OrganizationGroupResource#getOrganizationGroupByIdFull(String)}
	 * for failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationGroupByIdFullFailure() {
		Mockito.when(orgGroupService.getOrganizationGroupFullByUuid(UUID))
				.thenReturn(null);
		orgGroupResource.getOrganizationGroupByIdFull(UUID);
	}

	/**
	 * Tests that the
	 * {@link OrganizationGroupResource#getOrganizationGroupByIdFull(String)}
	 * throws application exception when organization group UUID is null
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationGroupByIdFullWithInvalidUuid() {
		orgGroupResource.getOrganizationGroupByIdFull(null);
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
}
