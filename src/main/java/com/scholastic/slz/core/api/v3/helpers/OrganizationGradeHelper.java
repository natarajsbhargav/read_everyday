/**
 * 
 */
package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.v3.vo.OrganizationGradeResultVO;
import com.scholastic.slz.core.api.v3.vo.UserVO;
import com.scholastic.slz.core.data.model.Product;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
public final class OrganizationGradeHelper {

	/** The logger for organization helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationGradeHelper.class);

	private OrganizationGradeHelper() {
	}

	/**
	 * Returns DTO for the given users
	 * 
	 * @param List
	 *            of users the users for which the DTO has to be created
	 * @return DTO for the OrganizationGradeResultVO
	 */
	public static OrganizationGradeResultVO getOrgGradeResultVO(
			final List<UserAccount> users) {
		final OrganizationGradeResultVO orgGradeResultVO = new OrganizationGradeResultVO();
		LOGGER.debug("The user size is  {}", users.size());
		final List<UserVO> students = new ArrayList<UserVO>();
		UserVO userVO = null;
		for (final UserAccount user : users) {
			userVO = getUserVO(user);
			students.add(userVO);
		}
		orgGradeResultVO.setStudents(students);

		return orgGradeResultVO;
	}

	/**
	 * Returns UserVO object
	 * 
	 * @param user
	 * 
	 */
	private static UserVO getUserVO(final UserAccount user) {
		LOGGER.debug("The user name is  {}", user.getUsername());
		final UserVO userVO = new UserVO();
		userVO.setTimestamp(null);
		userVO.setIdentifier(user.getUuid());
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		final Set<Product> products = user.getProducts();
		final boolean isEnrolled = (products != null && products.size() == LegacyApiConstants.ID_SIZE) ? true
				: false;
		userVO.setEnrolled(isEnrolled);
		return userVO;
	}
}
