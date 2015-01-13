package com.scholastic.slz.core.services;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.data.dao.SchoolCalenderDao;
import com.scholastic.slz.core.data.model.SchoolCalendar;
import com.scholastic.slz.core.data.model.SchoolMarkingPeriod;
import com.scholastic.slz.core.services.SchoolCalenderService;
/**
 * 
 * @author Madan D H
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SchoolCalendarTest {
	
	/** the SchoolCalenderService DAO */
	@Mock
	private transient SchoolCalenderDao schoolCalendarDAO;

	/** the SchoolCalenderService service */
	@InjectMocks
	final private SchoolCalenderService schoolCalenderService = new SchoolCalenderService();

	/**
	 * Tests that the service returns a list of SchoolCalender
	 */
	/**
	 * Tests that the service returns the School Calendar GUID:UUID
	 */
	
	@Test
	public void testSchoolCalendarByOrgCode() {
		final List<SchoolCalendar> schoolCalendarList = getSchoolCalendarList("088c9417413b487e773ca3bdec192cde", "3b5272ff-1648-48b3-8708-a5946b183244");
		Mockito.when(
				schoolCalendarDAO.getSchoolCalender("088c9417413b487e773ca3bdec192cde", "3b5272ff-1648-48b3-8708-a5946b183244"))
				.thenReturn(schoolCalendarList);

		final List<SchoolCalendar> schoolCalendar = schoolCalenderService.getCalenderDetails("088c9417413b487e773ca3bdec192cde", "3b5272ff-1648-48b3-8708-a5946b183244");
				

		assertNotNull(schoolCalendar);
	}
	
	private List<SchoolCalendar> getSchoolCalendarList(String guid, String uuid){
		final List<SchoolCalendar> schoolCalendarList = new ArrayList<SchoolCalendar>();
		SchoolCalendar schoolCalendar = new SchoolCalendar();
		schoolCalendar.setStartDate(Timestamp.valueOf("2013-01-01 00:00:00.0"));
		schoolCalendar.setEndDate(Timestamp.valueOf("2013-12-31 00:00:00.0"));
		schoolCalendar.setDescription("school year1");
		List<SchoolMarkingPeriod> schoolMarkingPeriods = new ArrayList<SchoolMarkingPeriod>();
		SchoolMarkingPeriod schoolMarking = new SchoolMarkingPeriod();
		schoolMarking.setStartDate(Timestamp.valueOf("2013-02-02 00:00:00.0"));
		schoolMarking.setEndDate(Timestamp.valueOf("2013-03-02 00:00:00.0"));
		schoolMarking.setDescription("marking periods2");
		schoolMarkingPeriods.add(schoolMarking);
		
		schoolCalendar = new SchoolCalendar();
		schoolCalendar.setStartDate(Timestamp.valueOf("2013-01-01 00:00:00.0"));
		schoolCalendar.setEndDate(Timestamp.valueOf("2013-12-31 00:00:00.0"));
		schoolCalendar.setDescription("school year2");
		schoolMarkingPeriods = new ArrayList<SchoolMarkingPeriod>();
		schoolMarking = new SchoolMarkingPeriod();
		schoolMarking.setStartDate(Timestamp.valueOf("2013-01-01 00:00:00.0"));
		schoolMarking.setEndDate(Timestamp.valueOf("2013-02-01 00:00:00.0"));
		schoolMarking.setDescription("marking periods1");
		schoolMarkingPeriods.add(schoolMarking);
		schoolMarking = new SchoolMarkingPeriod();
		schoolMarking.setStartDate(Timestamp.valueOf("2013-03-03 00:00:00.0"));
		schoolMarking.setEndDate(Timestamp.valueOf("2013-04-03 00:00:00.0"));
		schoolMarking.setDescription("marking periods3");
		schoolMarkingPeriods.add(schoolMarking);
		
		return schoolCalendarList;
	}
}
