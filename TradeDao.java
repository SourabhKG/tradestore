package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Trade;

public class TradeDao {


	private static String databaseURL = "jdbc:ucanaccess://C://Users//user//Documents//TradeStore.accdb";
	
	private static TradeDao dao=null;
	
	private TradeDao() {
		
	}
	public static synchronized TradeDao getInstance()
	{
		if(dao==null)
		{
			dao=new TradeDao();
		}
		return dao;
	}
    
    public int updateTrade(Trade trade)
    {
    	int row=0;
    	try (Connection connection = DriverManager.getConnection(databaseURL)) {
                     
            String sql = "update trade  set CounterPartyId=? ,BookId=?, MaturityDate=? where TradeId=? and Version=? ";
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trade.getCounterPartyId());
            statement.setString(2, trade.getBookId());
            statement.setDate(3, trade.getMaturityDate());
            statement.setString(4, trade.getTradeId());
            statement.setInt(5, trade.getVersion());
            row = statement.executeUpdate();
           
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  row;
    }
    public int updateTradesExpired(Trade trade)
    {
    	int row=0;
    	try (Connection connection = DriverManager.getConnection(databaseURL)) {
                     
            String sql = "update trade  set Expired=?  where MaturityDate < ? ";
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trade.getExpired());
            statement.setDate(2, trade.getMaturityDate());
            row = statement.executeUpdate();
         } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  row;
    }
    public int insert(Trade trade)
    {
    	int row=0;
    	try (Connection connection = DriverManager.getConnection(databaseURL)) {
                     
            String sql = "insert into trade (TradeId,Version,CounterPartyId,BookId,MaturityDate,CreatedDate,Expired) values(?,?,?,?,?,?,?)  ";
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trade.getTradeId());
            statement.setInt(2, trade.getVersion());
            statement.setString(3, trade.getCounterPartyId());
            statement.setString(4, trade.getBookId());
            statement.setDate(5, trade.getMaturityDate());
            statement.setDate(6, trade.getCreatedDate());
            statement.setString(7, trade.getExpired());
            row = statement.executeUpdate();
            connection.close();
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  row;
    }
    
    public Trade searchTrades(String tradeId)
    {
    	 Trade trade=null;
    	try (Connection connection = DriverManager.getConnection(databaseURL)) {
                     
            String sql = "SELECT * FROM Trade where TradeId=?";
             
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tradeId);
            ResultSet result = statement.executeQuery();
           
            while (result.next()) {
            	trade=new Trade();
            	trade.setTradeId(result.getString("TradeId"));
            	trade.setVersion( result.getInt("Version"));
            	trade.setCounterPartyId( result.getString("CounterPartyId"));
            	trade.setBookId( result.getString("BookId"));
            	trade.setMaturityDate( result.getDate("MaturityDate"));
            	trade.setCreatedDate( result.getDate("CreatedDate"));
            	trade.setExpired(result.getString("Expired"));
            	
               
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  trade;
    }
    
    public int getlatestVersion(String tradeId)
	{
		int latestVersion = 0;
		try (Connection connection = DriverManager.getConnection(databaseURL)) {

			String sql = "SELECT max(Version) as lVersion	FROM Trade where TradeId=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, tradeId);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				latestVersion = result.getInt("lVersion");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return latestVersion;
	}
    
    public ArrayList<Trade> displayTrades()
    {
    	ArrayList<Trade> TradeList=new ArrayList<Trade>();
    	try (Connection connection = DriverManager.getConnection(databaseURL)) {
                     
            String sql = "SELECT * FROM Trade";
             
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            Trade trade;
            while (result.next()) {
            	trade=new Trade();
            	trade.setTradeId(result.getString("TradeId"));
            	trade.setVersion( result.getInt("Version"));
            	trade.setCounterPartyId( result.getString("CounterPartyId"));
            	trade.setBookId( result.getString("BookId"));
            	trade.setMaturityDate( result.getDate("MaturityDate"));
            	trade.setCreatedDate( result.getDate("CreatedDate"));
            	trade.setExpired(result.getString("Expired"));
            	TradeList.add(trade);
               
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return  TradeList;
    }
}
