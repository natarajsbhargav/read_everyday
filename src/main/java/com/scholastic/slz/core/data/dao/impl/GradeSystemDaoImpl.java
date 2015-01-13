package com.scholastic.slz.core.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.scholastic.slz.core.data.dao.GradeSystemDao;
import com.scholastic.slz.core.data.model.Grade;

/* @author madan.haraginadoni
 *
 */
/**
 * GradeSystemDaoImpl is DAO class implementer for Grade System service
 * 
 */
public class GradeSystemDaoImpl extends GenericDaoImpl<Grade> implements
		GradeSystemDao {

	public GradeSystemDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.scholastic.slz.core.data.dao.GradeSystemDao#getGradeSystemDetails
	 * (java.lang.String)
	 */
	@Override
	public List<Grade> getGradeSystemDetails(String code) {
		List<Grade> gradeSystemList = null;

		final TypedQuery<Grade> queryGradeSystemDetails = entityManager
				.createNamedQuery(Grade.FIND_BY_GRADE_SYSTEM_ID, Grade.class)
				.setParameter("identifier", code);

		gradeSystemList = (List<Grade>) queryGradeSystemDetails.getResultList();

		return gradeSystemList;

	}

}
