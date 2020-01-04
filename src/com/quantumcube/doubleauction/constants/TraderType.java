package com.quantumcube.doubleauction.constants;

public enum TraderType {
	SELLER("seller"), BUYER("buyer");

	private TraderType(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}
}
