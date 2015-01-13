package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.UserDao;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author vijay.abbigeri
 * 
 */
public class UserService {

	/**
	 * Injected UserDao to get user details.
	 */
	@Inject
	private transient UserDao userDao;

	/**
	 * Returns the user for a given identifier and user type
	 * 
	 * @param identifier
	 *            the user identifier for which the users has to be returned
	 * @param userType
	 *            the type of users to be returned
	 * @return the list of users
	 */
	public UserAccount getUserByIdentifier(final String identifier,
			final String userType) {
		return userDao.findUserByIdentifierAndType(identifier, userType);
	}

	/**
	 * Returns the list of users for a given Organization and scholastic grade
	 * code
	 * 
	 * @param org
	 *            the Organization for which the users has to be returned
	 * @param gradeCode
	 *            the scholastic grade code of users to be returned
	 * @param appCode
	 *            the application code
	 * @return the list of users
	 */
	public List<UserAccount> getUsersByOrgAndGrade(final Organization org,
			final String gradeCode, final String appCode) {
		return userDao.findUsersByOrgAndGrade(org, gradeCode, appCode);
	}

	/**
	 * Returns the list of users for a given class and user type
	 * 
	 * @param classId
	 *            the class identifier for which the users has to be returned
	 * @param userType
	 *            the type of users to be returned
	 * @param appCode
	 *            the application code
	 * @return the list of users
	 */
	public List<UserAccount> getUsersByClassIdAndType(final Integer classId,
			final String userType, final String appCode) {
		return userDao.findUsersByClassIdAndType(classId, userType, appCode);
	}

	/**
	 * Returns the student (user) for a given orgId and user name
	 * 
	 * @param orgId
	 *            the organization identifier
	 * @param username
	 *            user name of the student
	 * @return the user
	 */
	public UserAccount getStudentByOrgIdAndUsername(final Integer orgId,
			final String username) {
		return userDao.findStudentByOrgIdAndUsername(orgId, username);
	}

	/**
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public UserAccount getAdminDetails(final String code) {
		final UserAccount userAccount = userDao
				.getOrganizationAdminDetails(code);

		return userAccount;
	}

	/**
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public UserAccount getTeacherDetails(final String code) {
		return userDao.getTeacherInfo(code);
	}

	/**
	 * 
	 * @param code
	 * @param lexileSource
	 * @param lexileScore
	 */
	public void putStudentLexile(final String code, final String lexileSource,
			final int lexileScore) {
		userDao.putLexile(code, lexileSource, lexileScore);
	}

	/**
	 * Returns the organization group administrative user for a given identifier
	 * 
	 * @param identifier
	 *            the user identifier (UUID)
	 * @return the organization group administrator
	 */
	public UserAccount getOrgGroupAdminById(final String identifier) {
		return userDao.findOrgGroupAdminById(identifier);
	}

	/**
	 * Returns the user for a given identifier
	 * 
	 * @param identifier
	 *            the user identifier (UUID)
	 * @return the UserAccount
	 */
	public UserAccount getUserByIdentifier(final String identifier) {
		return userDao.getUserByCode(identifier);
	}

	/**
	 * Returns the list of users for a given appCode and user type
	 * 
	 * @param userType
	 *            the type of users to be returned
	 * @param queryString
	 *            the type of users to be returned by matching queryString
	 * 
	 * @param appCode
	 *            the application code
	 * @return the list of users
	 */
	public List<UserAccount> getStudentByClasses(final String userType,
			final String appCode, final String queryString,
			final Integer organizationID) {
		return userDao.getStudentByClasses(userType, appCode, queryString,
				organizationID);
	}

	/**
	 * 
	 * @param uuid
	 * @param productCode
	 * @return the UserAccount
	 */
	public UserAccount getUserEntitlementByProduct(final String uuid,
			final String productCode) {
		return userDao.findUserEntitlementByProduct(uuid, productCode);
	}
}
