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

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.services.OrganizationService;

/**
 * 
 * @author shailu.chougale
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationListResourceTest {

	/**
	 * mocking OrganizationService to retrieve the Organization details.
	 */
	@Mock
	private transient OrganizationService orgService;

	/**
	 * OrganizationListResource for unit test
	 */
	@InjectMocks
	private transient final OrganizationListResource organizationListResource = new OrganizationListResource();

	/**
	 * identifier to be passed
	 */
	private static final String IDENTIFIER = "0cfbceba-d143-4701-8e13-d270884a694d:3bdee582a7903b6e73a67a7c5b86fb7f";

	/**
	 * test {@link OrganizationListResource#getOrganizationByIdentifier(String)}
	 * for success
	 */
	@Test
	public void testGetOrganizationByIdentifierSuccess() {
		final Organization org = populateOrganization(1, "TEST SCHOOL 1");
		final String[] arrIdentifiers = IDENTIFIER
				.split(LegacyApiConstants.COLON);
		Mockito.when(orgService.getOrganizationByIdentifier(arrIdentifiers))
				.thenReturn(org);
		Mockito.when(orgService.getOrgDetails(org)).thenReturn(org);
		final Response response = organizationListResource
				.getOrganizationByIdentifier(IDENTIFIER);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * test {@link OrganizationListResource#getOrganizationByIdentifier(String)}
	 * for failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationByIdentifierFailure() {
		Mockito.when(orgService.getOrganizationByCode("invalid code"))
				.thenReturn(null);
		final Response response = organizationListResource
				.getOrganizationByIdentifier("invalid code");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * method to create organization object
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	private Organization populateOrganization(final int identifier,
			final String name) {
		final Organization organization = new Organization();
		organization.setId(identifier);
		organization.setName(name);
		return organization;
	}

}
