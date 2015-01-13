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
import com.scholastic.slz.core.services.UserService;

/**
 * Test class for student lexile update resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentLexileUpdateResourceTest {

	/** the user service */
	@Mock
	private transient UserService userService;

	/** the student lexile update resource */
	@InjectMocks
	private transient final StudentLexileUpdateResource lexileUpdateRes = new StudentLexileUpdateResource();

	/** the lexile score */
	public static final int LEXILE_SCORE = 1;

	/** the lexile source */
	public static final String LEXILE_SOURCE = "intl";

	/** the user identifier */
	public static final String USER_UUID = "04e28b8f-46f8-49b1-a36d-1360c8bff7c7";

	/**
	 * Tests
	 * {@link StudentLexileUpdateResource#studentLexileUpdate(String, String)}
	 * for successful scenario
	 */
	@Test
	public void testStudentLexileUpdateSuccess() {
		Mockito.doNothing().when(userService)
				.putStudentLexile(USER_UUID, LEXILE_SOURCE, LEXILE_SCORE);
		final Response response = lexileUpdateRes.studentLexileUpdate(
				USER_UUID, String.valueOf(LEXILE_SCORE));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests that
	 * {@link StudentLexileUpdateResource#studentLexileUpdate(String, String)}
	 * throws application exception when lexile score format is invalid
	 */
	@Test(expected = ApplicationException.class)
	public void testStudentLexileUpdateInvalidLexileScore() {
		lexileUpdateRes.studentLexileUpdate(USER_UUID, "TEST");
	}

	/**
	 * Tests that
	 * {@link StudentLexileUpdateResource#studentLexileUpdate(String, String)}
	 * throws application exception when lexile score is empty
	 */
	@Test(expected = ApplicationException.class)
	public void testStudentLexileUpdateEmptyLexileScore() {
		lexileUpdateRes.studentLexileUpdate(USER_UUID, "");
	}

	/**
	 * Tests that
	 * {@link StudentLexileUpdateResource#studentLexileUpdate(String, String)}
	 * throws application exception when the user UUID is empty
	 */
	@Test(expected = ApplicationException.class)
	public void testStudentLexileUpdateEmptyUserId() {
		lexileUpdateRes.studentLexileUpdate("", String.valueOf(LEXILE_SCORE));
	}
}
