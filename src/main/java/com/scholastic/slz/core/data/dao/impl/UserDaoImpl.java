package com.scholastic.slz.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.scholastic.slz.core.data.dao.UserDao;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author vijay.abbigeri
 * 
 */
public class UserDaoImpl extends GenericDaoImpl<UserAccount> implements UserDao {

	/**
	 * Map to add parameters.
	 */
	private Map<String, Object> parameters;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount findUserByIdentifierAndType(final String uuid,
			final String userType) {
		UserAccount user = null;
		parameters = new HashMap<String, Object>();
		parameters.put("uuid", uuid);
		parameters.put("userType", userType);
		user = findSingleResultWithNamedQuery(
				UserAccount.FIND_BY_UUID_AND_TYPE, parameters);
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserAccount> findUsersByOrgAndGrade(final Organization org,
			final String gradeCode, final String appCode) {
		parameters = new HashMap<String, Object>();
		parameters.put("appCode", appCode);
		parameters.put("org", org);
		parameters.put("gradeCode", gradeCode);
		final List<UserAccount> users = findWithNamedQuery(
				UserAccount.FIND_BY_ORG_AND_GRADE, parameters);
		return users;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserAccount> findUsersByClassIdAndType(final Integer classId,
			final String userType, final String appCode) {
		parameters = new HashMap<String, Object>();
		parameters.put("classId", classId);
		parameters.put("userType", userType);
		parameters.put("appCode", appCode);
		return findWithNamedQuery(UserAccount.QUERY_FIND_BY_CLASS_ID_AND_TYPE,
				parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount getOrganizationAdminDetails(final String code) {
		UserAccount userAccount = null;

		final TypedQuery<UserAccount> queryAdminDetails = entityManager
				.createNamedQuery(UserAccount.FIND_ADMIN_DETAILS_BY_CODE,
						UserAccount.class).setParameter("identifier", code);
		userAccount = (UserAccount) queryAdminDetails.getSingleResult();

		return userAccount;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount getTeacherInfo(final String code) {

		UserAccount userAccount = null;

		final TypedQuery<UserAccount> queryAdminDetails = entityManager
				.createNamedQuery(UserAccount.FIND_TEACHER_DETAILS_BY_CODE,
						UserAccount.class).setParameter("identifier", code);

		userAccount = (UserAccount) queryAdminDetails.getSingleResult();

		return userAccount;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putLexile(final String code, final String lexileSource,
			final int lexileScore) {
		UserAccount userAccount = null;
		final TypedQuery<UserAccount> userDetails = entityManager
				.createNamedQuery(UserAccount.FIND_BY_CODE, UserAccount.class)
				.setParameter("identifier", code);

		userAccount = (UserAccount) userDetails.getSingleResult();

		if (userAccount != null) {
			try {
				transaction.begin();
				userAccount.setLexileSource(lexileSource);
				userAccount.setLexileLevel(lexileScore);
				// entityManager.persist(userAccount);
				entityManager.merge(userAccount);
				transaction.commit();
			} catch (SecurityException | IllegalStateException
					| RollbackException | HeuristicMixedException
					| HeuristicRollbackException | SystemException
					| NotSupportedException exception) {
				// TODO Auto-generated catch block

				try {
					transaction.rollback();

				} catch (IllegalStateException | SecurityException
						| SystemException exceptions) {
					// TODO Auto-generated catch block

				}

			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount findOrgGroupAdminById(final String identifier) {
		parameters = new HashMap<String, Object>();
		parameters.put("uuid", identifier);
		return findSingleResultWithNamedQuery(
				UserAccount.QUERY_FIND_ORG_GROUP_ADMIN_BY_ID, parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount getUserByCode(final String code) {
		parameters = new HashMap<String, Object>();
		parameters.put("identifier", code);
		return findSingleResultWithNamedQuery(UserAccount.FIND_BY_CODE,
				parameters);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserAccount> getStudentByClasses(final String userType,
			final String appCode, final String queryString,
			final Integer organizationID) {

		final TypedQuery<UserAccount> userDetails = entityManager
				.createNamedQuery(UserAccount.FIND_STUDENT_BY_CLASSES,
						UserAccount.class).setParameter("userType", userType)
				.setParameter("appCode", appCode)
				.setParameter("organizationID", organizationID)
				.setParameter("queryString", queryString);
		return userDetails.getResultList();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount findUserEntitlementByProduct(final String uuid,
			final String productCode) {
		parameters = new HashMap<String, Object>();
		parameters.put("uuid", uuid);
		parameters.put("productCode", productCode);
		return findSingleResultWithNamedQuery(
				UserAccount.FIND_USER_ENTITLEMENT, parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserAccount findStudentByOrgIdAndUsername(final Integer orgId,
			final String username) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("orgId", orgId);
		parameters.put("username", username);
		return findSingleResultWithNamedQuery(
				UserAccount.FIND_STUDENT_BY_ORG_ID_AND_USERNAME, parameters);
	}
}
