package controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Trade;


public class TradeStoreControllerTest {
	
public TradeStoreController tradeStore;
public int millesecondInaday=24*60*60*1000;
	
	@Before
	public void init()
	{
		tradeStore=new TradeStoreController();
	}

	//@Test
	public void maturityTrade() throws Exception
	{
		Trade trade = new Trade();
		trade.setMaturityDate(new java.sql.Date(System.currentTimeMillis() - millesecondInaday));
		try {
			tradeStore.InsertTradeInITradeStore(trade);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(TradeStoreController.matureMessage));
		}
	}
	
	//@Test
	public void OldversionTrade() throws Exception
	{
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setMaturityDate(new java.sql.Date(System.currentTimeMillis() + millesecondInaday));
		try {
			tradeStore.InsertTradeInITradeStore(trade);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(TradeStoreController.higherVersionMessage));
		}
	}
	
	//@Test
	public void sameVersionTrade() throws Exception
	{
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(2);
		trade.setMaturityDate(new java.sql.Date(System.currentTimeMillis() + millesecondInaday));
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-5");
		boolean updateflag=false;
		try {
			updateflag=tradeStore.InsertTradeInITradeStore(trade);
		} catch (Exception e) {
			
		}
	assertTrue(updateflag);	
		
	}
	
	@Test
	public void newVersionTrade() throws Exception
	{
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(3);
		trade.setMaturityDate(new java.sql.Date(System.currentTimeMillis() + millesecondInaday));
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-6");
		trade.setCreatedDate(new java.sql.Date(System.currentTimeMillis() ));
	    trade.setExpired("N");	
		boolean insertflag=false;
		try {
			insertflag=tradeStore.InsertTradeInITradeStore(trade);
		} catch (Exception e) {
			
		}
	assertTrue(insertflag);	
		
	}
}
