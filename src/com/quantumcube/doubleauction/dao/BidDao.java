package com.quantumcube.doubleauction.dao;

import java.util.List;

import com.quantumcube.doubleauction.DataStore;
import com.quantumcube.doubleauction.entities.Bid;

public class BidDao {
	public List<List<Bid>> getBids() {
		return DataStore.getBids();
	}
}
