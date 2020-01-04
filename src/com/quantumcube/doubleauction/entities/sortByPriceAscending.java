package com.quantumcube.doubleauction.entities;

import java.util.Comparator;

public class sortByPriceAscending implements Comparator<Bid> {
	@Override
	public int compare(Bid o1, Bid o2) {
		return o1.getPrice() - o2.getPrice();
	}
	
}