package dailyJobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.TradeDao;
import model.Trade;

public class NightJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Night job running");
		TradeDao dao=TradeDao.getInstance();
		Trade trade=new Trade();
		trade.setMaturityDate(new java.sql.Date(System.currentTimeMillis()));
        trade.setExpired("N");	
		int result=dao.updateTradesExpired(trade);
		System.out.println(result +" record mark as Expired");
		
	}

}
