package com.scholastic.slz.core.services;

import java.util.List;

import javax.inject.Inject;

import com.scholastic.slz.core.data.dao.GradeSystemDao;
import com.scholastic.slz.core.data.model.Grade;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Techer details service class
 * 
 */
public class GradeSystemService {
	@Inject
	GradeSystemDao gradeSystemDao;

	/**
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<Grade> getGradeSystemDetails(String code){
		List<Grade> gradeSystemDetails = gradeSystemDao
				.getGradeSystemDetails(code);

		return gradeSystemDetails;
	}
}
