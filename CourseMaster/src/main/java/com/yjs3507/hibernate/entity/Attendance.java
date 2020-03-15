package com.yjs3507.hibernate.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Classes classes;
	@Enumerated(EnumType.STRING)
	private Level level;
	@Column(name = "lesson_number")
	private int lessonNumber;
	@Column(name = "date")
	private Date date;

	@OneToMany(mappedBy = "attendance")
	private List<AttendancePerson> attendancePerson;

	public Attendance() {
	}

	public Attendance(Classes classes, Level level, int lessonNumber, Date date) {
		this.classes = classes;
		this.level = level;
		this.lessonNumber = lessonNumber;
		this.date = date;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
