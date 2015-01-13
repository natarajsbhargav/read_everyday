package com.scholastic.slz.core.data.dao;

import java.util.List;

import com.scholastic.slz.core.data.model.OrderEntitlement;

/**
 * @author snehalata.raulo
 * 
 */
public interface OrderEntitlementDao extends GenericDao<OrderEntitlement> {

	/**
	 * @param machineUuid
	 * @return the list of OrderEntitlement
	 */
	List<OrderEntitlement> findByOfflineMachineUuid(String machineUuid);

	/**
	 * @param registrationUuid
	 * @return the OrderEntitlement
	 */
	OrderEntitlement findByOfflineRegistrationUuid(String registrationUuid);

}
