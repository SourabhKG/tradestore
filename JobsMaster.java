package dailyJobs;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobsMaster {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		JobDetail nightjob = JobBuilder.newJob(NightJob.class).withIdentity("nightJob", "mainUser").build();  
        Trigger nightJobTrigger = TriggerBuilder.newTrigger().withIdentity("cronNight", "mainUser")  
            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(14, 20))  
                .build();    
        Scheduler nightScheduler = new StdSchedulerFactory().getScheduler();         
        nightScheduler.start();
        nightScheduler.scheduleJob(nightjob, nightJobTrigger);  
        Thread.sleep(100000);  
        nightScheduler.shutdown();  
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
	}

}
