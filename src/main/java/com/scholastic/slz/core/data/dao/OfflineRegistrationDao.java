package com.scholastic.slz.core.data.dao;

import java.util.List;

import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
public interface OfflineRegistrationDao extends GenericDao<OfflineRegistration> {

	/**
	 * @param productCode
	 * @param machineUuid
	 * @return list of registration
	 */
	List<OfflineRegistration> findByProductCode(String productCode,
			String machineUuid);

	/**
	 * @param offlineRegs
	 *            the list of OfflineRegistration
	 * @return updated the list of OfflineRegistration
	 */
	List<OfflineRegistration> removeRegistration(
			List<OfflineRegistration> offlineRegs);

	/**
	 * @param machineUuid
	 * @return list of OfflineRegistration
	 */
	List<OfflineRegistration> findByMachineUuid(String machineUuid);

	/**
	 * @param machineUuid
	 * @return list of verified OfflineRegistration
	 */
	OfflineRegistration verifyRegistration(OfflineRegistration pOfflineRegs);

	/**
	 * @param registrationUuid
	 * @return the OfflineRegistration
	 */
	OfflineRegistration findByRegistrationUuid(String registrationUuid);

	/**
	 * @param machineUuid
	 * @param productCode
	 * @param the
	 *            user
	 * @return the list of OfflineRegistration
	 */
	List<OfflineRegistration> findByProductAndUser(String machineUuid,
			String productCode, UserAccount user);

	/**
	 * @param offReg
	 * @return the created OfflineRegistration
	 */
	OfflineRegistration createOfflineRegistration(OfflineRegistration offReg);
}
