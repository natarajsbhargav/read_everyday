package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for Admin Details resource
 * 
 * @author Madan D H
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TeacherResourceTest {
	/** the UserAccount service */
	@Mock
	private transient UserService userService;

	/** the Teacher details resource */
	@InjectMocks
	private final transient TeacherResource teacherDetailsResource = new TeacherResource();

	/** the organization identifier */
	public static final String TEACHER_UUID = "04e28b8f-46f8-49b1-a36d-1360c8bff7c1019";

	/**
	 * Tests {@link TeacherDetailsResource#getTeacherDetails(String)} for
	 * successful scenario
	 */
	@Test
	public void testGetTeacherDetailsSuccess() {
		final UserAccount userAccount = new UserAccount();
		userAccount.setFirstName("fn1013");
		userAccount.setLastName("ln1013");
		userAccount.setUuid("04e28b8f-46f8-49b1-a36d-1360c8bff7c1019");
		ClassInfo classInfo = new ClassInfo();
		classInfo.setUuid("0b9e058c-39c8-49d5-8c1c-dfd585fdaca88");
		classInfo.setName("class1");
		Set<ClassInfo> classInfoSet = new HashSet<ClassInfo>();
		classInfoSet.add(classInfo);
		Organization org = new Organization();
		org.setOrgUuid("61ea719940fd34f889af8f8aca6f6a5d");
		org.setName("TEST SCHOOL 17");
		userAccount.setOrganization(org);
		userAccount.getOrganization().setClasses(classInfoSet);
		Mockito.when(
				userService
						.getTeacherDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c1019"))
				.thenReturn(userAccount);

		Response response = teacherDetailsResource
				.getTeacherDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c1019");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * Tests {@link TeacherDetailsResource#getTeacherDetails(String)} for
	 * UserAccount with the given id doesn't exists
	 * 
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetTeacherInfoUuidNotFound() throws ApplicationException,
			SQLException {
		Mockito.when(
				userService
						.getTeacherDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c65"))
				.thenReturn(null);
		teacherDetailsResource
				.getTeacherDetails("04e28b8f-46f8-49b1-a36d-1360c8bff7c65");
	}
	
	
	/**
	 * Tests {@link TeacherDetailsResource#getTeacherDetails(String)} for
	 * UserAccount with the given id is null
	 * 
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetTeacherInfoUuidNull() {
		Mockito.when(
				userService
						.getTeacherDetails(null))
				.thenReturn(null);
		teacherDetailsResource
				.getTeacherDetails(null);
	}
}
