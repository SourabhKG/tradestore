package model;

import java.sql.Date;

public class Trade {
	private String TradeId;
	private int Version ;
	private String CounterPartyId;
	private String BookId;
	private Date MaturityDate;
	private Date CreatedDate;
	private String	Expired;
	
	public String getTradeId() {
		return TradeId;
	}
	public void setTradeId(String tradeId) {
		TradeId = tradeId;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}
	public String getCounterPartyId() {
		return CounterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		CounterPartyId = counterPartyId;
	}
	public String getBookId() {
		return BookId;
	}
	public void setBookId(String bookId) {
		BookId = bookId;
	}
	public Date getMaturityDate() {
		return MaturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		MaturityDate = maturityDate;
	}
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	public String getExpired() {
		return Expired;
	}
	public void setExpired(String expired) {
		Expired = expired;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (TradeId + ", " + Version + ", " + CounterPartyId + ", " + BookId+ ", " + MaturityDate + ", " + CreatedDate + ", " + Expired);
	}
	
	

}
