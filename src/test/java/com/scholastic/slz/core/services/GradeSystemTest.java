package com.scholastic.slz.core.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.GradeSystemDao;
import com.scholastic.slz.core.data.model.Grade;
import com.scholastic.slz.core.data.model.GradeSystem;
import com.scholastic.slz.core.data.model.SriGrade;

/**
 * 
 * @author Madan D H
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class GradeSystemTest {

	/** the GradeSystemDao DAO */
	@Mock
	private transient GradeSystemDao gradeSystemDao;

	/** the GradeSystemService service */
	@InjectMocks
	private final GradeSystemService gradeSystemService = new GradeSystemService();

	/**
	 * Tests that the service returns a list of GradeSystem
	 */

	/**
	 * Tests that the service returns the GradeSystem by using country code
	 */
	@Test
	public void testGradeSystem() {
		final String code = "TUN";
		final List<Grade> gradeList = getGradeList(code);
		Mockito.when(gradeSystemDao.getGradeSystemDetails(code)).thenReturn(
				gradeList);

		final List<Grade> schoolCalendar = gradeSystemService
				.getGradeSystemDetails(code);

		assertNotNull(schoolCalendar);
	}

	/**
	 * @param code
	 * @return the Mock object for the Grade System
	 */
	private List<Grade> getGradeList(final String code) {
		final List<Grade> gradeList = new ArrayList<Grade>();
		final GradeSystem gradeSystem = new GradeSystem();
		gradeSystem.setCode("TUN");
		gradeSystem.setName("Tunisia");
		Grade grade = new Grade();
		grade.setFullName("Pre-Kindergarten");
		grade.setShortName("PK");
		grade.setSequenceNum(1);
		SriGrade sriGrade = new SriGrade();
		sriGrade.setFullName("Pre-K");
		sriGrade.setShortName("Pre-Kindergarten");
		grade.setSriGrade(sriGrade);
		gradeList.add(grade);

		grade = new Grade();
		grade.setFullName("Kindergarten");
		grade.setShortName("K");
		grade.setSequenceNum(2);
		sriGrade = new SriGrade();
		sriGrade.setFullName("1");
		sriGrade.setShortName("Kindergarten");
		grade.setSriGrade(sriGrade);
		gradeList.add(grade);

		grade = new Grade();
		grade.setFullName("1");
		grade.setShortName("First grade");
		grade.setSequenceNum(3);
		sriGrade = new SriGrade();
		sriGrade.setFullName("1");
		sriGrade.setShortName("Grade 1");
		grade.setSriGrade(sriGrade);
		gradeList.add(grade);

		grade = new Grade();
		grade.setFullName("Second grade");
		grade.setShortName("2");
		grade.setSequenceNum(4);
		sriGrade = new SriGrade();
		sriGrade.setFullName("Grade 2");
		sriGrade.setShortName("2");
		grade.setSriGrade(sriGrade);
		gradeList.add(grade);

		grade = new Grade();
		grade.setFullName("Third Grade");
		grade.setShortName("3");
		grade.setSequenceNum(5);
		sriGrade = new SriGrade();
		sriGrade.setFullName("Grade 3");
		sriGrade.setShortName("3");
		grade.setSriGrade(sriGrade);
		gradeList.add(grade);
		return gradeList;
	}
}
