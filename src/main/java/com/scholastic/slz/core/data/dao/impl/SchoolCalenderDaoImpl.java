package com.scholastic.slz.core.data.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.scholastic.slz.core.data.dao.SchoolCalenderDao;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.SchoolCalendar;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Implementing the DAO class for school calender
 * 
 */
public class SchoolCalenderDaoImpl extends GenericDaoImpl<SchoolCalendar>
		implements SchoolCalenderDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.scholastic.intl.core.data.dao.SchoolCalenderDao#getSchoolCalender
	 * (java.lang.String)
	 */
	@Override
	public List<SchoolCalendar> getSchoolCalender(final String orgGuid,
			final String orgUuid) {
		TypedQuery<SchoolCalendar> querySchoolCalendar = null;
		List<SchoolCalendar> schoolCalenderList = null;
		Organization org = null;
		final TypedQuery<Organization> organizationDetails = entityManager
				.createNamedQuery(Organization.FIND_BY_IDENTIFIERS,
						Organization.class).setParameter("orgGuid", orgGuid)
				.setParameter("orgUuid", orgUuid);

		org = (Organization) organizationDetails.getSingleResult();

		if (org != null) {

			querySchoolCalendar = entityManager.createNamedQuery(
					SchoolCalendar.FIND_SCHOOL_CALENDAR_DEATILS,
					SchoolCalendar.class).setParameter("identifier",
					org.getId());
			schoolCalenderList = (List<SchoolCalendar>) querySchoolCalendar
					.getResultList();
		}

		if (schoolCalenderList != null) {

			return schoolCalenderList;

		}
		return null;

	}

	public SchoolCalenderDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
