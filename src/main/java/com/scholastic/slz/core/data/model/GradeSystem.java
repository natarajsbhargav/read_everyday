package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the grade_systems database table.
 * 
 */
@Entity
@Table(name = "grade_systems")
@NamedQueries({
		@NamedQuery(name = "GradeSystem.findAll", query = "SELECT g FROM GradeSystem g"),
		@NamedQuery(name = "GradeSystem.findGradeSystemByCode", query = "SELECT g FROM GradeSystem g where g.code = :identifier"),
		@NamedQuery(name = "GradeSystem.findByGradeID", query = "SELECT gs FROM GradeSystem gs join fetch gs.grades as g left join fetch g.sriGrade where gs.deleted='N' and g.deleted='N' and g.sriGrade ='N' and gs.id= :identifier") })
public class GradeSystem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_GRADESYSTEM__BY_CODE = "GradeSystem.findGradeSystemByCode";
	public static final String FIND_GRADE_BY_ID = "GradeSystem.findByGradeID";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String code;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gradeSystem")
	private Set<Grade> grades;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Grade> getGrades() {
		return this.grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

}