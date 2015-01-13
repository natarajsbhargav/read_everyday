package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.SchoolCalenderDao;
import com.scholastic.slz.core.data.model.SchoolCalendar;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * School calender service class
 * 
 */
public class SchoolCalenderService {

	public SchoolCalenderService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Injecting the SchoolCalender DAO
	 */
	@Inject
	SchoolCalenderDao schoolDAO;

	/**
	 * @param code
	 * @return
	 * @throws Exception
	 */

	public List<SchoolCalendar> getCalenderDetails(final String orgGuid,
			final String orgUuid){

		final List<SchoolCalendar> calendarList = schoolDAO.getSchoolCalender(
				orgGuid, orgUuid);

		return calendarList;

	}

}
