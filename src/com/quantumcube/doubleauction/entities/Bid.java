package com.quantumcube.doubleauction.entities;



import com.quantumcube.doubleauction.constants.BidType;

public class Bid {
	private int bidId;
	private int traderId;
	private BidType bidType;
	private int quantity;
	private int price;

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public int getTraderId() {
		return traderId;
	}

	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}

	public BidType getBidType() {
		return bidType;
	}

	public void setBidType(BidType bidType) {
		this.bidType = bidType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bid [bidId=" + bidId + ", traderId=" + traderId + ", bidType=" + bidType + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	
}

