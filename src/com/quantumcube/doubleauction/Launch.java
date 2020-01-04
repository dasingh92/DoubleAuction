package com.quantumcube.doubleauction;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import com.quantumcube.doubleauction.entities.Bid;
import com.quantumcube.doubleauction.managers.BidManager;
import com.quantumcube.doubleauction.managers.TraderManager;

public class Launch {

	public static void main(String[] args) {
		PrintStream originalOut = System.out;
		PrintStream	out = null;
		try {
		out = new PrintStream(new FileOutputStream("Output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out);

		DataStore.InitializeData();

		TraderManager.printTraders();

		BidManager.printBids();
		
		Auctioneer.seperateBids();

		BidManager.checkForDuplicates();
		
			
		Set<Map.Entry<Bid, Bid>> mappings = Auctioneer.BidMatcher().entrySet();
		
		System.out.println("\nMatched Bids are as below... \n");
		for (Map.Entry<Bid, Bid> mapping : mappings) {
			System.out.println("Ask Bid: " + mapping.getKey() + "\nBuy Bid: " + mapping.getValue() + "\n");
		}
		
		System.out.println("\nThe total number of matched bids is: " + mappings.size());
		
		Auctioneer.unmatchedBids();
		
		Auctioneer.clearingBids();

		System.setOut(originalOut);
		System.out.println("Back to Console!!!");
		
		
	}
	
	
}
