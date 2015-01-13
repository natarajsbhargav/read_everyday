package com.scholastic.slz.core.data.dao;

import java.sql.SQLException;
import java.util.List;

import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public interface UserDao extends GenericDao<UserAccount> {

	/**
	 * Returns the user for a given UUID and user type
	 * 
	 * @param identifier
	 *            the UUID of the user to be returned
	 * @param userType
	 *            the type of the user to be returned
	 * @return the user
	 */
	UserAccount findUserByIdentifierAndType(String identifier, String userType);

	/**
	 * Returns the list of users for a given organization and grade
	 * 
	 * @param appCode
	 *            the application code
	 * @param org
	 *            the organization for which the users has to be returned
	 * @param gradeCode
	 *            the grade code
	 * @return the list of users
	 */
	List<UserAccount> findUsersByOrgAndGrade(Organization org,
			String gradeCode, String appCode);

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
	List<UserAccount> findUsersByClassIdAndType(Integer classId,
			String userType, String appCode);

	/**
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	UserAccount getOrganizationAdminDetails(String code);

	/**
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	UserAccount getTeacherInfo(String code);

	/**
	 * @param code
	 * @param lexileSource
	 * @param lexileScore
	 * @throws SQLException
	 * @throws Exception
	 */

	void putLexile(String code, String lexileSource, int lexileScore);

	/**
	 * Returns the organization group administrative user for a given identifier
	 * 
	 * @param orgGroupId
	 *            the user identifier (UUID)
	 * @return the organization group administrator
	 */
	UserAccount findOrgGroupAdminById(String identifier);

	/**
	 * Returns the user for a given identifier
	 * 
	 * @param uuid
	 *            the user identifier (UUID)
	 * @return the User
	 */
	UserAccount getUserByCode(String code);

	/**
	 * Returns the list of users for a given code and user type
	 * 
	 * @param userType
	 *            the type of users to be returned
	 * @param appCode
	 *            the application code
	 * @return the list of users
	 */
	List<UserAccount> getStudentByClasses(String userType, String appCode,
			String queryString, Integer organizationID);

	/**
	 * Returns the user for a given user uuid and productCOde
	 * 
	 * @param uuid
	 *            the user uuid
	 * @param productCode
	 *            the product code
	 * @return the list of users
	 */
	UserAccount findUserEntitlementByProduct(String uuid, String productCode);
	
	/**
	 * Returns a student for a given organization id and user name
	 * 
	 * @param orgId
	 *            the organization id
	 * @param username
	 *            the user name of the student
	 * @return the user
	 */
	UserAccount findStudentByOrgIdAndUsername(Integer orgId, String username);
}
