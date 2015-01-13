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
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for student username resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentUsernameResourceTest {

	/** the organization service */
	@Mock
	private transient OrganizationService orgService;

	/** the user service */
	@Mock
	private transient UserService userService;

	/** the student username resource */
	@InjectMocks
	private final transient StudentUsernameResource studentUsernameResource = new StudentUsernameResource();

	/** the organization identifier */
	public static final String ORG_ID = "088c9417413b487e773ca3bdec192cde:3b5272ff-1648-48b3-8708-a5946b183244";

	/** the user name */
	public static final String USERNAME = "testuser";

	/** the student user type */
	public static final String STUDENT = "student";

	/**
	 * Tests
	 * {@link StudentUsernameResource#getStudentByUsername(String, String)} for
	 * successful scenario
	 */
	@Test
	public void testGetStudentByUsernameSuccess() {
		final Organization org = new Organization();
		org.setId(1);
		org.setName("Test Org");
		Mockito.when(
				orgService.getOrganizationByIdentifier(ORG_ID
						.split(LegacyApiConstants.COLON))).thenReturn(org);

		final UserAccount user = new UserAccount();
		user.setUsername(USERNAME);
		user.setType(STUDENT);
		Mockito.when(userService.getStudentByOrgIdAndUsername(1, USERNAME))
				.thenReturn(user);

		final Response response = studentUsernameResource.getStudentByUsername(
				ORG_ID, USERNAME);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests
	 * {@link StudentUsernameResource#getStudentByUsername(String, String)} when
	 * organization with the given id doesn't exists
	 */
	@Test(expected = ApplicationException.class)
	public void testGetStudentByUsernameOrgNotFound() {
		Mockito.when(
				orgService.getOrganizationByIdentifier(ORG_ID
						.split(LegacyApiConstants.COLON))).thenReturn(null);
		studentUsernameResource.getStudentByUsername(ORG_ID, USERNAME);
	}

	/**
	 * Tests
	 * {@link StudentUsernameResource#getStudentByUsername(String, String)} when
	 * student with the given user name doesn't exists
	 */
	@Test(expected = ApplicationException.class)
	public void testGetStudentByUsernameUserNotFound() {
		final Organization org = new Organization();
		org.setId(1);
		org.setName("Test Org");
		Mockito.when(
				orgService.getOrganizationByIdentifier(ORG_ID
						.split(LegacyApiConstants.COLON))).thenReturn(org);

		Mockito.when(userService.getStudentByOrgIdAndUsername(1, USERNAME))
				.thenReturn(null);

		studentUsernameResource.getStudentByUsername(ORG_ID, USERNAME);
	}

	/**
	 * Tests
	 * {@link StudentUsernameResource#getStudentByUsername(String, String)} for
	 * invalid organization id
	 */
	@Test(expected = ApplicationException.class)
	public void testGetStudentByUsernameInvalidOrgId() {
		studentUsernameResource.getStudentByUsername(
				"088c9417413b487e773ca3bdec192cde", USERNAME);
	}
}
