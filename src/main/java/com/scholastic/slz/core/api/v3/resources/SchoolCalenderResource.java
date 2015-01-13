package com.scholastic.slz.core.api.v3.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.vo.MarkingPeriods;
import com.scholastic.slz.core.api.v3.vo.SchoolCalendarsResultVO;
import com.scholastic.slz.core.api.v3.vo.SchoolCalenderResponseVO;
import com.scholastic.slz.core.data.model.SchoolCalendar;
import com.scholastic.slz.core.data.model.SchoolMarkingPeriod;
import com.scholastic.slz.core.services.SchoolCalenderService;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * This class is the rest service for the School calender details
 * 
 */
@Path(LegacyApiConstants.SCHOOL_CALENDAR_PATH)
public class SchoolCalenderResource {
	/**
	 * Logger used for debug messages at runtime
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SchoolCalenderResource.class);

	/**
	 * Injecting the calender serice object
	 */
	@Inject
	private transient SchoolCalenderService calenderService;

	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSchoolCalendarDetails(@PathParam("identifier") final String code)
			throws ApplicationException, SQLException {
		Response response = null;
		List<SchoolCalendar> calendarList = null;
		final List<SchoolCalenderResponseVO> schoolCalendarResponse = new ArrayList<SchoolCalenderResponseVO>();
		if (code != null && !code.isEmpty()) {
			try {
				LOGGER.info("Organization identifier  : {}", code);
				String []orgID = code.split(LegacyApiConstants.COLON);
				if(orgID[0] !=null && orgID[1] != null){
					calendarList = calenderService.getCalenderDetails(orgID[0],orgID[1]);
				}
			} catch (Exception appExp) {
				LOGGER.debug(appExp.getMessage());
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.SCHOOL_NOT_FOUND,
						"Could not find a school calendars with the school identifier "
								+ code);
			}
			if (calendarList != null && calendarList.size() > 0) {
				for (final SchoolCalendar calendar : calendarList) {

					final SchoolCalenderResponseVO calenderVO = new SchoolCalenderResponseVO();
					calenderVO.setStartDate(calendar.getStartDate().toString().split(" ")[0]);
					calenderVO.setEndDate(calendar.getEndDate().toString().split(" ")[0]);
					calenderVO.setDescription(calendar.getDescription());

					final ArrayList<MarkingPeriods> markingPeriodList = new ArrayList<MarkingPeriods>();
					calenderVO.setMarkingPeriods(markingPeriodList);

					final Set<SchoolMarkingPeriod> schoolMarkingList = calendar
							.getSchoolMarkingPeriods();
					if (schoolMarkingList != null && schoolMarkingList.size() > 0) {
						for (final SchoolMarkingPeriod markingVO : schoolMarkingList) {
							final MarkingPeriods periods = new MarkingPeriods();
							periods.setEndDate(markingVO.getEndDate().toString().split(" ")[0]);
							periods.setStartDate(markingVO.getStartDate()
									.toString().split(" ")[0]);
							periods.setDescription(markingVO.getDescription());
							if (calenderVO.getMarkingPeriods() != null
									&& (!calenderVO.getMarkingPeriods().contains(
											periods))) {
								markingPeriodList.add(periods);

							}
							calenderVO.setMarkingPeriods(markingPeriodList);
						}
					}

					schoolCalendarResponse.add(calenderVO);

				}
				final SchoolCalendarsResultVO result = new SchoolCalendarsResultVO();
				result.setSchoolCalendars(schoolCalendarResponse);
				final ResponseBuilder respbuilder = Response.ok(result);
				response = respbuilder.build();
				return response;
			} else {
				LOGGER.debug("Organization identifier  is not found");
				throw new ApplicationException(Status.NOT_FOUND,
						ESlzApiErrorReason.SCHOOL_NOT_FOUND,
						"Could not find a school calendars with the school identifier "
								+ code);
			}
		} else {
			LOGGER.debug("Organization identifier  is empty please provide : ");
			throw new ApplicationException(Status.NOT_FOUND,
					ESlzApiErrorReason.SCHOOL_NOT_FOUND,
					"Could not find a school calendars with the school identifier "
							+ code);
		}

	}

}
