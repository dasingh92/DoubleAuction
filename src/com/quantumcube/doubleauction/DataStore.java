package com.quantumcube.doubleauction;

import java.util.ArrayList;
import java.util.List;


import com.quantumcube.doubleauction.constants.BidType;
import com.quantumcube.doubleauction.constants.TraderType;
import com.quantumcube.doubleauction.entities.Bid;
import com.quantumcube.doubleauction.entities.Trader;
import com.quantumcube.doubleauction.managers.BidManager;
import com.quantumcube.doubleauction.managers.TraderManager;

public class DataStore {
	public static final int MAX_NUMBIDS = 10;
	public static final int MIN_QUANTITY = 1;
	public static final int MAX_QUANTITY = 30;
	public static final int MIN_PRICE = 50;
	public static final int MAX_PRICE = 150;
	public static final int NUMSELLER = 10;
	public static final int NUMBUYER = 10;

	private static List<Trader> traders = new ArrayList<>();
	private static List<List<Bid>> bids = new ArrayList<>();

	public static List<Trader> getTraders() {
		// TODO Auto-generated method stub
		return traders;
	}

	public static List<List<Bid>> getBids() {
		// TODO Auto-generated method stub
		return bids;
	}

	public static void CreateSellers() {
		for (int i = 0; i < DataStore.NUMSELLER; i++) {
			int traderId = (int) (Math.random() * 100);
			TraderType type = TraderType.SELLER;

			Trader trader = TraderManager.getInstance().createTrader(traderId, type);
			traders.add(trader);
		}
	}
	
	public static void CreateBuyers() {
		for (int i = 0; i < DataStore.NUMBUYER; i++) {
			int traderId = (int) (Math.random() * 100 + 31);
			TraderType type = TraderType.BUYER;

			Trader trader = TraderManager.getInstance().createTrader(traderId, type);
			traders.add(trader);
		}
	}

	public static void CreateBids() {
		for(Trader trader : traders) {
			List<Bid> traderBids = new ArrayList<>();
			for(int i = 0; i < DataStore.MAX_NUMBIDS; i++) {
				int bidId = (int)(Math.random() * 1000);
				int traderId = trader.getTraderId();
				BidType type = (trader.getTraderType() == TraderType.BUYER) ? BidType.BUY : BidType.ASK ;
				int quantity = (int)(Math.random() * DataStore.MAX_QUANTITY);
				
				//If quantity less than 1 put MIN_QUANTITY else the random generation works.
				quantity = (quantity < DataStore.MIN_QUANTITY)? DataStore.MIN_QUANTITY : quantity;
				int price = (int)(Math.random() * 150);
				price = (price < DataStore.MIN_PRICE) ? DataStore.MIN_PRICE : price;
				
				// A Smart Trader implementation, i.e, if price <= 100 && trader Type is SELLER, then add 20% more to price,
				// else keep it the same.
				
				price = ((price <= 100) && (trader.getTraderType() == TraderType.SELLER)) ? price += 0.2 * price : price;
				
				Bid bid = BidManager.getInstance().createBid(bidId, traderId, type, quantity, price);
				
				traderBids.add(bid);
			}
			bids.add(traderBids);
		}
	}
	
	public static void InitializeData() {
		CreateSellers();
		CreateBuyers();
		CreateBids();
	}
	
}
