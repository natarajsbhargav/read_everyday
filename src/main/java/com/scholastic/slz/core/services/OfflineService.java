/**
 * 
 */
package com.scholastic.slz.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.v3.form.OfflineRegistrationForm;
import com.scholastic.slz.core.data.dao.GenericDao.DELETED;
import com.scholastic.slz.core.data.dao.OfflineRegistrationDao;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.data.model.Product;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
public class OfflineService {

	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OfflineService.class);

	/** The Offline Registration DAO */
	@Inject
	private transient OfflineRegistrationDao offlineRegDao;

	/**
	 * @param user
	 * @return number of seats available for a order
	 */
	public Integer geNumOfSeats(final UserAccount user) {
		Integer numOfSeats = null;
		final Set<Product> products = user.getProducts();
		if (products != null && !products.isEmpty()) {
			final Product product = products.iterator().next();
			final Set<OrderEntitlement> orders = product.getOrderEntitlements();
			if (orders != null && !orders.isEmpty()) {
				final OrderEntitlement order = orders.iterator().next();
				if (order != null) {
					LOGGER.debug(
							"The number of seat licenses for the order {}",
							order.getNumOfSeats());
					numOfSeats = order.getNumOfSeats();
				}
			}
		}
		return numOfSeats;
	}

	/**
	 * @param machineUuid
	 * @return list of OfflineRegistration
	 */
	public List<OfflineRegistration> getByMachineUuid(final String machineUuid) {
		return offlineRegDao.findByMachineUuid(machineUuid);
	}

	/**
	 * @param productCode
	 *            the product code
	 * @param machineUuid
	 *            the machine uuid
	 * @return list of OfflineRegistration
	 * 
	 */
	public List<OfflineRegistration> getByProductCode(final String productCode,
			final String machineUuid) {
		return offlineRegDao.findByProductCode(productCode, machineUuid);
	}

	/**
	 * @param registrationUuid
	 * @return the OfflineRegistration
	 */
	public OfflineRegistration getByRegistrationUuid(
			final String registrationUuid) {
		return offlineRegDao.findByRegistrationUuid(registrationUuid);
	}

	/**
	 * 
	 * @param offlineRegs
	 *            the list of OfflineRegistration object
	 * @return true/false for the updated falg
	 * 
	 */
	private Boolean getRemoveFlag(final List<OfflineRegistration> pOfflineRegs) {
		boolean bFound = true;
		for (final OfflineRegistration offlineReg : pOfflineRegs) {
			final String status = offlineReg.getDeleted();
			final String deleted = DELETED.Y.name();
			bFound = deleted.equals(status);
			if (!bFound) {
				break;
			}
		}
		return bFound;
	}

	/**
	 * 
	 * @param offlineRegs
	 *            the list of OfflineRegistration object
	 * @return true/false for the updated product object
	 * 
	 */
	public Boolean removeRegistration(
			final List<OfflineRegistration> offlineRegs) {
		Boolean updateFlag = false;
		if (offlineRegs != null && !offlineRegs.isEmpty()) {
			final List<OfflineRegistration> nOfflineRegs = offlineRegDao
					.removeRegistration(offlineRegs);
			updateFlag = getRemoveFlag(nOfflineRegs);
		}
		return updateFlag;
	}

	/**
	 * @param regArray
	 * @return true/false If registered return true else false
	 */
	public Boolean validateAndCreateRegistration(
			final OfflineRegistrationForm form, final UserAccount user) {
		Boolean validate = false;
		final String productCode = form.getProductCode();
		final String regUuid = form.getRegistrationUuid();
		final String machineUuid = form.getMachineUuid();
		final String machineName = form.getMachineName();
		final Integer numOfSeats = form.getNumOfSeats();
		final List<OfflineRegistration> offlineRegs = offlineRegDao
				.findByProductAndUser(machineUuid, productCode, user);
		final Integer countOffReg = offlineRegs.size();
		LOGGER.debug("The count of Offline Registrations is: {}", countOffReg);
		if ((numOfSeats != null && numOfSeats > countOffReg)
				|| (countOffReg == 0)) {
			LOGGER.debug("Ready to Register for Machine {}", machineName);
			// if the machine UUID assigned to another user, will be removed
			// from the other user.
			final List<OfflineRegistration> offRegs = getByProductCode(
					productCode, machineUuid);
			LOGGER.debug("Registrations for removal {}", offRegs.size());
			removeRegistration(offRegs);
			// create the offline registration
			final OfflineRegistration offReg = new OfflineRegistration();
			offReg.setMachineName(machineName);
			offReg.setMachineUuid(machineUuid);
			offReg.setProduct(user.getProducts().iterator().next());
			offReg.setRegistrationUuid(regUuid);
			offReg.setOrganization(user.getOrganization());
			offReg.setUserAccount(user);
			offlineRegDao.createOfflineRegistration(offReg);
			validate = true;
		}
		return validate;
	}

	/**
	 * @param offlineRegs
	 * @return verified registrations
	 */
	public List<OfflineRegistration> verifyRegistration(
			final List<OfflineRegistration> pOfflineRegs) {
		final List<OfflineRegistration> nOfflineRegs = new ArrayList<OfflineRegistration>();
		if (pOfflineRegs != null && !pOfflineRegs.isEmpty()) {
			for (final OfflineRegistration offlineReg : pOfflineRegs) {
				final OfflineRegistration offReg = offlineRegDao
						.verifyRegistration(offlineReg);
				if (offReg != null) {
					nOfflineRegs.add(offReg);
				}

			}
		}
		return nOfflineRegs;
	}

	/**
	 * @param offlineReg
	 * @return verified OfflineRegistration
	 */
	public OfflineRegistration verifyRegistration(
			final OfflineRegistration offlineReg) {
		return offlineRegDao.verifyRegistration(offlineReg);
	}
}
