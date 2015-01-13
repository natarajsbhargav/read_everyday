package com.scholastic.slz.core.data.dao;

import java.sql.SQLException;
import java.util.List;

import com.scholastic.slz.core.data.model.SchoolCalendar;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * DAO class for the School calender details , implentaiton is the DaoImpl class
 * 
 */
public interface SchoolCalenderDao {
	/**
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	List<SchoolCalendar> getSchoolCalender(String guid, String uuid);
}
