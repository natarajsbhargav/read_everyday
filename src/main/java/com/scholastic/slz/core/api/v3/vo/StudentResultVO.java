package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class StudentResultVO extends ResultVO {

	/** The identifier */
	@XmlAttribute
	@JsonProperty("identifier")
	private String identifier;

	/** The firstName */
	@XmlAttribute
	@JsonProperty("firstName")
	private String firstName;

	/** The lastName */
	@XmlAttribute
	@JsonProperty("lastName")
	private String lastName;

	/** the lexile score */
	@XmlElement
	private Integer lexileScore;

	/** The enrolled */
	@XmlAttribute
	@JsonProperty("enrolled")
	private Boolean enrolled;

	/** The school */
	@XmlElement
	@JsonProperty("school")
	private BasicVO school;

	/** The grade */
	@XmlElement
	@JsonProperty("grade")
	private StudentGradeVO grade = new StudentGradeVO();

	/** The classes */
	@XmlElement
	@JsonProperty("classes")
	private List<BasicVO> classes = new ArrayList<BasicVO>();

	/**
	 * 
	 * @return The identifier
	 */
	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * 
	 * @param identifier
	 *            The identifier
	 */
	@JsonProperty("identifier")
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The firstName
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The firstName
	 */
	@JsonProperty("firstName")
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return The lastName
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The lastName
	 */
	@JsonProperty("lastName")
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the lexile score of this student
	 * 
	 * @return the lexile score
	 */
	public Integer getLexileScore() {
		return lexileScore;
	}

	/**
	 * Sets the lexile score for this student
	 * 
	 * @param lexileScore
	 *            the lexile score to be set
	 */
	public void setLexileScore(Integer lexileScore) {
		this.lexileScore = lexileScore;
	}

	/**
	 * 
	 * @return The school
	 */
	@JsonProperty("school")
	public BasicVO getSchool() {
		return school;
	}

	/**
	 * 
	 * @param school
	 *            The school
	 */
	@JsonProperty("school")
	public void setSchool(final BasicVO school) {
		this.school = school;
	}

	/**
	 * 
	 * @return The grade
	 */
	@JsonProperty("grade")
	public StudentGradeVO getGrade() {
		return grade;
	}

	/**
	 * 
	 * @param grade
	 *            The grade
	 */
	@JsonProperty("grade")
	public void setGrade(final StudentGradeVO grade) {
		this.grade = grade;
	}

	/**
	 * 
	 * @return The classes
	 */
	@JsonProperty("classes")
	public List<BasicVO> getClasses() {
		return classes;
	}

	/**
	 * 
	 * @param classes
	 *            The classes
	 */
	@JsonProperty("classes")
	public void setClasses(final List<BasicVO> classes) {
		this.classes = classes;
	}

	/**
	 * @return the enrolled
	 */
	public Boolean isEnrolled() {
		return enrolled;
	}

	/**
	 * @param enrolled
	 *            the enrolled to set
	 */
	public void setEnrolled(final Boolean enrolled) {
		this.enrolled = enrolled;
	}

}