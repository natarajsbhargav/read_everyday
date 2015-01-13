package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

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
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for Admin Details resource
 * 
 * @author Madan D H
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminDetailsResourceTest {
	/** the UserAccount service */
	@Mock
	private transient UserService userService;

	/** the Admin details resource */
	@InjectMocks
	private final transient AdminDetailsResource admintUsernameResource = new AdminDetailsResource();

	/** the organization identifier */
	public static final String ADMIN_UUID = "04e28b8f-46f8-49b1-a36d-1360c8bff7c63";

	/**
	 * Tests {@link AdminDetailsResource#getAdminDetails(String)} for successful
	 * scenario
	 */
	@Test
	public void testGetAdminDetailsByUUID() {
		final UserAccount userAccount = new UserAccount();
		userAccount.setFirstName("fn57");
		userAccount.setLastName("ln57");
		userAccount.setUuid(ADMIN_UUID);
		
		Organization org = new Organization();
		org.setOrgGuid("088c9417413b487e773ca3bdec192cde");
		org.setName("TEST SCHOOL 1");
		userAccount.setOrganization(org);
		Mockito.when(
				userService
						.getAdminDetails(ADMIN_UUID))
				.thenReturn(userAccount);

		Response response = null;

		response = admintUsernameResource
				.getAdminDetails(ADMIN_UUID);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * Tests {@link AdminDetailsResource#getAdminDetails(String)} for
	 * UserAccount with the given id doesn't exists
	 * 
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetAdminDetailsWhenUuidNotFound()
			throws ApplicationException, SQLException {
		Mockito.when(
				userService
						.getAdminDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c65"))
				.thenReturn(null);
		admintUsernameResource
				.getAdminDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c65");
	}
	
	/**
	 * Tests {@link AdminDetailsResource#getAdminDetails(String)} for
	 * UserAccount with the given id is null
	 * 
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetAdminDetailsWhenUuidNull()
			throws ApplicationException, SQLException {
		Mockito.when(
				userService
						.getAdminDetails(null))
				.thenReturn(null);
		admintUsernameResource
				.getAdminDetails(null);
	}
}
