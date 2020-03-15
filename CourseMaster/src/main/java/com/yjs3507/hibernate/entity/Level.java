package com.yjs3507.hibernate.entity;

public enum Level {

	I4("I4", null, "I4"), I3("I3", I4, "I3"), I2("I2", I3, "I2"), I1("I1", I2, "I1"), IO4("IO4", I1, "IO4"),
	IO3("IO3", IO4, "IO3"), IO2("IO2", IO3, "IO2"), IO1("IO1", IO2, "IO1"), O4("O4", IO1, "O4"), O3("O3", O4, "O3"),
	O2("O2", O3, "O2"), O1("O1", O2, "O1"), B4("B4", O1, "B4"), B3("B3", B4, "B3"), B2("B2", B3, "B2"),
	B1("B1", B2, "B1");

	private final String key;

	private final Level postLevel;

	private final String screenDesc;

	public String getKey() {
		return key;
	}

	public Level getPostLevel() {
		return postLevel;
	}

	private Level(String key, Level postLevel, String screenDesc) {
		this.key = key;
		this.postLevel = postLevel;
		this.screenDesc = screenDesc;
	}

	public String getScreenDesc() {
		return screenDesc;
	}

}
