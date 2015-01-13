package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.v3.vo.OrganizationGroupFullVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationGroupVO;
import com.scholastic.slz.core.api.v3.vo.OrganizationVO;
import com.scholastic.slz.core.data.model.Organization;
import com.scholastic.slz.core.data.model.OrganizationGroup;
import com.scholastic.slz.core.services.OrganizationGroupService;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class OrganizationGroupHelper {

	/** The logger for organization group helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrganizationGroupHelper.class);

	private OrganizationGroupHelper() {
	}

	/**
	 * Returns basic DTO for a given organization group with just the identifier
	 * and name
	 * 
	 * @param orgGroup
	 *            the organization group for which the DTO has to be created
	 * @return DTO for the organization group
	 */
	public static OrganizationGroupVO getBasicOrganizationGroupVO(
			final OrganizationGroup orgGroup) {
		OrganizationGroupVO orgGroupVO = null;
		if (orgGroup != null) {
			LOGGER.debug("Getting DTO for organization group: {}",
					orgGroup.getName());
			orgGroupVO = new OrganizationGroupVO();
			orgGroupVO.setIdentifier(orgGroup.getUuid());
			orgGroupVO.setName(orgGroup.getName());
			orgGroupVO.setTimestamp(null);
		}
		return orgGroupVO;
	}

	/**
	 * Returns DTO for the given organization group
	 * 
	 * @param organizationGroup
	 *            the organization group for which the DTO has to be created
	 * @return DTO for the organization group
	 */
	public static OrganizationGroupVO getOrganizationGroupVO(
			final OrganizationGroup organizationGroup) {
		OrganizationGroupVO orgGroupVO = null;
		if (organizationGroup != null) {
			LOGGER.debug("Getting DTO for organization group: {}",
					organizationGroup.getName());
			orgGroupVO = createOrganizationGroupVO(organizationGroup);
			addParentOrgGroupDetails(orgGroupVO,
					organizationGroup.getParentOrgGroup());
			addChildOrgGroups(orgGroupVO, organizationGroup.getChildOrgGroups());
			addChildOrgs(orgGroupVO, organizationGroup.getChildOrganizations());
		}
		return orgGroupVO;
	}

	/**
	 * Creates the organization group DTO object
	 * 
	 * @param organizationGroup
	 *            the organization group for which the DTO has to be created
	 * @return DTO for the organization group
	 */
	private static OrganizationGroupVO createOrganizationGroupVO(
			final OrganizationGroup organizationGroup) {
		final OrganizationGroupVO orgGroupVO = new OrganizationGroupVO();
		if (organizationGroup != null) {
			orgGroupVO.setIdentifier(organizationGroup.getUuid());
			orgGroupVO.setName(organizationGroup.getName());
		}
		return orgGroupVO;
	}

	/**
	 * Adds the parent organization group details to the organization group DTO
	 * 
	 * @param orgGroupVO
	 *            the organization group DTO to which the parent details needs
	 *            to be added
	 * @param parentOrgGroup
	 *            the parent organization group
	 */
	private static void addParentOrgGroupDetails(
			final OrganizationGroupVO orgGroupVO,
			final OrganizationGroup parentOrgGroup) {
		if (orgGroupVO != null) {
			LOGGER.debug(
					"Setting parent organization group details for org group: {}",
					orgGroupVO.getName());
			final OrganizationGroupVO parentOrgGroupVO = createOrganizationGroupVO(parentOrgGroup);
			parentOrgGroupVO.setTimestamp(null);
			orgGroupVO.setParentOrgGroup(parentOrgGroupVO);
		}
	}

	/**
	 * Adds the child organization groups to the organization group DTO
	 * 
	 * @param orgGroupVO
	 *            the organization group DTO to which the child organization
	 *            groups needs to be added
	 * @param childOrgGroups
	 *            list of child organization groups
	 */
	private static void addChildOrgGroups(final OrganizationGroupVO orgGroupVO,
			final Set<OrganizationGroup> childOrgGroups) {
		if (orgGroupVO != null && childOrgGroups != null) {
			LOGGER.debug("Setting child org group details for org group: {}",
					orgGroupVO.getName());
			LOGGER.debug("Number of child org groups: {}",
					childOrgGroups.size());
			final List<OrganizationGroupVO> childOrgGroupVOs = new ArrayList<OrganizationGroupVO>();
			OrganizationGroupVO childOrgGroupVO;
			for (final OrganizationGroup childOrgGroup : childOrgGroups) {
				childOrgGroupVO = createOrganizationGroupVO(childOrgGroup);
				childOrgGroupVO.setTimestamp(null);
				childOrgGroupVOs.add(childOrgGroupVO);
			}
			orgGroupVO.setChildOrgGroups(childOrgGroupVOs);
		}
	}

	/**
	 * Adds the child organizations to the organization group DTO
	 * 
	 * @param orgGroupVO
	 *            the organization group DTO to which the child organizations
	 *            needs to be added
	 * @param childOrgs
	 *            list of child organizations
	 */
	private static void addChildOrgs(final OrganizationGroupVO orgGroupVO,
			final Set<Organization> childOrgs) {
		if (orgGroupVO != null && childOrgs != null) {
			LOGGER.debug("Setting child org details for org group: {}",
					orgGroupVO.getName());
			final List<OrganizationVO> childOrgVOs = new ArrayList<OrganizationVO>();
			LOGGER.debug("Number of child orgs: {}", childOrgs.size());
			OrganizationVO childOrgVO;
			for (final Organization childOrg : childOrgs) {
				childOrgVO = OrganizationHelper
						.getBasicOrganizationVO(childOrg);
				childOrgVOs.add(childOrgVO);
			}
			orgGroupVO.setChildOrgs(childOrgVOs);
		}
	}

	/**
	 * Returns DTO for the given organization group with complete hierarchy and
	 * additional details like country code, organization count
	 * 
	 * @param organizationGroup
	 *            the organization group for which the DTO has to be created
	 * @param orgGroupService
	 *            the organization group service
	 * @param isRoot
	 *            flag indicating if this organizationGroup is a root group
	 * @return DTO for the organization group
	 */
	public static OrganizationGroupVO getOrganizationGroupFullVO(
			final OrganizationGroup organizationGroup,
			final OrganizationGroupService orgGroupService, final boolean isRoot) {
		OrganizationGroupVO orgGroupVO = null;
		if (organizationGroup != null) {
			LOGGER.debug("Getting DTO for organization group: {}",
					organizationGroup.getName());
			orgGroupVO = createOrgGroupFullVO(organizationGroup, isRoot);
			final List<OrganizationGroupVO> childOrgGroupVOs = new ArrayList<OrganizationGroupVO>();
			if (organizationGroup.getChildOrgGroups() != null) {
				OrganizationGroupVO childOrgGroupVO;
				for (final OrganizationGroup childOrgGroup : organizationGroup
						.getChildOrgGroups()) {
					final OrganizationGroup childGroup = orgGroupService
							.getOrganizationGroupFullByUuid(childOrgGroup
									.getUuid());
					if (childGroup != null) {
						childOrgGroupVO = getOrganizationGroupFullVO(
								childGroup, orgGroupService, false);
						childOrgGroupVOs.add(childOrgGroupVO);
					}
				}
			}
			orgGroupVO.setChildOrgGroups(childOrgGroupVOs);
		}
		return orgGroupVO;
	}

	/**
	 * Creates the organization group full DTO object
	 * 
	 * @param organizationGroup
	 *            the organization group for which the DTO has to be created
	 * @param isRoot
	 *            flag indicating if this organizationGroup is a root group
	 * @return DTO for the organization group
	 */
	private static OrganizationGroupVO createOrgGroupFullVO(
			final OrganizationGroup organizationGroup, final boolean isRoot) {
		OrganizationGroupVO orgGroupVO = null;
		if (organizationGroup != null) {
			LOGGER.debug("Creating DTO for the organization group: {}",
					organizationGroup.getName());
			orgGroupVO = new OrganizationGroupFullVO();
			orgGroupVO.setIdentifier(organizationGroup.getUuid());
			orgGroupVO.setName(organizationGroup.getName());
			if (organizationGroup.getCountry() != null) {
				orgGroupVO.setCountryCode(organizationGroup.getCountry()
						.getCode());
			}
			if (organizationGroup.getChildOrganizations() != null) {
				orgGroupVO.setOrgCount(organizationGroup
						.getChildOrganizations().size());
			}
			if (isRoot) {
				LOGGER.debug("Root organiztion group, adding parent organization group details");
				addParentOrgGroupDetails(orgGroupVO,
						organizationGroup.getParentOrgGroup());
			} else {
				orgGroupVO.setTimestamp(null);
			}
		}
		return orgGroupVO;
	}

}