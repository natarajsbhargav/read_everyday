/**
 * 
 */
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
import com.scholastic.slz.core.services.OfflineService;

/**
 * @author snehalata.raulo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OfflineRegistrationRemoveMachineResourceTest {

	/** the Offline service */
	@Mock
	private transient OfflineService offlineService;

	/** the Offline Remove Machine Resource */
	@InjectMocks
	private transient final OfflineRegistrationRemoveMachineResource removeMachineResource = new OfflineRegistrationRemoveMachineResource();

	/** the Machine uuid */
	public static final String MACHINE_UUID = "Machine-01";

	/**
	 * Success Test method for removal of product registration by machine.
	 */
	@Test
	public final void testRemoveMachineRegistrationSuccess() {
		final List<OfflineRegistration> offlineRegs = new ArrayList<OfflineRegistration>();
		final OfflineRegistration offlineReg = new OfflineRegistration();
		offlineReg.setId(1);
		offlineReg.setMachineUuid(MACHINE_UUID);
		offlineReg.setMachineName(MACHINE_UUID);
		offlineRegs.add(offlineReg);
		Mockito.when(offlineService.getByMachineUuid(MACHINE_UUID)).thenReturn(
				offlineRegs);
		Mockito.when(offlineService.removeRegistration(offlineRegs))
				.thenReturn(true);
		final Response response = removeMachineResource
				.removeMachineRegistration(MACHINE_UUID);
		assertEquals("removeMachineRegistration success test has cleared",
				Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Failure Test method for removal of product registration.
	 */
	@Test(expected = ApplicationException.class)
	public final void testRemoveMachineRegistrationFailure() {
		Mockito.when(offlineService.getByMachineUuid(MACHINE_UUID)).thenReturn(
				null);
		removeMachineResource.removeMachineRegistration(MACHINE_UUID);
	}

}
