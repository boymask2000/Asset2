package batchjob.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import common.Log;

public class ControlloScadenzeInterventi implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Log.getLogger().info("ControlloScadenzeInterventi");

	}

}
