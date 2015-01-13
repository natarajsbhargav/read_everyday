package com.scholastic.slz.core.data.dao;

import java.sql.SQLException;
import java.util.List;

import com.scholastic.slz.core.data.model.Grade;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Is the DAO interface for GradeSystem service
 * 
 */
public interface GradeSystemDao {
	/**
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	List<Grade> getGradeSystemDetails(String code);
}
