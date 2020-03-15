package com.yjs3507.hibernate.entity;

public enum Role {

	LEADER("LEADER", "Leader"), FOLLOWER("FOLLOWER", "Follower"),
	UNKNOWN("UNKNOWN", "Belli Değil (Solo kayıtları için)");

	private final String key;

	private final String screenDesc;

	public String getKey() {
		return key;
	}

	public String getScreenDesc() {
		return screenDesc;
	}

	private Role(String key, String screenDesc) {
		this.key = key;
		this.screenDesc = screenDesc;
	}
}
