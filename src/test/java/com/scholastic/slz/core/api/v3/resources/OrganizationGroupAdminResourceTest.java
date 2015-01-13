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
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for organization group admin resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationGroupAdminResourceTest {

	/** the user service */
	@Mock
	private transient UserService userService;

	/** the organization group admin resource */
	@InjectMocks
	private final transient OrganizationGroupAdminResource orgGroupAdminResource = new OrganizationGroupAdminResource();

	/** the admin UUID */
	public static final String UUID = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";

	/** the admin user type */
	public static final String GADMIN = "gadmin";

	/**
	 * Tests
	 * {@link OrganizationGroupAdminResource#getOrganizationGroupAdminById(String)}
	 * for successful scenario
	 */
	@Test
	public void testGetOrganizationGroupAdminByIdSuccess() {
		final UserAccount groupAdmin = new UserAccount();
		groupAdmin.setUuid(UUID);
		groupAdmin.setType(GADMIN);
		groupAdmin.setFirstName("Test admin");

		Mockito.when(userService.getOrgGroupAdminById(UUID)).thenReturn(
				groupAdmin);

		final Response response = orgGroupAdminResource
				.getOrganizationGroupAdminById(UUID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests
	 * {@link OrganizationGroupAdminResource#getOrganizationGroupAdminById(String)}
	 * for failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrganizationGroupAdminByIdFailure() {
		Mockito.when(userService.getOrgGroupAdminById(UUID)).thenReturn(null);
		orgGroupAdminResource.getOrganizationGroupAdminById(UUID);
	}

}
