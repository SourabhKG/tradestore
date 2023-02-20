package service;

import java.sql.Date;

import model.Trade;

public interface TradeStore {
	
	public int addTransmission(Trade trade);

	public int getlatestVersion(String tradeId);

	public int updateTrade(Trade trade);


}
