package beans;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import batchjob.GenericJob;
import common.TimeUtil;

public class Batch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Callable<Integer> callable;
	private String description;
	private String startTime;
	private String endTime;
	private Future<Integer> future;
	private String status;
	private GenericJob job;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Callable<Integer> getCallable() {
		return callable;
	}

	public void setCallable(Callable<Integer> callable) {
		this.callable = callable;
	}

	public Future<Integer> getFuture() {
		return future;
	}

	public void setFuture(Future<Integer> future) {
		this.future = future;
		startTime = TimeUtil.getTimestamp();
		Runnable run = new Runnable() {

			@Override
			public void run() {
				while (!future.isDone()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
		
						e.printStackTrace();
					}
				}
				
				endTime = TimeUtil.getTimestamp();
		
			}

		};
		(new Thread(run)).start();
	}

	public String getStatus() {
		if (future.isDone())
			return "Completed";
		return "Running";
	}
	
	public boolean isDone() {
		return future.isDone();
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public GenericJob getJob() {
		return job;
	}

	public void setJob(GenericJob job) {
		this.job = job;
	}
}
