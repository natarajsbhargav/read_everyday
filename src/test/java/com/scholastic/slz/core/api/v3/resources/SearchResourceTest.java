package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.ClassService;
import com.scholastic.slz.core.services.OrganizationGroupService;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class SearchResourceTest {

	/** The organization service */
	@Mock
	private transient OrganizationService orgService;

	/** The organization group service */
	@Mock
	private transient OrganizationGroupService orgGroupService;

	/** The user service */
	@Mock
	private transient UserService userService;

	/** The Class service */
	@Mock
	private transient ClassService classService;

	/** The SearchResource to test */
	@InjectMocks
	private transient final SearchResource searchResource = new SearchResource();

	/** the organization GUID */
	public static final String ORG_GUID = "088c9417413b487e773ca3bdec192cde";

	/** the organization UUID */
	public static final String ORG_UUID = "3b5272ff-1648-48b3-8708-a5946b183244";

	/** the organization ID */
	public static final String ORG_ID = "088c9417413b487e773ca3bdec192cde:3b5272ff-1648-48b3-8708-a5946b183244";

	/** the user ID */
	private static final String USER_ID = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";

	/** the query string */
	private static final String QUERY_STRING = "test";

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for student success
	 */
	@Test
	public void testGetSearchResultsSuccessForStudent() {

		Mockito.when(userService.getUserByIdentifier(USER_ID)).thenReturn(
				populateUser());

		Mockito.when(classService.getClassesForTeacher(1, QUERY_STRING))
				.thenReturn(populateClassInfo());
		Mockito.when(
				classService.getClassesWithQuery(ORG_UUID, ORG_GUID,
						QUERY_STRING)).thenReturn(populateClassInfo());

		final Response response = searchResource.getSearchResults(QUERY_STRING,
				USER_ID, ORG_ID, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for Admin success
	 */
	@Test
	public void testGetSearchResultsSuccessForAdmin() {

		final UserAccount userAccount = populateUser();
		userAccount.setType("gadmin");
		Mockito.when(userService.getUserByIdentifier(USER_ID)).thenReturn(
				userAccount);
		Mockito.when(
				userService.getStudentByClasses("student", "SRII", "%test%", 1))
				.thenReturn(populateUsers());

		Mockito.when(classService.getClassesForTeacher(1, QUERY_STRING))
				.thenReturn(populateClassInfo());
		Mockito.when(
				classService.getClassesWithQuery(ORG_UUID, ORG_GUID,
						QUERY_STRING)).thenReturn(populateClassInfo());

		Mockito.when(orgService.getOrganizationsByName(QUERY_STRING))
				.thenReturn(populateOrganizations());
		Mockito.when(
				orgService.getOrganizationByNameAndId(QUERY_STRING, ORG_GUID,
						ORG_UUID)).thenReturn(populateOrganization());
		Mockito.when(
				orgService.getOrganizationByIdAndGroupName(QUERY_STRING,
						ORG_GUID, ORG_UUID)).thenReturn(populateOrganization());

		Mockito.when(orgGroupService.getOrganizationGroupsByName(QUERY_STRING))
				.thenReturn(populateOrganisationGroups());

		final Response response = searchResource.getSearchResults(QUERY_STRING,
				USER_ID, ORG_ID, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for empty organisation success
	 */
	@Test
	public void testGetSearchResultsSuccessForEmptyOrganisation() {

		final UserAccount userAccount = populateUser();
		userAccount.setType("gadmin");
		Mockito.when(userService.getUserByIdentifier(USER_ID)).thenReturn(
				userAccount);
		Mockito.when(
				userService.getStudentByClasses("gadmin", "SRII", QUERY_STRING,
						1)).thenReturn(populateUsers());

		Mockito.when(classService.getClassesForTeacher(1, QUERY_STRING))
				.thenReturn(populateClassInfo());
		Mockito.when(
				classService.getClassesWithQuery(ORG_UUID, ORG_GUID,
						QUERY_STRING)).thenReturn(populateClassInfo());

		final Response response = searchResource.getSearchResults(QUERY_STRING,
				USER_ID, null, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for teacher success
	 */
	@Test
	public void testGetSearchResultsSuccessForTeacher() {

		final UserAccount userAccount = populateUser();
		userAccount.setType("teacher");
		Mockito.when(userService.getUserByIdentifier(USER_ID)).thenReturn(
				userAccount);
		Mockito.when(
				userService.getStudentByClasses("teacher", "SRII",
						QUERY_STRING, 1)).thenReturn(populateUsers());

		Mockito.when(classService.getClassesForTeacher(1, QUERY_STRING))
				.thenReturn(populateClassInfo());
		Mockito.when(
				classService.getClassesWithQuery(ORG_UUID, ORG_GUID,
						QUERY_STRING)).thenReturn(populateClassInfo());

		final Response response = searchResource.getSearchResults(QUERY_STRING,
				USER_ID, ORG_ID, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for Empty user ID failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetSearchResultsfailureForEmptyUserId() {

		final Response response = searchResource.getSearchResults(QUERY_STRING,
				null, ORG_ID, null);

		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for Invalid pattern failure
	 */
	@Test(expected = ApplicationException.class)
	final public void testGetSearchResultsfailureForInvalidPatter() {
		final Response response = searchResource.getSearchResults("te",
				USER_ID, ORG_ID, null);

		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	/**
	 * tests
	 * {@link SearchResource#getSearchResults(String ,String, String,String)}
	 * for Empty pattern failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetSearchResultsfailureForEmptyPatter() {
		final Response response = searchResource.getSearchResults(null,
				USER_ID, ORG_ID, null);

		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	/**
	 * method to create list of user objects
	 * 
	 * @return user objects
	 */
	public List<UserAccount> populateUsers() {
		final List<UserAccount> users = new ArrayList<UserAccount>();
		users.add(populateUser());
		return users;
	}

	/**
	 * method to create list of user object
	 * 
	 * @return user objects
	 */
	public UserAccount populateUser() {
		final UserAccount userAccount = new UserAccount();
		userAccount.setEmail("user@gmail.com");
		userAccount.setId(1);
		userAccount.setFirstName("firstname");
		userAccount.setLastName("lastname");
		userAccount.setType("student");
		userAccount.setOrganization(populateOrganization());
		return userAccount;
	}

	/**
	 * method to create class information objects
	 * 
	 * @return user objects
	 */
	public List<ClassInfo> populateClassInfo() {
		final List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		final ClassInfo classInfo = new ClassInfo();
		classInfo.setId(1);
		classInfo.setName("testclass");
		classInfoList.add(new ClassInfo());
		return classInfoList;
	}

	/**
	 * method to create list of organization group objects
	 * 
	 * @return user objects
	 */
	public List<OrganizationGroup> populateOrganisationGroups() {
		final List<OrganizationGroup> organizationGroupList = new ArrayList<OrganizationGroup>();
		final OrganizationGroup organizationGroup = new OrganizationGroup();
		organizationGroup.setId(1);
		organizationGroup.setName("testOrganisation");
		organizationGroupList.add(organizationGroup);
		return organizationGroupList;
	}

	/**
	 * method to create list of organization objects
	 * 
	 * @return user objects
	 */
	public List<Organization> populateOrganizations() {

		final List<Organization> orgs = new ArrayList<Organization>();
		orgs.add(populateOrganization());
		return orgs;

	}

	/**
	 * method to create list of organization object
	 * 
	 * @return user objects
	 */
	private Organization populateOrganization() {
		final Organization organization = new Organization();
		organization.setId(1);
		organization.setName("organization");
		return organization;
	}

}
