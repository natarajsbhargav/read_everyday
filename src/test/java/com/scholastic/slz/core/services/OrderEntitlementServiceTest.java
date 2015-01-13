package com.scholastic.slz.core.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.OrderEntitlementDao;
import com.scholastic.slz.core.data.model.OrderEntitlement;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderEntitlementServiceTest {

	/** the order entitlement DAO */
	@Mock
	private OrderEntitlementDao entitlementDao;

	/** the order entitlement service */
	@InjectMocks
	private OrderEntitlementService orderEntitlementService = new OrderEntitlementService();

	/**
	 * Tests that the service returns a list of order entitlements for a given
	 * machineUuid
	 */
	@Test
	public void testGetByOfflineMachineUuid() {

		final List<OrderEntitlement> orderEntitlements = new ArrayList<OrderEntitlement>();
		orderEntitlements.add(createOrderEntitlement(1, 15));
		final String machineUuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c101901";
		Mockito.when(entitlementDao.findByOfflineMachineUuid(machineUuid))
				.thenReturn(orderEntitlements);

		List<OrderEntitlement> result = orderEntitlementService
				.getByOfflineMachineUuid(machineUuid);
		assertNotNull(result);
	}

	/**
	 * Tests that the service returns the Order entitlements for a given
	 * machineUuid
	 */
	@Test
	public void testGetByOfflineRegistrationUuid() {
		final OrderEntitlement orderEntitlement = createOrderEntitlement(1, 15);
		final String registrationUuid = "04e28b8f-46f8-49b1-a36d-1360c8bff7c101901";
		Mockito.when(
				entitlementDao.findByOfflineRegistrationUuid(registrationUuid))
				.thenReturn(orderEntitlement);

		OrderEntitlement result = orderEntitlementService
				.getByOfflineRegistrationUuid(registrationUuid);
		assertNotNull(result);
	}

	/**
	 * Creates an order entitlement with a given identifier and grace period
	 * 
	 * @param identifier
	 *            the order entitlement identifier
	 * @param gracePeriodInDays
	 *            grace period in days
	 * @return the order entitlement
	 */
	private OrderEntitlement createOrderEntitlement(final Integer identifier,
			final Integer gracePeriodInDays) {
		final OrderEntitlement orderEntitlement = new OrderEntitlement();
		orderEntitlement.setId(identifier);
		orderEntitlement.setGracePeriodInDays(gracePeriodInDays);
		return orderEntitlement;
	}

}
