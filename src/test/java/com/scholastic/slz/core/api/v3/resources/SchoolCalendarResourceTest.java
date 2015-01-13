package com.scholastic.slz.core.api.v3.resources;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.data.model.SchoolCalendar;
import com.scholastic.slz.core.data.model.SchoolMarkingPeriod;
import com.scholastic.slz.core.services.SchoolCalenderService;

/**
 * Test class for school calendar resource
 * 
 * @author shailu.chougale
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SchoolCalendarResourceTest {

	/** the school calendar service */
	@Mock
	private transient SchoolCalenderService schoolCalenderService;

	/** the school calendar resource */
	@InjectMocks
	private transient final SchoolCalenderResource schoolCalenderResource = new SchoolCalenderResource();

	/** the organization ID */
	public static final String ORGID = "088c9417413b487e773ca3bdec192cde:3b5272ff-1648-48b3-8708-a5946b183244";

	/** the organization GUID */
	public static final String ORG_GUID = "088c9417413b487e773ca3bdec192cde";

	/** the organization UUID */
	public static final String ORG_UUID = "3b5272ff-1648-48b3-8708-a5946b183244";

	/**
	 * tests {@link SchoolCalenderResource#getSchoolCalendarDetails(String)} for
	 * success
	 * 
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@Test
	public void testGetSchoolCalendarDetailsSuccess()
			throws ApplicationException, SQLException {
		final SchoolMarkingPeriod schoolMarkingPeriod = new SchoolMarkingPeriod();
		schoolMarkingPeriod.setDescription("marking description");
		schoolMarkingPeriod.setStartDate(Timestamp
				.valueOf("2013-01-01 00:00:00.0"));
		schoolMarkingPeriod.setEndDate(Timestamp
				.valueOf("2013-12-31 00:00:00.0"));

		final Set<SchoolMarkingPeriod> schoolMarkingPeriods = new HashSet<SchoolMarkingPeriod>();
		schoolMarkingPeriods.add(schoolMarkingPeriod);

		final SchoolCalendar schoolCalendar = new SchoolCalendar();
		schoolCalendar.setDescription("test calender");
		schoolCalendar.setStartDate(new Date());
		schoolCalendar.setEndDate(new Date());
		schoolCalendar.setSchoolMarkingPeriods(schoolMarkingPeriods);

		final List<SchoolCalendar> calendarList = new ArrayList<SchoolCalendar>();
		calendarList.add(schoolCalendar);

		Mockito.when(
				schoolCalenderService.getCalenderDetails(ORG_GUID, ORG_UUID))
				.thenReturn(calendarList);
		final Response response = schoolCalenderResource
				.getSchoolCalendarDetails(ORGID);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * tests {@link SchoolCalenderResource#getSchoolCalendarDetails(String)}
	 * when organization Id is null
	 * 
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetSchoolCalendarDetailsOrgIdNull()
			throws ApplicationException, SQLException {
		final Response response = schoolCalenderResource
				.getSchoolCalendarDetails(null);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * tests {@link SchoolCalenderResource#getSchoolCalendarDetails(String)}
	 * with no calendar exists
	 * 
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetSchoolCalendarDetailsCalendarNotFound()
			throws ApplicationException, SQLException {
		Mockito.when(
				schoolCalenderService.getCalenderDetails(ORG_GUID, ORG_UUID))
				.thenReturn(null);
		final Response response = schoolCalenderResource
				.getSchoolCalendarDetails(ORGID);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	/**
	 * tests {@link SchoolCalenderResource#getSchoolCalendarDetails(String)} for
	 * Exception
	 * 
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@Test(expected = ApplicationException.class)
	public void testGetSchoolCalendarDetailsForException()
			throws ApplicationException, SQLException {
		Mockito.doThrow(new ApplicationException()).when(schoolCalenderService)
				.getCalenderDetails(ORG_GUID, ORG_UUID);
		final Response response = schoolCalenderResource
				.getSchoolCalendarDetails(ORGID);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

}