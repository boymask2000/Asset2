package batchjob.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import common.Log;

public class EmptyJob implements Job {
	 
    public void execute(JobExecutionContext context)
     throws JobExecutionException {
 Log.getLogger().debug("ecco");
    	System.out.println("Sono un Job e sono partito.");
 
    }
 
}