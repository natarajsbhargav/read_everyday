package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholastic.slz.core.data.dao.OrderEntitlementDao;
import com.scholastic.slz.core.data.model.OrderEntitlement;

/**
 * @author snehalata.raulo
 * 
 */
public class OrderEntitlementDaoImpl extends GenericDaoImpl<OrderEntitlement>
		implements OrderEntitlementDao {

	/**
	 * Map to add parameters.
	 */
	private Map<String, Object> parameters;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OrderEntitlement> findByOfflineMachineUuid(
			final String machineUuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("machineUuid", machineUuid);
		final List<OrderEntitlement> entitlements = findWithNamedQuery(
				OrderEntitlement.FIND_BY_OFFLINE_MACHINE_UUID, parameters);
		return entitlements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderEntitlement findByOfflineRegistrationUuid(
			final String registrationUuid) {
		OrderEntitlement entitlement = null;
		parameters = new HashMap<String, Object>();
		parameters.put("registrationUuid", registrationUuid);
		final List<OrderEntitlement> entitlements = findWithNamedQuery(
				OrderEntitlement.FIND_BY_OFFLINE_REGISTRATION_UUID, parameters);
		if (entitlements != null && entitlements.size() == 1) {
			entitlement = entitlements.get(0);
		}
		return entitlement;
	}

}
