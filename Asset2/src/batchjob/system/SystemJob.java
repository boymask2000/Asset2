package batchjob.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import batchjob.GenericJob;
import beans.Batch;
import common.Log;
import common.TimeUtil;

public abstract class SystemJob extends GenericJob {
	private static final long MILLI_ORA = 60 * 60 * 1000;
	private String startTime;
	private String className;
	private String desc;
	private List<Batch> elencoBatch = new ArrayList<Batch>();
	private Batch batch = new Batch();
	private long periodo = 24 * MILLI_ORA; // Millisecondi in 24 ore
	private long lastStart = 0;
	public static final long MINUTO = 60 * 1000;

	public SystemJob(String className, String startTime, String desc) {
		this.className = className;
		this.startTime = startTime;
		this.desc = desc;

		if (this.startTime == null)
			this.startTime = "163000";
	}

	public void setPeriodo(long per) {

		periodo = per;
	}

	public void go() {
		lastStart = (new Date()).getTime();

		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				attendiOraPartenza();
				
				while (true) {
					attendiPeriodo();

					Log.getLogger().info("System job "+desc+" started at "+(new Date()));
					
					int count = makeJob();
					
					Log.getLogger().info("System job "+desc+" completed at "+(new Date()));
					
					lastStart = (new Date()).getTime();
					// return count;
				}
			}

		
		};
		submit(callable, desc);

	}
	private void attendiOraPartenza() {
		while( TimeUtil.getCurrentTimeShort().compareTo(startTime)<0) {
			System.out.println("Attesa ora partenza " + startTime);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void attendiPeriodo() {

		while ((new Date()).getTime() - lastStart < periodo) {

		//	System.out.println("Attesa " + ((new Date()).getTime() - lastStart));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	protected void submit(Callable<Integer> callable, String desc) {

		batch.setDescription(desc);
		batch.setCallable(callable);
		batch.setJob(this);

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Future<Integer> future = executor.submit(callable);
		batch.setFuture(future);

		elencoBatch.add(batch);
	}

	public abstract int makeJob();

	public static List<SystemJob> getAllSystemJobs() {
		List<SystemJob> ll = new ArrayList<SystemJob>();

		SystemJob j1 = new ControlloScadenzeInterventi();
		ll.add(j1);

		return ll;

	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}
