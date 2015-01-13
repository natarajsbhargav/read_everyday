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
import com.scholastic.slz.core.api.v3.form.OfflineRegistrationForm;
import com.scholastic.slz.core.data.model.UserAccount;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.UserService;

/**
 * @author snehalata.raulo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OfflineRegistrationResourceTest {

	/** the Offline service */
	@Mock
	private transient OfflineService offlineService;

	/** the User service */
	@Mock
	private transient UserService userService;

	/** the Offline Remove Machine Resource */
	@InjectMocks
	private transient final OfflineRegistrationResource offlineRegistrationResource = new OfflineRegistrationResource();

	/**
	 * Success Test method for Offline Registration
	 */
	@Test
	public final void testGetNewOfflineRegistrationSuccess() {
		final OfflineRegistrationForm form = new OfflineRegistrationForm();
		form.setMachineName("Machine 01");
		form.setMachineUuid("Machine-01");
		form.setNumOfSeats(10);
		form.setProductCode("PIW1A");
		form.setRegistrationUuid("3378699c-fbf7-438c-baf5-0096ded3469a");
		form.setUserIdentifier("b95628678b24af800b9af3cf1a5a0887");
		final UserAccount user = new UserAccount();
		user.setUuid("b95628678b24af800b9af3cf1a5a0887");
		Mockito.when(
				userService.getUserEntitlementByProduct(
						"b95628678b24af800b9af3cf1a5a0887", "PIW1A"))
				.thenReturn(user);
		final Integer numOfSeats = 10;
		Mockito.when(offlineService.geNumOfSeats(user)).thenReturn(numOfSeats);
		Mockito.when(offlineService.validateAndCreateRegistration(form, user))
				.thenReturn(true);
		final Response response = offlineRegistrationResource
				.getNewOfflineRegistration(form);
		assertEquals("getAllRegions success test has cleared",
				Status.OK.getStatusCode(), response.getStatus());

	}

	/**
	 * Failure Test method for Offline Registration
	 */
	@Test(expected = ApplicationException.class)
	public final void testGetNewOfflineRegistrationFailure() {
		final OfflineRegistrationForm form = new OfflineRegistrationForm();
		form.setMachineName("Machine 01");
		form.setMachineUuid("Machine-01");
		form.setNumOfSeats(10);
		form.setProductCode("PIW1A");
		form.setRegistrationUuid("3378699c-fbf7-438c-baf5-0096ded3469a");
		form.setUserIdentifier("b95628678b24af800b9af3cf1a5a0887");
		final UserAccount user = new UserAccount();
		user.setUuid("b95628678b24af800b9af3cf1a5a0887");
		Mockito.when(
				userService.getUserEntitlementByProduct(
						"b95628678b24af800b9af3cf1a5a0887", "PIW1A"))
				.thenReturn(user);
		final Integer numOfSeats = 10;
		Mockito.when(offlineService.geNumOfSeats(user)).thenReturn(numOfSeats);
		Mockito.when(offlineService.validateAndCreateRegistration(form, user))
				.thenReturn(false);
		offlineRegistrationResource.getNewOfflineRegistration(form);
	}

}
