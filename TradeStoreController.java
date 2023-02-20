package controllers;

import model.Trade;
import service.TradeStore;
import service.TradeStoreService;

public class TradeStoreController {
	public static String matureMessage="Mature trades";
	public static String higherVersionMessage="Higher version already exist in Trade store";
	
	public boolean InsertTradeInITradeStore(Trade trade) throws Exception
	{
		TradeStore service=new TradeStoreService();
		boolean flag=false;
		if (trade.getMaturityDate().before(new java.sql.Date(System.currentTimeMillis())))
			throw new Exception(TradeStoreController.matureMessage);
		else if(service.getlatestVersion(trade.getTradeId())>trade.getVersion())
			throw new Exception(TradeStoreController.higherVersionMessage);
		else if(service.getlatestVersion(trade.getTradeId())==trade.getVersion())
			flag=service.updateTrade(trade)>0?true:false;
		else
			flag=service.addTransmission(trade)>0?true:false;
		
	return flag;
	}

}
