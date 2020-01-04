package com.quantumcube.doubleauction.managers;

import java.util.List;

import com.quantumcube.doubleauction.constants.TraderType;
import com.quantumcube.doubleauction.dao.TraderDao;
import com.quantumcube.doubleauction.entities.Trader;

public class TraderManager {
	private TraderManager(){
		
	}
	private static TraderManager instance = new TraderManager();
	private static TraderDao dao = new TraderDao();
	
	public static TraderManager getInstance() {
		return instance;
	}
	
	public Trader createTrader(int traderId, TraderType type) {
		Trader trader = new Trader();
		trader.setTraderId(traderId);
		trader.setTraderType(type);
		return trader;	
	}
	public List<Trader> getTraders(){
		return dao.getTraders();
	}
	
	public static void printTraders() {
	List<Trader> traders = dao.getTraders();
	System.out.println("The traders are as follows... \n");
	for (Trader trader : traders)
		System.out.println("\n" + trader);
	}
}
