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

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OrganizationService;
import com.scholastic.slz.core.services.UserService;

/**
 * Tests the organization grade resource
 * 
 * @author snehalata.raulo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationGradeResourceTest {

	/** the Organization Service */
	@Mock
	private transient OrganizationService orgService;

	/** the User Service */
	@Mock
	private transient UserService userService;

	/** the OrganizationGrade Resource */
	@InjectMocks
	private transient final OrganizationGradeResource organizationGradeResource = new OrganizationGradeResource();

	/** the organization identifier */
	public static final String ORG_IDENTIFIER = "088c9417413b487e773ca3bdec192cde:3b5272ff-1648-48b3-8708-a5946b183244";

	/** the scholastic grade code */
	public static final String SCHOLASTIC_GRADE_CODE = "TUN";

	/**
	 * Success Test for the OrganizationGrade Resource returns organization
	 * details depending on the organization identifier
	 */
	@Test
	public void testGetOrgGradeInfo() {
		final String[] arrIdentifiers = ORG_IDENTIFIER
				.split(LegacyApiConstants.COLON);
		final Organization organization = new Organization();
		organization.setId(1);
		organization.setName("TestSchool 1");
		final UserAccount userAccount = new UserAccount();
		userAccount.setOrganization(organization);
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		userAccounts.add(userAccount);
		Mockito.when(orgService.getOrganizationByIdentifier(arrIdentifiers))
				.thenReturn(organization);
		Mockito.when(
				userService.getUsersByOrgAndGrade(organization,
						SCHOLASTIC_GRADE_CODE, "SRII"))
				.thenReturn(userAccounts);
		final Response response = organizationGradeResource.getOrgGradeInfo(
				ORG_IDENTIFIER, SCHOLASTIC_GRADE_CODE);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests that the OrganizationGrade resource throws application exception
	 * when scholastic grade code is null
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrgGradeInfoForNullGradeCode() {
		organizationGradeResource.getOrgGradeInfo(ORG_IDENTIFIER, null);
	}

	/**
	 * Tests that the OrganizationGrade resource throws application exception
	 * when no organizations exist with a given identifier
	 */
	@Test(expected = ApplicationException.class)
	public void testGetOrgGradeInfoNoOrgExists() {
		final String[] arrIdentifiers = ORG_IDENTIFIER
				.split(LegacyApiConstants.COLON);
		Mockito.when(orgService.getOrganizationByIdentifier(arrIdentifiers))
				.thenReturn(null);
		organizationGradeResource.getOrgGradeInfo(ORG_IDENTIFIER,
				SCHOLASTIC_GRADE_CODE);
	}

}
