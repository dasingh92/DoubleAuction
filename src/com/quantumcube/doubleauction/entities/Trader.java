package com.quantumcube.doubleauction.entities;



import com.quantumcube.doubleauction.constants.TraderType;

public class Trader {
	@Override
	public String toString() {
		return "Trader [traderId=" + traderId + ", traderType=" + traderType + "]";
	}

	private int traderId;
	private TraderType traderType;
	
	public TraderType getTraderType() {
		return traderType;
	}

	public void setTraderType(TraderType traderType) {
		this.traderType = traderType;
	}

		public int getTraderId() {
		return traderId;
	}

	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}
}
