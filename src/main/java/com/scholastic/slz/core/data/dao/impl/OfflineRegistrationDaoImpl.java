package com.scholastic.slz.core.data.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.scholastic.slz.core.data.dao.OfflineRegistrationDao;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
public class OfflineRegistrationDaoImpl extends
		GenericDaoImpl<OfflineRegistration> implements OfflineRegistrationDao {

	/**
	 * Map to add parameters.
	 */
	private Map<String, Object> parameters;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OfflineRegistration> findByProductCode(
			final String productCode, final String machineUuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("productCode", productCode);
		parameters.put("machineUuid", machineUuid);
		final List<OfflineRegistration> offlineRegs = findWithNamedQuery(
				OfflineRegistration.FIND_BY_PRODUCT_MACHINE, parameters);
		return offlineRegs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<OfflineRegistration> removeRegistration(
			final List<OfflineRegistration> pOfflineRegs) {
		final List<OfflineRegistration> offlineRegs = new ArrayList<OfflineRegistration>();
		for (final OfflineRegistration offlineReg : pOfflineRegs) {
			offlineReg.setDeleted(DELETED.Y.name());
			offlineRegs.add(update(offlineReg));
		}
		return offlineRegs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OfflineRegistration> findByMachineUuid(final String machineUuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("machineUuid", machineUuid);
		final List<OfflineRegistration> offlineRegs = findWithNamedQuery(
				OfflineRegistration.FIND_BY_MACHINE, parameters);
		return offlineRegs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public OfflineRegistration verifyRegistration(
			final OfflineRegistration pOfflineReg) {
		OfflineRegistration offlineReg = null;
		if (pOfflineReg != null) {
			final Date now = Calendar.getInstance().getTime();
			pOfflineReg.setLastVerifiedDate(now);
			offlineReg = update(pOfflineReg);
		}
		return offlineReg;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfflineRegistration findByRegistrationUuid(
			final String registrationUuid) {
		parameters = new HashMap<String, Object>();
		parameters.put("registrationUuid", registrationUuid);
		final OfflineRegistration offlineReg = findSingleResultWithNamedQuery(
				OfflineRegistration.FIND_BY_REGISTRATION, parameters);
		return offlineReg;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OfflineRegistration> findByProductAndUser(
			final String machineUuid, final String productCode,
			final UserAccount user) {
		parameters = new HashMap<String, Object>();
		parameters.put("machineUuid", machineUuid);
		parameters.put("productCode", productCode);
		parameters.put("user", user);
		final List<OfflineRegistration> offlineRegs = findWithNamedQuery(
				OfflineRegistration.VALIDATE_BEFORE_REGISTRATION, parameters);
		return offlineRegs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public OfflineRegistration createOfflineRegistration(
			final OfflineRegistration poffReg) {
		if (poffReg != null) {
			update(poffReg);
		}
		return poffReg;
	}
}
