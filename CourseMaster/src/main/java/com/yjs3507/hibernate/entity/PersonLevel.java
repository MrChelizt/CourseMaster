package com.yjs3507.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_LEVEL")
public class PersonLevel extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	private Level level;

	@Enumerated(EnumType.STRING)
	private DanceType danceType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "person_id")
	private Person person;

	public PersonLevel() {

	}

	public PersonLevel(Level level, DanceType danceType) {
		this.level = level;
		this.danceType = danceType;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public DanceType getDanceType() {
		return danceType;
	}

	public void setDanceType(DanceType danceType) {
		this.danceType = danceType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
