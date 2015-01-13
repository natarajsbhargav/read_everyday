package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.v3.vo.ClassVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationVO;
import com.scholastic.slz.core.api.v3.vo.UserVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class ClassHelper {

	/** The logger for class helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClassHelper.class);

	private ClassHelper() {
	}

	/**
	 * Returns DTO for the given class
	 * 
	 * @param classInfo
	 *            the class for which the DTO has to be created
	 * @param students
	 *            the list of students to be added
	 * @param teachers
	 *            the list of teachers to be added
	 * @return DTO for the class
	 */
	public static ClassVO getClassVO(final ClassInfo classInfo,
			final List<UserAccount> students, final List<UserAccount> teachers) {
		ClassVO classVO = null;
		if (classInfo != null) {
			LOGGER.debug("Getting DTO for class: {}", classInfo.getName());
			classVO = new ClassVO();
			classVO.setIdentifier(OrganizationHelper
					.getOrganizationIdentifier(classInfo.getOrganization())
					+ LegacyApiConstants.COLON + classInfo.getUuid());
			classVO.setName(classInfo.getName());
			addOrganizationDetails(classVO, classInfo.getOrganization());
			addUserDetails(classVO, students, teachers);
		}
		return classVO;
	}

	/**
	 * Adds the organization details to the class DTO
	 * 
	 * @param classVO
	 *            the class DTO to which the organization details will be added
	 * @param org
	 *            the organization
	 */
	private static void addOrganizationDetails(final ClassVO classVO,
			final Organization org) {
		LOGGER.debug("Setting organization details for the class: {}",
				classVO.getName());
		if (org != null) {
			LOGGER.debug("Organization id: {}, Organization name: {}",
					org.getId(), org.getName());
			final OrganizationVO organizationVO = OrganizationHelper
					.getBasicOrganizationVO(org);
			classVO.setOrganization(organizationVO);
		}
	}

	/**
	 * Adds the user details to the class DTO
	 * 
	 * @param classVO
	 *            the class DTO to which the user details will be added
	 * @param students
	 *            the list of students to be added
	 * @param teachers
	 *            the list of teachers to be added
	 */
	private static void addUserDetails(final ClassVO classVO,
			final List<UserAccount> students, final List<UserAccount> teachers) {
		LOGGER.debug("Setting students and teachers for the class: {}",
				classVO.getName());
		classVO.setStudents(getUserVOs(students));
		classVO.setTeachers(getUserVOs(teachers));
	}

	/**
	 * Returns the list of user DTOs for the given list of users
	 * 
	 * @param users
	 *            the list of users for which the DTOs has to be created
	 * @return the list of DTOs for the users
	 */
	private static List<UserVO> getUserVOs(final List<UserAccount> users) {
		final List<UserVO> userVOs = new ArrayList<UserVO>();
		if (users != null && !users.isEmpty()) {
			UserVO userVO;
			for (final UserAccount user : users) {
				userVO = new UserVO();
				userVO.setTimestamp(null);
				userVO.setIdentifier(user.getUuid());
				userVO.setFirstName(user.getFirstName());
				userVO.setLastName(user.getLastName());
				final boolean isEnrolled = user.getProducts() != null && user
						.getProducts().size() == 1 ? true : false;
				userVO.setEnrolled(isEnrolled);
				userVOs.add(userVO);
			}
		}
		return userVOs;
	}
	
	
	/**
	 * @param classInfoList
	 * @return classesVO for the student
	 */
	public static List<ClassVO> getBasicClassVOs(final List<ClassInfo> classInfoList){
		final List<ClassVO> classVOs = new ArrayList<ClassVO>();
		if(classInfoList != null && classInfoList.size() > 0){
			ClassVO classVO;
			for(final ClassInfo classInfo :classInfoList){
				classVO = new ClassVO();
				classVO.setIdentifier(classInfo.getUuid());
				classVO.setName(classInfo.getName());
				classVOs.add(classVO);
			}
		}
		
		return classVOs;
		
	}
}