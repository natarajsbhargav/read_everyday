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
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.services.OrganizationService;

/**
 * Test class for organization resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationResourceTest {

	/** the organization service */
	@Mock
	private transient OrganizationService orgService;

	/** the organization resource */
	@InjectMocks
	private final transient OrganizationResource orgResource = new OrganizationResource();
	
	/** the organization code */
	public static final String CODE = "CANRGTH"; 

	/**
	 * Tests {@link OrganizationResource#getOrganizationByCode(String)} for
	 * successful scenario
	 */
	@Test
	public void testGetOrganizationByCodeSuccess() {
		final Organization org = createOrganization(1, "Test Organization");
		Mockito.when(orgService.getOrganizationByCode(CODE)).thenReturn(org);

		final Response response = orgResource.getOrganizationByCode(CODE);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests {@link OrganizationResource#getOrganizationByCode(String)} for
	 * failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationByCodeFailure() {
		Mockito.when(orgService.getOrganizationByCode(CODE)).thenReturn(null);
		orgResource.getOrganizationByCode(CODE);
	}

	/**
	 * Tests that the {@link OrganizationResource#getOrganizationByCode(String)}
	 * throws application exception when organization code is less than seven
	 * characters
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationByCodeWithInvalidCode() {
		orgResource.getOrganizationByCode("TEST");
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
