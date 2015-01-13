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
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.services.OfflineService;
import com.scholastic.slz.core.services.OrderEntitlementService;

/**
 * Test class for offline registration verify machine resource
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OfflineRegistrationVerifyMachineResourceTest {

	/** the offline service */
	@Mock
	private transient OfflineService offlineService;

	@Mock
	/** the order entitlement service */
	private transient OrderEntitlementService entitlementService;

	/** the offline registration verify machine resource */
	@InjectMocks
	private transient final OfflineRegistrationVerifyMachineResource verifyMachineRes = new OfflineRegistrationVerifyMachineResource();

	/** the machine UUID */
	public static final String MACHINE_UUID = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca90";

	/**
	 * Tests
	 * {@link OfflineRegistrationVerifyMachineResource#verifyRegistrationByMachine(String)}
	 * for successful scenario
	 */
	@Test
	public void testVerifyRegistrationByMachineSuccess() {
		final OfflineRegistration offlineReg = new OfflineRegistration();
		offlineReg.setId(1);
		offlineReg.setMachineName("Machine 1");

		final List<OfflineRegistration> offlineRegs = new ArrayList<OfflineRegistration>();
		offlineRegs.add(offlineReg);

		final OrderEntitlement orderEntitlement = new OrderEntitlement();
		orderEntitlement.setId(1);

		final List<OrderEntitlement> orderEntitlements = new ArrayList<OrderEntitlement>();
		orderEntitlements.add(orderEntitlement);

		Mockito.when(offlineService.getByMachineUuid(MACHINE_UUID)).thenReturn(
				offlineRegs);
		Mockito.when(offlineService.verifyRegistration(offlineRegs))
				.thenReturn(offlineRegs);
		Mockito.when(entitlementService.getByOfflineMachineUuid(MACHINE_UUID))
				.thenReturn(orderEntitlements);

		final Response response = verifyMachineRes
				.verifyRegistrationByMachine(MACHINE_UUID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Tests that
	 * {@link OfflineRegistrationVerifyMachineResource#verifyRegistrationByMachine(String)}
	 * throws application exception when machine UUID is null
	 */
	@Test(expected = ApplicationException.class)
	public void testVerifyRegistrationByMachineFailure() {
		verifyMachineRes.verifyRegistrationByMachine(null);
	}
}
