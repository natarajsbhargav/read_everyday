/**
 * 
 */
package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.scholastic.slz.core.api.v3.vo.VerifyMachineResultVO;
import com.scholastic.slz.core.api.v3.vo.VerifyRegistrationVO;
import com.scholastic.slz.core.data.model.OrderEntitlement;

/**
 * @author snehalata.raulo
 * 
 */
public final class OfflineRegistrationHelper {

	private OfflineRegistrationHelper() {
	}

	/**
	 * Returns DTO for the given list of Order Entitlement
	 * 
	 * @param orderEntitlements
	 *            the list of Order for which the DTO has to be created
	 * @return DTO for the verified registrations
	 */
	public static VerifyMachineResultVO getVerifyRegistrationResultVO(
			final List<OrderEntitlement> orderEntitlements) {
		final VerifyMachineResultVO verifyRegResultVO = new VerifyMachineResultVO();
		final List<VerifyRegistrationVO> verifyRegVos = new ArrayList<VerifyRegistrationVO>();
		if (orderEntitlements != null && !orderEntitlements.isEmpty()) {
			VerifyRegistrationVO verifyRegVO = null;
			for (final OrderEntitlement orderEntitlement : orderEntitlements) {
				verifyRegVO = new VerifyRegistrationVO();
				if (orderEntitlement.getProduct() != null) {
					verifyRegVO.setProductCode(orderEntitlement.getProduct()
							.getCode());
				}
				verifyRegVO.setOfflineNumDays(orderEntitlement.getNumOfDays());
				Date orderExpiryDate = orderEntitlement.getGracePeriodEndDate();
				if (orderExpiryDate == null) {
					orderExpiryDate = orderEntitlement.getSubscriptionEndDate();
				}
				verifyRegVO.setOrderExpiryDate(orderExpiryDate);
				verifyRegVos.add(verifyRegVO);
			}
			verifyRegResultVO.setProducts(verifyRegVos);
		}
		return verifyRegResultVO;
	}

	/**
	 * @param orderEntitlement
	 * @return DTO for the verified registration
	 */
	public static VerifyRegistrationVO getVerifyRegistrationResultVO(
			final OrderEntitlement orderEntitlement) {
		final VerifyRegistrationVO verifyRegVO = new VerifyRegistrationVO();
		if (orderEntitlement != null) {
			if (orderEntitlement.getProduct() != null) {
				verifyRegVO.setProductCode(orderEntitlement.getProduct().getCode());
			}
			verifyRegVO.setOfflineNumDays(orderEntitlement.getNumOfDays());
			Date orderExpiryDate = orderEntitlement.getGracePeriodEndDate();
			if (orderExpiryDate == null) {
				orderExpiryDate = orderEntitlement.getSubscriptionEndDate();
			}
			verifyRegVO.setOrderExpiryDate(orderExpiryDate);
		}
		return verifyRegVO;
	}
}
