package TRADE.TRADE;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import dao.TradeDao;
import model.Trade;

public class TradeDaoTest {
	TradeDao dao;
	
	@Before
	public void init()
	{
		dao=TradeDao.getInstance();
	}
	
	//@Test
	public void display()
	{
		for (Trade trade:dao.displayTrades())
			System.out.println(trade);
	}
	
	//@Test
	public void search()
	{
		String TradeId="T1";
		Trade trade=dao.searchTrades(TradeId);
		System.out.println(trade);
		
	}
	
	//@Test
	public void insert()
	{
		Trade trade=new Trade();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setCounterPartyId("CP-2");
		trade.setBookId("B1");
		trade.setMaturityDate(new Date(2023, 02, 20));
		long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        trade.setCreatedDate(date);
        trade.setExpired("N");		
        System.out.println(trade);
		int result=dao.insert(trade);
		System.out.println(result);
	}
	
	//@Test
	public void update()
	{
		Trade trade=new Trade();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setCounterPartyId("CP-3");
		trade.setBookId("B2");
		trade.setMaturityDate(new Date(2023, 02, 20));
		System.out.println(trade);
		int result=dao.updateTrade(trade);
		System.out.println(result);
	}
	@Test
	public void updateExpired()
	{
		Trade trade=new Trade();
		long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        trade.setMaturityDate(date);
        trade.setExpired("N");	
		System.out.println(trade);
		int result=dao.updateTradesExpired(trade);
		System.out.println(result);
	}

}
