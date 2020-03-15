package com.yjs3507.hibernate.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLASS")
public class Classes extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	private DanceType danceType;

	@Enumerated(EnumType.STRING)
	private Level classLevel;

	@Column(name = "lesson_number")
	private int lessonNumber;

	@Column(name = "start_date")
	private Date startDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classes")
	private List<ClassMember> classMembers;

	@Enumerated(EnumType.STRING)
	private ClassStatus classStatus;

	public Classes() {
	}

	public Classes(DanceType danceType, Level classLevel, int lessonNumber, Date startDate) {
		this.danceType = danceType;
		this.classLevel = classLevel;
		this.lessonNumber = lessonNumber;
		this.startDate = startDate;
		this.classStatus = ClassStatus.ACTIVE;
	}

	public DanceType getDanceType() {
		return danceType;
	}

	public void setDanceType(DanceType danceType) {
		this.danceType = danceType;
	}

	public Level getClassLevel() {
		return classLevel;
	}

	public void setClassLevel(Level classLevel) {
		this.classLevel = classLevel;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<ClassMember> getClassMembers() {
		return classMembers;
	}

	public void setClassMembers(List<ClassMember> classMembers) {
		this.classMembers = classMembers;
	}

	public ClassStatus getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(ClassStatus classStatus) {
		this.classStatus = classStatus;
	}

	@Override
	public String toString() {
		return "Class [danceType=" + danceType + ", classLevel=" + classLevel + ", lessonNumber=" + lessonNumber
				+ ", startDate=" + startDate + "]";
	}

}
