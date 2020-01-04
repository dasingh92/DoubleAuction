package com.quantumcube.doubleauction.constants;

public enum BidType {
	ASK("ask"), BUY("buy");

	private BidType(String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}
}
