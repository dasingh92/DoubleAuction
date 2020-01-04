package com.quantumcube.doubleauction.dao;

import java.util.List;

import com.quantumcube.doubleauction.DataStore;
import com.quantumcube.doubleauction.entities.Trader;

public class TraderDao {
	public List<Trader> getTraders() {
		return DataStore.getTraders();
	}
}
