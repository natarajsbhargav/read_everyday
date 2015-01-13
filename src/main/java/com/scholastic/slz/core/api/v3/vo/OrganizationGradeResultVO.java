package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class OrganizationGradeResultVO extends ResultVO {

	/** The students attribute */
	@XmlElement
	private List<UserVO> students = new ArrayList<UserVO>();

	/**
	 * @return the students
	 */
	public List<UserVO> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setStudents(final List<UserVO> students) {
		this.students = students;
	}

}