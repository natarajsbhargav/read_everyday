/**
 * 
 */
package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.OrderEntitlementDao;
import com.scholastic.slz.core.data.model.OrderEntitlement;

/**
 * @author snehalata.raulo
 * 
 */
public class OrderEntitlementService {

	/** The Order Entitlement DAO */
	@Inject
	private transient OrderEntitlementDao entitlementDao;

	/**
	 * @param machineUuid
	 *            The offline registration machine uuid
	 * @return the list of Order entitlements
	 */
	public List<OrderEntitlement> getByOfflineMachineUuid(
			final String machineUuid) {
		return entitlementDao.findByOfflineMachineUuid(machineUuid);
	}

	/**
	 * @param registrationUuid
	 * @return the Order entitlement
	 */
	public OrderEntitlement getByOfflineRegistrationUuid(
			final String registrationUuid) {
		return entitlementDao.findByOfflineRegistrationUuid(registrationUuid);
	}

}
