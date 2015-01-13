package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.v3.vo.BasicVO;
import com.scholastic.slz.core.api.v3.vo.StudentGradeVO;
import com.scholastic.slz.core.api.v3.vo.StudentResultVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Grade;
import com.scholastic.slz.core.data.model.GradeSystem;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.SriGrade;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
public final class StudentHelper {

	/** The logger for organization helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StudentHelper.class);

	private StudentHelper() {
	}

	/**
	 * Returns DTO for the given users
	 * 
	 * @param List
	 *            of users the users for which the DTO has to be created
	 * @return DTO for the OrganizationGradeResultVO
	 */
	public static StudentResultVO getStudentResultVO(final UserAccount student) {
		LOGGER.debug("Getting StudentResultVO for student: {}",
				student.getUsername());
		final StudentResultVO studentResultVO = new StudentResultVO();
		studentResultVO.setIdentifier(student.getUuid());
		studentResultVO.setFirstName(student.getFirstName());
		studentResultVO.setLastName(student.getLastName());
		studentResultVO.setLexileScore(student.getLexileLevel());
		studentResultVO.setSchool(getSchoolVO(student));
		studentResultVO.setGrade(getStudentGradeVO(student));
		studentResultVO.setClasses(getStudentClassesVO(student));
		return studentResultVO;
	}

	/**
	 * Returns DTO for a given student along with the enrolled status for a
	 * given application code
	 * 
	 * @param student
	 *            the student for which the DTO has to be created
	 * @param appCode
	 *            the application code
	 * @return the student DTO
	 */
	public static StudentResultVO getStudentResultVO(final UserAccount student,
			final String appCode) {
		final StudentResultVO studentResultVO = getStudentResultVO(student);
		studentResultVO.setLexileScore(null);
		studentResultVO.setEnrolled(UserHelper.isEnrolled(student, appCode));
		return studentResultVO;
	}

	/**
	 * Returns StudentGradeVO DTO for the given users
	 * 
	 * @param user
	 * @return DTO for the StudentGradeVO
	 */
	private static List<BasicVO> getStudentClassesVO(final UserAccount student) {
		final List<BasicVO> basicVos = new ArrayList<BasicVO>();
		final Set<ClassInfo> classes = student.getClasses();
		if (classes != null && !classes.isEmpty()) {
			LOGGER.debug("Class size is: {}", classes.size());
			for (final ClassInfo cls : student.getClasses()) {
				final BasicVO basicVo = new BasicVO();
				basicVo.setIdentifier(cls.getUuid());
				basicVo.setName(cls.getName());
				basicVos.add(basicVo);
			}
		}
		return basicVos;
	}

	/**
	 * Returns StudentGradeVO DTO for the given users
	 * 
	 * @param user
	 * @return DTO for the StudentGradeVO
	 */
	private static StudentGradeVO getStudentGradeVO(final UserAccount student) {
		final StudentGradeVO studentGradeVO = new StudentGradeVO();
		final Grade grade = student.getGrade();
		if (grade != null) {
			LOGGER.debug("Getting StudentGradeVO for grade: {}",
					grade.getFullName());
			studentGradeVO.setLocalGradeCode(grade.getFullName());
			studentGradeVO.setLocalGradeName(grade.getImportCode());
			studentGradeVO.setSequenceNum(grade.getSequenceNum());
			final GradeSystem gradeSystem = grade.getGradeSystem();
			if (gradeSystem != null) {
				studentGradeVO.setLocalGradeSystemCode(gradeSystem.getCode());
			}
			final SriGrade sriGrade = grade.getSriGrade();
			if (sriGrade != null) {
				studentGradeVO.setScholasticGradeCode(sriGrade.getShortName());
				studentGradeVO.setScholasticGradeName(sriGrade.getFullName());
			}
		}
		return studentGradeVO;
	}

	/**
	 * Returns School DTO for the given users
	 * 
	 * @param user
	 * @return DTO for the Organization
	 */
	private static BasicVO getSchoolVO(final UserAccount student) {
		final Organization org = student.getOrganization();
		final BasicVO schoolBasicVo = new BasicVO();
		if (org != null) {
			LOGGER.debug("Getting SchoolVO for Organization: {}", org.getName());
			final String orgIdentifier = org.getOrgGuid()
					+ LegacyApiConstants.COLON + org.getOrgUuid();
			schoolBasicVo.setIdentifier(orgIdentifier);
			schoolBasicVo.setName(org.getName());
		}
		return schoolBasicVo;
	}
}
