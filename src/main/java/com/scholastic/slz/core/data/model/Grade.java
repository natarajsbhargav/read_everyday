package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the grades database table.
 * 
 */
@Entity
@Table(name = "grades")
@NamedQueries({ @NamedQuery(name = "Grade.findByGradeSystemId", query = "SELECT g FROM Grade g join fetch g.sriGrade join fetch g.gradeSystem where g.gradeSystem.code =:identifier and g.deleted='N' and g.sriGrade.deleted='N' and g.gradeSystem.deleted='N'") })
public class Grade extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_GRADE_SYSTEM_ID = "Grade.findByGradeSystemId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "import_code")
	private String importCode;

	@Column(name = "sequence_num")
	private Integer sequenceNum;

	@Column(name = "short_name")
	private String shortName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_system_id")
	private GradeSystem gradeSystem;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "sri_grade_id")
	private SriGrade sriGrade;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "grade")
	private Set<UserAccount> userAccounts;

	public Grade() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImportCode() {
		return this.importCode;
	}

	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}

	public Integer getSequenceNum() {
		return this.sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public GradeSystem getGradeSystem() {
		return this.gradeSystem;
	}

	public void setGradeSystem(GradeSystem gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	public SriGrade getSriGrade() {
		return this.sriGrade;
	}

	public void setSriGrade(SriGrade sriGrade) {
		this.sriGrade = sriGrade;
	}

	public Set<UserAccount> getUserAccounts() {
		return this.userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

}