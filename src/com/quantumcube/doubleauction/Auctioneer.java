package com.quantumcube.doubleauction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quantumcube.doubleauction.constants.BidType;
import com.quantumcube.doubleauction.entities.Bid;
import com.quantumcube.doubleauction.entities.sortByPriceAscending;
import com.quantumcube.doubleauction.entities.sortByPriceDescending;
import com.quantumcube.doubleauction.managers.BidManager;

public class Auctioneer {
	private static List<List<Bid>> bids = BidManager.getInstance().getBids();
	private static List<Bid> askBid = new ArrayList<>();
	private static List<Bid> buyBid = new ArrayList<>();
	private static Map<Bid, Bid> match = new HashMap<>();
	private static Map<Integer, Double> traderProfit = new HashMap<>();
	private static int auctioneerProfit = 0;

	public static void seperateBids() {
		Iterator<List<Bid>> bidsIterator = bids.iterator();

		while (bidsIterator.hasNext()) {
			List<Bid> listBid = bidsIterator.next();
			Iterator<Bid> iterator = listBid.iterator();

			while (iterator.hasNext()) {
				Bid bid = iterator.next();
				if (bid.getBidType() == BidType.ASK) {
					askBid.add(bid);
				} else {
					buyBid.add(bid);
				}
			}

		}
		Collections.sort(askBid, new sortByPriceAscending());
		Collections.sort(buyBid, new sortByPriceDescending());

		System.out.println("\n The number of Ask Bids is: " + askBid.size());
		System.out.println("\n The number of Buy Bids is: " + buyBid.size());

		auctioneerProfit = (askBid.size() * 2 + buyBid.size() * 2);
		System.out.println("\nAuctioneer Profit at initial bidding: " + auctioneerProfit);
	}

	private static Map<Bid, Bid> matchBids() {
		
		Iterator<Bid> askIterator = askBid.iterator();
		Iterator<Bid> buyIterator = buyBid.iterator();
		while (askIterator.hasNext() && buyIterator.hasNext()) {
			Bid ask = askIterator.next();
			Bid buy = buyIterator.next();

			if (!(ask.getPrice() >= buy.getPrice()) && !(ask.getTraderId() == buy.getTraderId())) {
				match.put(ask, buy);
				if (ask.getQuantity() < buy.getQuantity()) {
					askIterator.remove();
					buyBid.set(buyBid.indexOf(buy),
							BidManager.getInstance().createBid(buy.getBidId(), buy.getTraderId(), buy.getBidType(),
									(buy.getQuantity() - ask.getQuantity()), buy.getPrice()));
				} else if (ask.getQuantity() == buy.getQuantity()) {
					askIterator.remove();
					buyIterator.remove();
				} else {
					buyIterator.remove();
					askBid.set(askBid.indexOf(ask),
							BidManager.getInstance().createBid(ask.getBidId(), ask.getTraderId(), ask.getBidType(),
									(ask.getQuantity() - buy.getQuantity()), ask.getPrice()));
				}

			} else {
				break;
			}

		}
		
		return match;
	}

	public static  Map<Bid, Bid> BidMatcher(){
		do{
			match = matchBids();
		}
		while(!(askBid.get(0).getPrice() - buyBid.get(0).getPrice() > 0)); 
		return match;
	}
	
	public static void clearingBids() {
		Set<Map.Entry<Bid, Bid>> mapping = match.entrySet();

		for (Map.Entry<Bid, Bid> mapper : mapping) {
			Bid ask = mapper.getKey();
			Bid buy = mapper.getValue();

			int clearingPrice = (ask.getPrice() + buy.getPrice()) / 2;

			if (ask.getQuantity() <= buy.getQuantity()) {
				double askProfit = (clearingPrice - ask.getPrice()) * ask.getQuantity();
				double buyProfit = (buy.getPrice() - clearingPrice) * ask.getQuantity();
				double auctionProfit = (askProfit * 0.1 + buyProfit * 0.1 + 6);

				if (traderProfit.containsKey(ask.getTraderId())) {
					traderProfit.put(ask.getTraderId(),
							traderProfit.get(ask.getTraderId()) + (askProfit - askProfit * 0.1 - 3));
				} else {
					traderProfit.put(ask.getTraderId(), askProfit - askProfit * 0.1 - 3);
				}
				if (traderProfit.containsKey(buy.getTraderId())) {
					traderProfit.put(buy.getTraderId(),
							traderProfit.get(buy.getTraderId()) + (buyProfit - buyProfit * 0.1 - 3));
				} else {
					traderProfit.put(buy.getTraderId(), buyProfit - buyProfit * 0.1 - 3);
				}
				auctioneerProfit += auctionProfit;
			} else {
				double askProfit = (clearingPrice - ask.getPrice()) * buy.getQuantity();
				double buyProfit = (buy.getPrice() - clearingPrice) * buy.getQuantity();
				double auctionProfit = (askProfit * 0.1 + buyProfit * 0.1);

				traderProfit.put(ask.getTraderId(), askProfit - askProfit * 0.1);
				traderProfit.put(buy.getTraderId(), askProfit - askProfit * 0.1);
				auctioneerProfit += auctionProfit;
			}
		}
		Set<Map.Entry<Integer, Double>> mappings = traderProfit.entrySet();
		System.out.println("\nThe trader and Auctioneer Profit is... \n");
		for (Map.Entry<Integer, Double> mappers : mappings) {
			System.out.println("\nTrader Id : " + mappers.getKey() + ", Profit: " + mappers.getValue());

		}
		System.out.println("\nAuctioneer Profit after Bid Matching: " + auctioneerProfit);
	}

	public static void unmatchedBids() {
		Iterator<Bid> askIterator = askBid.iterator();
		Iterator<Bid> buyIterator = buyBid.iterator();
		
		System.out.println("\n The number of unmatched Ask Bids after matching is: " + askBid.size());
		System.out.println("\n The number of unmatched Buy Bids after matching is: " + buyBid.size());
		
		System.out.println("The UNMATCHED BIDS ARE AS FOLLOWS...\n");
		System.out.println("\t \t \t \t \t \t \t \t \t \t ASK BIDS \t \t \t \t \t \t \t \t \t \t \t BUY BIDS"
				+ "\n-------------------------------------------------------------------------------------------------------"+
				"-----------------------------------------------------------------\n");
		while (askIterator.hasNext() && buyIterator.hasNext()) {
			Bid ask = askIterator.next();
			Bid buy = buyIterator.next();
			System.out.println(ask + " \t|\t " + buy + "\n");
		}
	}

	public List<Bid> getAskBid() {
		return askBid;
	}

	public List<Bid> getbuyBid() {
		return buyBid;
	}
}
