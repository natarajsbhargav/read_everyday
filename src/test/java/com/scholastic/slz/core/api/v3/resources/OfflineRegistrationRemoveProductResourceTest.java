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
public class OfflineRegistrationRemoveProductResourceTest {

	/** the Offline service */
	@Mock
	private transient OfflineService offlineService;

	/** the Offline Remove product Resource */
	@InjectMocks
	private transient final OfflineRegistrationRemoveProductResource removeMachineResource = new OfflineRegistrationRemoveProductResource();

	/** the Machine uuid */
	public static final String MACHINE_UUID = "Machine-01";

	/** the Product COde */
	public static final String PRODUCT_CODE = "PIW1A";

	/**
	 * Success Test method for removal of product registration by product and
	 * machine.
	 */
	@Test
	public final void testRemoveProductRegistrationSuccess() {
		final List<OfflineRegistration> offlineRegs = new ArrayList<OfflineRegistration>();
		final OfflineRegistration offlineReg = new OfflineRegistration();
		offlineReg.setId(1);
		offlineReg.setMachineUuid(MACHINE_UUID);
		offlineReg.setMachineName(MACHINE_UUID);
		offlineRegs.add(offlineReg);
		Mockito.when(
				offlineService.getByProductCode(PRODUCT_CODE, MACHINE_UUID))
				.thenReturn(offlineRegs);
		Mockito.when(offlineService.removeRegistration(offlineRegs))
				.thenReturn(true);
		final Response response = removeMachineResource
				.removeProductRegistration(PRODUCT_CODE, MACHINE_UUID);
		assertEquals("removeProductRegistration success test has cleared",
				Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Failure Test method for removal of product registration by product and
	 * machine.
	 */
	@Test(expected = ApplicationException.class)
	public final void testRemoveProductRegistrationFailure() {
		Mockito.when(
				offlineService.getByProductCode(PRODUCT_CODE, MACHINE_UUID))
				.thenReturn(null);
		removeMachineResource.removeProductRegistration(PRODUCT_CODE,
				MACHINE_UUID);
	}

}
