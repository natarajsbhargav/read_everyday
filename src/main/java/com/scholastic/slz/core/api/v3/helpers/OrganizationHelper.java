package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.v3.vo.BasicVO;
import com.scholastic.slz.core.api.v3.vo.GradeSystemVO;
import com.scholastic.slz.core.api.v3.vo.LocaleVO;
import com.scholastic.slz.core.api.v3.vo.MarkingPeriodVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationResultVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationVO;
import com.scholastic.slz.core.api.v3.vo.SchoolCalendarVO;
import com.scholastic.slz.core.api.v3.vo.TimezoneVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Country;
import com.scholastic.slz.core.data.model.GradeSystem;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.data.model.Region;
import com.scholastic.slz.core.data.model.SchoolCalendar;
import com.scholastic.slz.core.data.model.SchoolMarkingPeriod;
import com.scholastic.slz.core.data.model.TimeZone;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class OrganizationHelper {

	/** The logger for organization helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationHelper.class);

	private OrganizationHelper() {
	}

	/**
	 * Returns DTO for the given organization
	 * 
	 * @param organization
	 *            the organization for which the DTO has to be created
	 * @return DTO for the organization
	 */
	public static OrganizationVO getOrganizationVO(
			final Organization organization) {
		OrganizationVO organizationVO = null;
		if (organization != null) {
			LOGGER.debug("Getting DTO for organization: {}",
					organization.getName());
			organizationVO = new OrganizationVO();
			organizationVO
					.setIdentifier(getOrganizationIdentifier(organization));
			organizationVO.setName(organization.getName());
			organizationVO.setTestAccount(organization.isTestOrg());
		}
		return organizationVO;
	}

	/**
	 * Returns basic DTO for the given organization with just the identifier and
	 * name
	 * 
	 * @param organization
	 *            the organization for which the DTO has to be created
	 * @return DTO for the organization
	 */
	public static OrganizationVO getBasicOrganizationVO(
			final Organization organization) {
		OrganizationVO organizationVO = null;
		if (organization != null) {
			LOGGER.debug("Getting DTO for organization: {}",
					organization.getName());
			organizationVO = new OrganizationVO();
			organizationVO
					.setIdentifier(getOrganizationIdentifier(organization));
			organizationVO.setName(organization.getName());
			organizationVO.setTimestamp(null);
		}
		return organizationVO;
	}

	/**
	 * Returns the identifier for an organization concatenating the
	 * organizations GUID and UUID
	 * 
	 * @param org
	 *            the organization
	 * @return the organization identifier
	 */
	public static String getOrganizationIdentifier(final Organization org) {
		String orgId = null;
		if (org != null && org.getOrgGuid() != null && org.getOrgUuid() != null) {
			orgId = org.getOrgGuid() + LegacyApiConstants.COLON
					+ org.getOrgUuid();
		}
		return orgId;
	}

	/**
	 * Returns the OrganizationResultVO DTO for given organization and
	 * identifier organizations GUID and UUID
	 * 
	 * @param org
	 *            the organization
	 * @return the organization identifier
	 */
	public static OrganizationResultVO getOrganizationreseltVO(
			final String identifier, final Organization org) {
		LOGGER.debug("Getting OrganizationResultVO for organization: {}",
				org.getName());
		final OrganizationResultVO orgResultVO = new OrganizationResultVO();
		orgResultVO.setIdentifier(identifier);
		orgResultVO.setName(org.getName());
		orgResultVO.setTestAccount(org.isTestOrg());
		orgResultVO.setLocale(getLocaleVO(org));
		orgResultVO.setTimezone(getTimeZoneVO(org));
		orgResultVO.setGradeSystem(getGradeSystemVO(org));
		orgResultVO.setSchoolCalendar(getSchoolCalendarVO(org));
		orgResultVO.setSchoolGroups(getOrgGroups(org));
		orgResultVO.setClasses(getClasses(org));
		return orgResultVO;
	}

	/**
	 * Returns the Class DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the list of BasicVO
	 */
	private static List<BasicVO> getClasses(final Organization org) {
		final Set<ClassInfo> classInfos = org.getClasses();
		final List<BasicVO> classVos = new ArrayList<BasicVO>();
		if (classInfos != null && !classInfos.isEmpty()) {
			LOGGER.debug("The Class size is  {}", classInfos.size());
			for (final ClassInfo classInfo : classInfos) {
				final BasicVO classVO = new BasicVO();
				classVO.setIdentifier(classInfo.getUuid());
				classVO.setName(classInfo.getName());
				classVos.add(classVO);
			}
		}
		return classVos;
	}

	/**
	 * Returns the OrganizationGroup DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the list of BasicVO
	 */
	private static List<BasicVO> getOrgGroups(final Organization org) {
		final Set<OrganizationGroup> orgGroups = org.getOrganizationGroups();
		final List<BasicVO> schoolGroups = new ArrayList<BasicVO>();
		if (orgGroups != null && !orgGroups.isEmpty()) {
			LOGGER.debug("The Organization Group size is {} ", orgGroups.size());
			for (final OrganizationGroup orgGroup : orgGroups) {
				final BasicVO schoolGroup = new BasicVO();
				schoolGroup.setName(orgGroup.getName());
				schoolGroup.setIdentifier(orgGroup.getUuid());
				schoolGroups.add(schoolGroup);
			}
		}
		return schoolGroups;
	}

	/**
	 * Returns the list of MarkingPeriodVO DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the list of MarkingPeriodVO
	 */
	private static List<MarkingPeriodVO> getMarkingPeriods(
			final SchoolCalendar schoolCalendar) {
		final Set<SchoolMarkingPeriod> markingPeriods = schoolCalendar
				.getSchoolMarkingPeriods();
		final List<MarkingPeriodVO> markingPeriodVos = new ArrayList<MarkingPeriodVO>();
		if (markingPeriods != null && !markingPeriods.isEmpty()) {
			LOGGER.debug("The Marking period size is  {}",
					markingPeriods.size());
			for (final SchoolMarkingPeriod smp : markingPeriods) {
				final MarkingPeriodVO markingPeriodVo = new MarkingPeriodVO();
				markingPeriodVo.setDescription(smp.getDescription());
				markingPeriodVo.setStartDate(smp.getStartDate());
				markingPeriodVo.setEndDate(smp.getEndDate());
				markingPeriodVos.add(markingPeriodVo);
			}
		}
		return markingPeriodVos;
	}

	/**
	 * Returns the SchoolCalendarVO DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the SchoolCalendarVO
	 */
	private static SchoolCalendarVO getSchoolCalendarVO(final Organization org) {
		final Set<SchoolCalendar> schoolCalendars = org.getSchoolCalendars();
		final SchoolCalendarVO schoolCalendarVO = new SchoolCalendarVO();
		if (schoolCalendars != null && !schoolCalendars.isEmpty()) {
			final Iterator<SchoolCalendar> iter = schoolCalendars.iterator();
			final SchoolCalendar schoolCalendar = iter.next();
			LOGGER.debug("The School Year description is {} ",
					schoolCalendar.getDescription());
			if (schoolCalendar.getCurrentCalendar()) {
				schoolCalendarVO
						.setMarkingPeriods(getMarkingPeriods(schoolCalendar));
				schoolCalendarVO
						.setDescription(schoolCalendar.getDescription());
				schoolCalendarVO.setStartDate(schoolCalendar.getStartDate());
				schoolCalendarVO.setEndDate(schoolCalendar.getEndDate());
			}
		}
		return schoolCalendarVO;
	}

	/**
	 * Returns the GradeSystemVO DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the GradeSystemVO
	 */
	private static GradeSystemVO getGradeSystemVO(final Organization org) {
		final GradeSystemVO gradeSystemVO = new GradeSystemVO();
		final GradeSystem gradeSystem = org.getGradeSystem();
		if (gradeSystem != null) {
			LOGGER.debug("The grade system Name is: {} ", gradeSystem.getName());
			gradeSystemVO.setCode(gradeSystem.getCode());
			gradeSystemVO.setName(gradeSystem.getName());
		}
		return gradeSystemVO;
	}

	/**
	 * Returns the TimezoneVO DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the TimezoneVO
	 */
	private static TimezoneVO getTimeZoneVO(final Organization org) {
		final TimeZone timezone = org.getTimeZone();
		final TimezoneVO TimezoneVO = new TimezoneVO();
		if (timezone != null) {
			LOGGER.debug("The timezone Canonical Name is: {} ",
					timezone.getCanonicalName());
			TimezoneVO.setCanonicalName(timezone.getCanonicalName());
			TimezoneVO.setDescription(timezone.getDescription());
		}
		return TimezoneVO;
	}

	/**
	 * Returns the LocaleVO DTO for given organization
	 * 
	 * @param org
	 *            the organization
	 * @return the LocaleVO
	 */
	private static LocaleVO getLocaleVO(final Organization org) {
		final LocaleVO localeVO = new LocaleVO();
		final Region region = org.getRegion();
		if (region != null) {
			LOGGER.debug("Getting region for LocaleVO --- region Name: {}",
					org.getName());
			localeVO.setRegionIdentifier(region.getId());
			localeVO.setRegionName(region.getName());
		}
		final Country country = org.getCountry();
		if (country != null) {
			LOGGER.debug("Getting Country for LocaleVO --- country Name: {}",
					country.getName());
			localeVO.setCountryCode(country.getCode());
			localeVO.setCountryName(country.getName());
		}
		return localeVO;
	}

	/**
	 * Adds the region details to a given organization DTO
	 * 
	 * @param orgVO
	 *            the organization DTO to which the region details needs to be
	 *            added
	 * @param region
	 *            the region
	 */
	public static void addRegionDetails(final OrganizationVO orgVO,
			final Region region) {
		if (region != null) {
			final String regionId = region.getId() == null ? null : region
					.getId().toString();
			orgVO.setRegionIdentifier(regionId);
			orgVO.setRegionName(region.getName());
		}
	}

	/**
	 * Adds the country details to a given organization DTO
	 * 
	 * @param orgVO
	 *            the organization DTO to which the country details needs to be
	 *            added
	 * @param country
	 *            the country
	 */
	public static void addCountryDetails(final OrganizationVO orgVO,
			final Country country) {
		if (country != null) {
			orgVO.setCountryCode(country.getCode());
			orgVO.setCountryName(country.getName());
		}
	}
}
