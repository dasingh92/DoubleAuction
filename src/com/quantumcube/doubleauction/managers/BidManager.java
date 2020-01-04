package com.quantumcube.doubleauction.managers;

import java.util.Iterator;
import java.util.List;

import com.quantumcube.doubleauction.Auctioneer;
import com.quantumcube.doubleauction.constants.BidType;
import com.quantumcube.doubleauction.dao.BidDao;
import com.quantumcube.doubleauction.entities.Bid;

public class BidManager {
	private BidManager() {

	}

	private static BidManager instance = new BidManager();
	private static BidDao bidDao = new BidDao();
	private static Auctioneer auctioneer = new Auctioneer();

	public static BidManager getInstance() {
		return instance;
	}

	public Bid createBid(int bidId, int traderId, BidType type, int quantity, int price) {
		Bid bid = new Bid();
		bid.setBidId(bidId);
		bid.setTraderId(traderId);
		bid.setBidType(type);
		bid.setQuantity(quantity);
		bid.setPrice(price);
		return bid;
	}
	
	public Bid createBid(Bid bid, int quantity) {
		bid.setQuantity(quantity);
		return bid;
	}
	
	public List<List<Bid>> getBids(){
		return bidDao.getBids();
		
	}
	
	public static void printBids() {
		List<List<Bid>> bids = bidDao.getBids();
		Iterator<List<Bid>> bidsIterator = bids.iterator();
		System.out.println("\nThe bids are as follows...\n");
		while (bidsIterator.hasNext()) {
			List<Bid> listBid = bidsIterator.next();
			Iterator<Bid> iterator = listBid.iterator();

			while (iterator.hasNext()) {
				Bid bid = iterator.next();
				System.out.println("\n" + bid);
			}

		}

	}
	
	public static void checkForDuplicates() {
		
		List<Bid> buyBids = auctioneer.getbuyBid();
		List<Bid> askBids = auctioneer.getAskBid();
		
		Iterator<Bid> askIterator = askBids.iterator();
		Iterator<Bid> iterator = buyBids.iterator();
		
		while (iterator.hasNext()) {
			Bid bid = iterator.next();
			while (askIterator.hasNext()) {
				Bid bid1 = askIterator.next();
				if (bid1.getBidId() == bid.getBidId()) {
					System.out.println("\nMatch Found!!!");
				}

			}
		}
		System.out.println("\nAll Bid Id Are Unique!!!");
	}
}
