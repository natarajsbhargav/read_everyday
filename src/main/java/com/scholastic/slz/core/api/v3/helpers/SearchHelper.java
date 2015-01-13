package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.v3.vo.ClassVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationGroupVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationVO;
import com.scholastic.slz.core.api.v3.vo.SearchResultVO;
import com.scholastic.slz.core.api.v3.vo.UserVO;
import com.scholastic.slz.core.data.model.ClassInfo;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class SearchHelper {

	/** The logger for search helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchHelper.class);

	private SearchHelper() {
	}

	/**
	 * Adds organization details to the search results DTO
	 * 
	 * @param org
	 *            the organization
	 * @param searchResultVO
	 *            the search results DTO to which the organization details needs
	 *            to be added
	 */
	public static void addOrganizationDetails(final Organization org,
			final SearchResultVO searchResultVO) {
		final List<OrganizationVO> orgVOs = new ArrayList<OrganizationVO>();
		if (org != null) {
			LOGGER.debug(
					"Adding organization details for the organization: {}",
					org.getId());
			final OrganizationVO orgVO = getOrganizationVO(org);
			orgVOs.add(orgVO);
		}
		searchResultVO.setOrgs(orgVOs);
	}

	/**
	 * Adds User details to the search results DTO
	 * 
	 * @param org
	 *            the userAccount
	 * @param searchResultVO
	 *            the search results DTO to which the organization details needs
	 *            to be added
	 */
	public static void addStudentDetails(
			final List<UserAccount> userAccountList,
			final SearchResultVO searchResultVO) {
		final List<UserVO> students = new ArrayList<UserVO>();

		searchResultVO.setStudents(students);
	}

	/**
	 * Adds classes details to the search results DTO
	 * 
	 * @param org
	 *            the userAccount
	 * @param searchResultVO
	 *            the search results DTO to which the organization details needs
	 *            to be added
	 */
	public static void addAllClassDetails(final List<ClassInfo> classInfoList,
			final SearchResultVO searchResultVO) {
		List<ClassVO> classes = null;
		classes = ClassHelper.getBasicClassVOs(classInfoList);
		searchResultVO.setClasses(classes);
	}

	/**
	 * Adds a list of organizations to the search results DTO
	 * 
	 * @param orgs
	 *            the list of organizations
	 * @param searchResultVO
	 *            the search results DTO to which the organizations needs to be
	 *            added
	 */
	public static void addOrganizationDetails(final List<Organization> orgs,
			final SearchResultVO searchResultVO) {
		final List<OrganizationVO> orgVOs = new ArrayList<OrganizationVO>();
		if (orgs != null && !orgs.isEmpty()) {
			LOGGER.debug("Total number of organizations: {}", orgs.size());
			OrganizationVO orgVO;
			for (final Organization org : orgs) {
				LOGGER.debug(
						"Adding organization details for the organization: {}",
						org.getId());
				orgVO = getOrganizationVO(org);
				orgVOs.add(orgVO);
			}
		}
		searchResultVO.setOrgs(orgVOs);
	}

	/**
	 * Returns the organization DTO for a given organization
	 * 
	 * @param org
	 *            the organization for which the DTO has to be created
	 * @return DTO for the organization
	 */
	private static OrganizationVO getOrganizationVO(final Organization org) {
		OrganizationVO orgVO = null;
		if (org != null) {
			orgVO = OrganizationHelper.getBasicOrganizationVO(org);
			OrganizationHelper.addRegionDetails(orgVO, org.getRegion());
			OrganizationHelper.addCountryDetails(orgVO, org.getCountry());
			orgVO.setAdminAreaName(org.getAdminAreaName());
			orgVO.setLocalityName(org.getLocalityName());
		}
		return orgVO;
	}

	/**
	 * Adds the organization group details the search results DTO for a given
	 * organization
	 * 
	 * @param org
	 *            the organization
	 * @param searchResultVO
	 *            the search results DTO to which the organizations needs to be
	 *            added
	 */
	public static void addOrganizationGroupDetails(final Organization org,
			final SearchResultVO searchResultVO) {
		if (org == null) {
			searchResultVO.setOrgGroups(new ArrayList<OrganizationGroupVO>());
		} else {
			LOGGER.debug(
					"Adding organization group details for the organization: {}",
					org.getId());
			final Set<OrganizationGroup> orgGroups = org
					.getOrganizationGroups();
			addOrganizationGroupDetails(orgGroups, searchResultVO);
		}
	}

	/**
	 * Adds a collection of organization groups to the search results DTO
	 * 
	 * @param orgGroups
	 *            the collection of organization groups
	 * @param searchResultVO
	 *            the search results DTO to which the organizations needs to be
	 *            added
	 */
	public static void addOrganizationGroupDetails(
			final Collection<OrganizationGroup> orgGroups,
			final SearchResultVO searchResultVO) {
		final List<OrganizationGroupVO> orgGroupVOs = new ArrayList<OrganizationGroupVO>();
		if (orgGroups != null && !orgGroups.isEmpty()) {
			LOGGER.debug("Total number of organization groups: {}",
					orgGroups.size());
			OrganizationGroupVO orgGroupVO;
			for (final OrganizationGroup orgGroup : orgGroups) {
				LOGGER.debug("Adding details for the organization group: {}",
						orgGroup.getId());
				orgGroupVO = OrganizationGroupHelper
						.getBasicOrganizationGroupVO(orgGroup);
				orgGroupVOs.add(orgGroupVO);
			}
		}
		searchResultVO.setOrgGroups(orgGroupVOs);
	}

	public static void addAllStudentUsers(List<UserAccount> userAccountList,
			SearchResultVO searchResultVO) {

		List<UserVO> userVOs = null;
		userVOs = UserHelper.getBasicUserVOs(userAccountList);
		searchResultVO.setStudents(userVOs);

	}
}