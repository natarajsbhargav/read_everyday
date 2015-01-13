package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.LegacyApiConstants.UserType;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.UserService;

/**
 * 
 * @author shailu.chougale
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentResourceTest {

	/** the user service for fetching user details */
	@Mock
	private transient UserService userService;

	/** the student resource for unit test */
	@InjectMocks
	private transient final StudentResource studentResource = new StudentResource();

	/** student Identifier for testing */
	private static final String IDENTIFIER = "04e28b8f-46f8-49b1-a36d-1360c8bff7c10190";

	/**
	 * tests {@link StudentResource#getStudentDetailsByIdentifier(String)} for
	 * success
	 */
	@Test
	public void testGetStudentDetailsByIdentifierSuccess() {
		final UserAccount student = populateStudent();
		Mockito.when(
				userService.getUserByIdentifier(IDENTIFIER, UserType.STUDENT
						.name().toLowerCase(Locale.getDefault()))).thenReturn(
				student);
		final Response response = studentResource
				.getStudentDetailsByIdentifier(IDENTIFIER);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * tests {@link StudentResource#getStudentDetailsByIdentifier(String)} for
	 * failure
	 */
	@Test(expected = ApplicationException.class)
	public void testGetStudentDetailsByIdentifierFailure() {
		Mockito.when(
				userService.getUserByIdentifier(IDENTIFIER, UserType.STUDENT
						.name().toLowerCase(Locale.getDefault()))).thenReturn(
				null);
		final Response response = studentResource
				.getStudentDetailsByIdentifier(IDENTIFIER);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * method to initialize student object
	 * 
	 * @return
	 */
	private UserAccount populateStudent() {
		final UserAccount student = new UserAccount();
		student.setId(1);
		student.setFirstName("first name");
		student.setLastName("last name");
		student.setType("student");
		return student;
	}

}
