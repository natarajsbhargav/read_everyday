package com.scholastic.slz.core.api.v3.resources;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scholastic.slz.core.api.LegacyApiConstants;
import com.scholastic.slz.core.api.exception.ApplicationException;
import com.scholastic.slz.core.api.exception.ESlzApiErrorReason;
import com.scholastic.slz.core.api.v3.vo.GradeSystemResultVO;
import com.scholastic.slz.core.api.v3.vo.GradeVO;
import com.scholastic.slz.core.data.model.Grade;
import com.scholastic.slz.core.data.model.GradeSystem;
import com.scholastic.slz.core.services.GradeSystemService;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * This is for getting Grade System details from Resteasy service
 * 
 */
@Path(LegacyApiConstants.GRADE_SYSTEM_PATH)
public class GradeSystemResource {
	/**
	 * LOGGER field for debugging
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GradeSystemResource.class);

	/**
	 * Injecting the Grade System which consist of Implementing method service
	 * layer Object
	 */
	@Inject
	private transient GradeSystemService gradeSystemService;
	/**
	 * Date Formatting for response object timestamp
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.H");
	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getGradeSystemDetails(
			@PathParam("identifier") final String code)
			{
		Response response = null;
		List<Grade> gradeResult = null;
		final GradeSystemResultVO result = new GradeSystemResultVO();
		final List<GradeVO> gradeList = new ArrayList<GradeVO>();
		if (code != null && !code.isEmpty()) {
			
				LOGGER.debug("Grade System country code: {}", code);
				gradeResult = gradeSystemService.getGradeSystemDetails(code);
			
			if (gradeResult != null && gradeResult.size() > 0) {
				if (gradeResult.get(0).getGradeSystem() != null) {
					final GradeSystem gradeSystem = gradeResult.get(0)
							.getGradeSystem();
					result.setCode(gradeSystem.getCode());
					result.setName(gradeSystem.getName());
					result.setTimestamp(dateFormat.format(new Date()));
				}

				for (final Grade grade : gradeResult) {
					GradeVO gradeVO = new GradeVO();
					gradeVO.setLocalGradeName(grade.getFullName());
					gradeVO.setLocalGradeCode(grade.getShortName());
					gradeVO.setScholasticGradeCode(grade.getSriGrade()
							.getShortName());
					gradeVO.setScholasticGradeName(grade.getSriGrade()
							.getFullName());
					gradeVO.setSequenceNum(grade.getSequenceNum().toString());
					gradeList.add(gradeVO);
				}
				result.setGrades(gradeList);
				final ResponseBuilder respbuilder = Response.ok(result);
				response = respbuilder.build();
				return response;
			} else {
				throw new ApplicationException(Response.Status.NOT_FOUND,
						ESlzApiErrorReason.SERVER_ERROR_GENERIC,
						"Grade System is not available for the code " + code);
			}
		} else {
			throw new ApplicationException(Response.Status.NOT_FOUND,
					ESlzApiErrorReason.CLIENT_ERROR_GENERIC,
					"Code for the Grade System cannot be null, Please enter the valid Country code");
		}

	}
}
