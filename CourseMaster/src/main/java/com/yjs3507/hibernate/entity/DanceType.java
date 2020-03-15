package com.yjs3507.hibernate.entity;

public enum DanceType {

	LINDY_HOP("Lindy Hop", Level.I4), SOLO_CHARLESTON("Solo Charleston", Level.IO4);

	private final String screenDesc;

	private final Level maxLevel;

	public String getScreenDesc() {
		return screenDesc;
	}

	public Level getMaxLevel() {
		return maxLevel;
	}

	private DanceType(String screenDesc, Level maxLevel) {
		this.screenDesc = screenDesc;
		this.maxLevel = maxLevel;
	}

}
