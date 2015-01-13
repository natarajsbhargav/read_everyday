package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.Grade;
import com.scholastic.slz.core.data.model.GradeSystem;
import com.scholastic.slz.core.data.model.SriGrade;
import com.scholastic.slz.core.services.GradeSystemService;
/**
 * Test class for Admin Details resource
 * 
 * @author Madan D H
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class GradeSystemResourceTest {
	
	/**
	 * Injecting the Grade System which consist of Implementing method service
	 * layer Object
	 */
	@Mock
	private transient GradeSystemService gradeSystemService;
	
	/** the Teacher details resource */
	@InjectMocks
	private final transient GradeSystemResource gradeSystemResource = new GradeSystemResource();
	
	/** the country code */
	public static final String COUNTRY_CODE = "TUN";
	
	/**
	 * Tests
	 * {@link AdminDetailsResource#getAdminDetails(String)} for
	 * successful scenario
	 * @throws SQLException 
	 * @throws ApplicationException 
	 */
	@Test
	public void testGradeSystemDetails(){
		List<Grade> gradeResult = new ArrayList<Grade>();
		Grade grade = new Grade();
		GradeSystem gradeSystem = new GradeSystem();
		grade.setFullName("Grade 10");
		grade.setShortName("10");
		grade.setSequenceNum(10);
		gradeResult.add(grade);
		gradeSystem.setCode("TUN");
		gradeSystem.setName("Tunisia");
		SriGrade sriGrade = new SriGrade();
		sriGrade.setShortName("11");
		sriGrade.setFullName("Eleventh grade");
		grade.setGradeSystem(gradeSystem);
		grade.setSriGrade(sriGrade);
		gradeResult.add(grade);
		Mockito.when(
				gradeSystemService.getGradeSystemDetails(COUNTRY_CODE)).thenReturn(gradeResult);
		Response response = gradeSystemResource.getGradeSystemDetails(COUNTRY_CODE);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
	}
	
	/**
	 * Tests
	 * {@link GradeSystemDetailsResource#getGradeSystemDetails(String)} for
	 * Grade with the given country code doesn't exists
	 * @throws SQLException 
	 * @throws ApplicationException 
	 */
	@Test(expected = ApplicationException.class)
	public void testGetGradeDetailsWhenCodeNotFound() throws ApplicationException, SQLException{
		List<Grade> gradeResult = 	gradeSystemService.getGradeSystemDetails(null);
						gradeSystemResource.getGradeSystemDetails("ABC");
						assertNull(gradeResult);			
						
	}
	
	/**
	 * Tests
	 * {@link GradeSystemDetailsResource#getGradeSystemDetails(String)} for
	 * Grade with the given country code is null
	 * @throws SQLException 
	 * @throws ApplicationException 
	 */
	@Test(expected = ApplicationException.class)
	public void testGetGradeDetailsWhenCodeNull() throws ApplicationException, SQLException{
		List<Grade> gradeResult = 	gradeSystemService.getGradeSystemDetails(null);
						gradeSystemResource.getGradeSystemDetails(null);
						assertNull(gradeResult);			
						
	}

}
