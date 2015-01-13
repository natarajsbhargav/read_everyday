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
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.OrderEntitlementService;

/**
 * Test class for offline registration verify registration resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OfflineRegistrationVerifyRegistrationResourceTest {

	/** the offline service */
	@Mock
	private transient OfflineService offlineService;

	@Mock
	/** the order entitlement service */
	private transient OrderEntitlementService entitlementService;

	/** the offline registration verify registration resource */
	@InjectMocks
	private transient final OfflineRegistrationVerifyRegistrationResource verifyRegistrationRes = new OfflineRegistrationVerifyRegistrationResource();

	/** the registration UUID */
	public static final String REGISTRATION_UUID = "114d7b8c-6101-4736-b0a9-28e86ccb1b45";

	/**
	 * Tests
	 * {@link OfflineRegistrationVerifyRegistrationResource#verifyRegistrationByUuid(String)}
	 * for successful scenario
	 */
	@Test
	public void testVerifyRegistrationByUuidSuccess() {
		final OfflineRegistration offlineReg = new OfflineRegistration();
		offlineReg.setId(1);
		offlineReg.setMachineName("Machine 1");

		final OrderEntitlement orderEntitlement = new OrderEntitlement();
		orderEntitlement.setId(1);

		Mockito.when(offlineService.getByRegistrationUuid(REGISTRATION_UUID))
				.thenReturn(offlineReg);
		Mockito.when(offlineService.verifyRegistration(offlineReg)).thenReturn(
				offlineReg);
		Mockito.when(
				entitlementService
						.getByOfflineRegistrationUuid(REGISTRATION_UUID))
				.thenReturn(orderEntitlement);
		final Response response = verifyRegistrationRes
				.verifyRegistrationByUuid(REGISTRATION_UUID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests that
	 * {@link OfflineRegistrationVerifyRegistrationResource#verifyRegistrationByUuid(String)}
	 * throws application exception when registration UUID is null
	 */
	@Test(expected = ApplicationException.class)
	public void testVerifyRegistrationByUuidFailure() {
		verifyRegistrationRes.verifyRegistrationByUuid(null);
	}
}
