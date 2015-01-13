package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.v3.vo.OrganizationGroupVO;
import com.scholastic.slz.core.api.v3.vo.UserVO;
import com.scholastic.slz.core.data.model.Application;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.data.model.Product;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class UserHelper {

	/** The logger for user helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserHelper.class);

	private UserHelper() {
	}

	/**
	 * Returns DTO for the given organization group administrator
	 * 
	 * @param user
	 *            the organization group administrator
	 * @return DTO for the organization group administrator
	 */
	public static UserVO getOrgGroupAdminVO(final UserAccount user) {
		UserVO userVO = null;
		if (user != null) {
			LOGGER.debug("Getting DTO for the organization group admin: {}",
					user.getUuid());
			userVO = createUserVO(user);
			addOrgGroupDetails(userVO, user.getOrganizationGroup());
		}
		return userVO;
	}

	/**
	 * Creates the user DTO object
	 * 
	 * @param user
	 *            the user for which the DTO has to be created
	 * @return DTO for the user
	 */
	private static UserVO createUserVO(final UserAccount user) {
		final UserVO userVO = new UserVO();
		if (user != null) {
			userVO.setIdentifier(user.getUuid());
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
		}
		return userVO;
	}

	/**
	 * Adds the organization group details to the user DTO
	 * 
	 * @param userVO
	 *            the user DTO to which the organization group details needs to
	 *            be added
	 * @param orgGroup
	 *            the organization group
	 */
	private static void addOrgGroupDetails(final UserVO userVO,
			final OrganizationGroup orgGroup) {
		LOGGER.debug(
				"Adding organization group details for the group admin: {}",
				userVO.getIdentifier());
		final OrganizationGroupVO orgGroupVO = OrganizationGroupHelper
				.getBasicOrganizationGroupVO(orgGroup);
		userVO.setOrgGroup(orgGroupVO);
	}

	/**
	 * @param userAccountList
	 * @return list of users vo on recieving the UserAccount entity mapper
	 */
	public static List<UserVO> getBasicUserVOs(
			final List<UserAccount> userAccountList) {
		final List<UserVO> userVOList = new ArrayList<UserVO>();
		if (userAccountList != null && userAccountList.size() > 0) {
			UserVO user;
			for (final UserAccount userAccount : userAccountList) {
				user = new UserVO();
				user.setFirstName(userAccount.getFirstName());
				user.setLastName(userAccount.getLastName());
				user.setUsername(userAccount.getUsername());
				final boolean isEnrolled = userAccount.getProducts() != null
						&& userAccount.getProducts().size() == 1 ? true : false;
				user.setEnrolled(isEnrolled);
				if (userAccount.getGrade() != null
						&& userAccount.getGrade().getSriGrade() != null
						&& userAccount.getGrade().getSriGrade().getShortName() != null) {
					user.setScholasticGradeCode(userAccount.getGrade()
							.getSriGrade().getShortName());
				}
				user.setIdentifier(userAccount.getUuid());
				userVOList.add(user);
			}
		}

		return userVOList;
	}

	/**
	 * Returns a flag indicating if a given user has enrolled to a product with
	 * the given application code
	 * 
	 * @param user
	 *            the user
	 * @param appCode
	 *            the application code
	 * @return true if this user has enrolled to a product with the given
	 *         application code; false otherwise
	 */
	public static boolean isEnrolled(final UserAccount user,
			final String appCode) {
		boolean isEnrolled = false;
		if (user != null && user.getProducts() != null) {
			for (final Product product : user.getProducts()) {
				final Application app = product.getApplication();
				if (app != null && app.getCode() != null
						&& app.getCode().equalsIgnoreCase(appCode)) {
					isEnrolled = true;
					break;
				}
			}
		}
		LOGGER.debug("Enrolled flag: {}", isEnrolled);
		return isEnrolled;
	}
}