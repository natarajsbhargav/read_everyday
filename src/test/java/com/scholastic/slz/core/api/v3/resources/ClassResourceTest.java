package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.ClassService;
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for the class resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassResourceTest {

	/** the class service */
	@Mock
	private transient ClassService classService;

	/** the user service */
	@Mock
	private transient UserService userService;

	/** the class resource */
	@InjectMocks
	private transient final ClassResource classResource = new ClassResource();

	/** the organization identifier */
	public static final String ORG_ID = "088c9417413b487e773ca3bdec192cde:3b5272ff-1648-48b3-8708-a5946b183244";

	/** the class identifier */
	public static final String CLASS_ID = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca8";

	/** the application code */
	public static final String APP_CODE = "EBB";

	/**
	 * Tests {@link ClassResource#getClassByIdentifier(String)} for successful
	 * scenario
	 */
	@Test
	public void testGetClassByIdentifierSuccess() {
		final ClassInfo classInfo = new ClassInfo();
		classInfo.setId(1);
		classInfo.setName("Test Class");

		final List<UserAccount> teachers = new ArrayList<UserAccount>();
		final UserAccount teacher = new UserAccount();
		teacher.setFirstName("Teacher 1");
		teacher.setType(LegacyApiConstants.UserType.TEACHER.name());
		teachers.add(teacher);

		final List<UserAccount> students = new ArrayList<UserAccount>();
		final UserAccount student = new UserAccount();
		student.setFirstName("Student 1");
		student.setType(LegacyApiConstants.UserType.STUDENT.name());
		students.add(student);

		Mockito.when(classService.getClassByUuid(CLASS_ID)).thenReturn(
				classInfo);
		Mockito.when(
				userService.getUsersByClassIdAndType(
						classInfo.getId(),
						LegacyApiConstants.UserType.TEACHER.name().toLowerCase(
								Locale.getDefault()), APP_CODE)).thenReturn(
				teachers);
		Mockito.when(
				userService.getUsersByClassIdAndType(
						classInfo.getId(),
						LegacyApiConstants.UserType.STUDENT.name().toLowerCase(
								Locale.getDefault()), APP_CODE)).thenReturn(
				students);

		final Response response = classResource.getClassByIdentifier(ORG_ID
				+ LegacyApiConstants.COLON + CLASS_ID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests that {@link ClassResource#getClassByIdentifier(String)} throws
	 * application exception when class with a given identifier doesn't exists
	 */
	@Test(expected = ApplicationException.class)
	public void testGetClassByIdentifierNoClassFound() {
		Mockito.when(classService.getClassByUuid(CLASS_ID)).thenReturn(null);
		classResource.getClassByIdentifier(ORG_ID + LegacyApiConstants.COLON
				+ CLASS_ID);
	}

	/**
	 * Tests that {@link ClassResource#getClassByIdentifier(String)} throws
	 * application exception for invalid identifier
	 */
	@Test(expected = ApplicationException.class)
	public void testGetClassByIdentifierInvalidId() {
		classResource.getClassByIdentifier(CLASS_ID);
	}

}