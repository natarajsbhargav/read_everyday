package com.scholastic.slz.core.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.UserDao;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	/** the user DAO */
	@Mock
	private UserDao userDAO;

	/** the user service */
	@InjectMocks
	private UserService userService = new UserService();

	/**
	 * Tests that the service returns the user for a given identifier and
	 * userType
	 */
	@Test
	public void testGetUserByIdentifierAndType() {
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "student1";
		final String type = "student";
		final UserAccount user = createUser(identifier, username, type);
		Mockito.when(userDAO.findUserByIdentifierAndType(identifier, type))
				.thenReturn(user);

		final UserAccount result = userService.getUserByIdentifier(identifier, type);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the user for a given identifier
	 */
	@Test
	public void testGetUserByIdentifier() {
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "student1";
		final String type = "student";
		final UserAccount user = createUser(identifier, username, type);
		Mockito.when(userDAO.getUserByCode(identifier)).thenReturn(user);

		final UserAccount result = userService.getUserByIdentifier(identifier);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the list of users for a given organization
	 * and grade
	 */
	@Test
	public void testGetUsersByOrgAndGrade() {
		Organization organization = new Organization();
		final String gradeCode = "PK";
		final String appCode = "SRII";
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "student1";
		final String type = "student";
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		userAccounts.add(createUser(identifier, username, type));

		Mockito.when(
				userDAO.findUsersByOrgAndGrade(organization, gradeCode, appCode))
				.thenReturn(userAccounts);

		final List<UserAccount> result = userService.getUsersByOrgAndGrade(
				organization, gradeCode, appCode);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the list of users for a given class and
	 * type
	 */
	@Test
	public void testGetUsersByClassIdAndType() {
		final Integer classId = Integer.valueOf("10");
		final String userType = "student";
		final String appCode = "SRII";
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "student1";
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		userAccounts.add(createUser(identifier, username, userType));
		Mockito.when(
				userDAO.findUsersByClassIdAndType(classId, userType, appCode))
				.thenReturn(userAccounts);

		final List<UserAccount> result = userService.getUsersByClassIdAndType(
				classId, userType, appCode);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the list of users for a given orgID,
	 * queryString match the username , firstname and lastname and type
	 */
	@Test
	public void testGetStudentByClasses() {
		final Integer orgID = Integer.valueOf("10");
		final String userType = "student";
		final String queryText = "test";
		final String appCode = "SRII";
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "student1";
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		userAccounts.add(createUser(identifier, username, userType));
		Mockito.when(
				userDAO.getStudentByClasses(userType, appCode, queryText, orgID))
				.thenReturn(userAccounts);

		final List<UserAccount> result = userService.getStudentByClasses(
				userType, appCode, queryText, orgID);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns a student user for a given organization id
	 * and user name
	 */

	@Test
	public void testGetStudentByOrgIdAndUsername() {
		String username = "student1";
		String type = "student";
		String uuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c7";
		Integer orgId = 1;
		UserAccount user = createUser(uuid, username, type);
		Mockito.when(userDAO.findStudentByOrgIdAndUsername(orgId, username))
				.thenReturn(user);

		UserAccount result = userService.getStudentByOrgIdAndUsername(orgId,
				username);
		assertNotNull(result);
	}

	/**
	 * Test that the service returns the administrator user for a given UUID
	 */
	@Test
	public void testGetAdminDetails() {
		final String identifier = "3b89669d-984b-49e6-b2c4-3c746f2906cb";
		final String username = "Administrator 1";
		final String type = "administrator";
		final UserAccount adminUserDetails = createUser(identifier, username,
				type);
		Mockito.when(userDAO.getOrganizationAdminDetails(identifier))
				.thenReturn(adminUserDetails);

		UserAccount adminDetails = userService.getAdminDetails(identifier);

		assertNotNull(adminDetails);
	}

	/**
	 * Testing the service returns the teacher for a given UUID
	 */
	@Test
	public void testGetTeacherDetails() {
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c1019";
		final String username = "teacher1";
		final String type = "teacher";
		final UserAccount teacherUserDetails = createUser(identifier, username,
				type);
		Mockito.when(userDAO.getTeacherInfo(identifier)).thenReturn(
				teacherUserDetails);

		final UserAccount teacherDetails = userService
				.getTeacherDetails(identifier);

		assertNotNull(teacherDetails);
	}

	/**
	 * Tests that the service returns the organization group administrative user
	 * for a given identifier
	 */
	@Test
	public void testGetOrgGroupAdminById() {
		final String identifier = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String username = "Administrator 1";
		final String type = "gadmin";
		UserAccount useraccount = createUser(identifier, username, type);
		Mockito.when(userDAO.findOrgGroupAdminById(identifier)).thenReturn(
				useraccount);

		UserAccount result = userService.getOrgGroupAdminById(identifier);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the user entitlement for a given UUID and
	 * product code
	 */
	@Test
	public void testGetUserEntitlementByProduct() {
		final String uuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";
		final String productCode = "GC";
		final String type = "student";
		final UserAccount user = createUser(uuid, productCode, type);
		Mockito.when(userDAO.findUserEntitlementByProduct(uuid, productCode))
				.thenReturn(user);

		final UserAccount result = userService.getUserEntitlementByProduct(uuid,
				productCode);
		assertNotNull(result);
	}

	/**
	 * Creates a user with the given UUID, user name and type
	 * 
	 * @param identifier
	 *            the UUID of the user
	 * @param username
	 *            the user name
	 * @param type
	 *            the user type (student/teacher/administrator/gadmin)
	 * @return the user
	 */
	private UserAccount createUser(final String identifier,
			final String username, final String type) {
		final UserAccount userAccount = new UserAccount();
		userAccount.setUuid(identifier);
		userAccount.setUsername(username);
		userAccount.setType(type);
		return userAccount;
	}

}