package com.yjs3507.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATTENDANCE_PERSON")
public class AttendancePerson extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "PersonId")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "AttendanceId", nullable = false)
	private Attendance attendance;

	public AttendancePerson() {
	}

	public AttendancePerson(Person person, Attendance attendance) {
		this.person = person;
		this.attendance = attendance;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

}
