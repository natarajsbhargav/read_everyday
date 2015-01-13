package com.scholastic.slz.core.api.v3.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.v3.vo.BasicVO;
import com.scholastic.slz.core.api.v3.vo.CountryVO;
import com.scholastic.slz.core.api.v3.vo.RegionListResultVO;
import com.scholastic.slz.core.api.v3.vo.RegionResultVO;
import com.scholastic.slz.core.data.model.Country;
import com.scholastic.slz.core.data.model.Region;

/**
 * @author snehalata.raulo
 * 
 */
public final class RegionHelper {

	/** The logger for organization helper */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegionHelper.class);

	private RegionHelper() {
	}

	/**
	 * Returns RegionListResultVO DTO for the given list of region
	 * 
	 * @param regions
	 * @return DTO for the RegionListResultVO
	 */
	public static RegionListResultVO getRegionListVO(final List<Region> regions) {
		final RegionListResultVO regionResultVO = new RegionListResultVO();
		if (regions != null && !regions.isEmpty()) {
			final List<BasicVO> regionVOs = new ArrayList<BasicVO>();
			LOGGER.debug("Region size : {}", regions.size());
			BasicVO regionVO = null;
			for (final Region region : regions) {
				LOGGER.debug("Region Name : {}", region.getName());
				regionVO = new BasicVO();
				final String regionId = region.getId().toString();
				regionVO.setIdentifier(regionId);
				regionVO.setName(region.getName());
				regionVOs.add(regionVO);
			}
			regionResultVO.setRegions(regionVOs);
		}
		return regionResultVO;
	}

	/**
	 * Returns RegionResultVO DTO for the given region
	 * 
	 * @param region
	 * @return DTO for the RegionResultVO
	 */
	public static RegionResultVO getRegionResultVO(final Region region) {
		LOGGER.debug("Get RegionResultVO for the region : {}", region.getName());
		final RegionResultVO regionResultVO = new RegionResultVO();
		regionResultVO.setIdentifier(region.getId());
		regionResultVO.setName(region.getName());
		regionResultVO.setCountries(getCountryVO(region));
		return regionResultVO;
	}

	/**
	 * Returns list of CountryVO DTO for the given region
	 * 
	 * @param region
	 * @return DTO for the list of CountryVO
	 */
	private static List<CountryVO> getCountryVO(final Region region) {
		final Set<Country> countries = region.getCountries();
		final List<CountryVO> countryVOs = new ArrayList<CountryVO>();
		if (countries != null && !countries.isEmpty()) {
			LOGGER.debug("Get CountryVO for the country size : {}",
					countries.size());
			for (final Country country : countries) {
				final CountryVO countryVO = new CountryVO();
				countryVO.setCode(country.getCode());
				countryVO.setName(country.getName());
				countryVOs.add(countryVO);
			}
		}
		return countryVOs;
	}
}
