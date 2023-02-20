package service;

import java.sql.Date;

import dao.TradeDao;
import model.Trade;

public class TradeStoreService implements TradeStore{

	private TradeDao dao=null;
	
	public TradeStoreService() {
		dao=TradeDao.getInstance();
	}
	
	@Override
	public int addTransmission(Trade trade) {
				
		return dao.insert(trade);
	}

	@Override
	public int getlatestVersion(String tradeId) {
		
		if(tradeId==null)
			return 0;
		return dao.getlatestVersion(tradeId);
	}

	@Override
	public int updateTrade(Trade trade) {
		return dao.updateTrade(trade);
		
	}


}
