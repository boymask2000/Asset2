package batchjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

import beans.Batch;
import common.JsfUtil;
import managed.ManagedBatch;

public abstract class GenericJob {
	protected final BlockingQueue<String> queue;

	private Batch batch = new Batch();
	private String lastGood = "";

	protected String jobID = null;

	public GenericJob() {
		queue = new LinkedBlockingQueue<String>();
	}

	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public abstract void go();

	public String getPartialResult() {
		if (batch.getFuture().isDone())
			return lastGood;
		String val = "";

		while (true) {
			String s = queue.poll();
			if (s == null)
				break;
			val = s;
			lastGood = val;
		}
		if (val.equals(""))
			return lastGood;
		return val;

	}

	protected void submit(Callable<Integer> callable, String desc) {

		batch.setDescription(desc);
		batch.setCallable(callable);
		batch.setJob(this);

		ManagedBatch managedBatch = (ManagedBatch) JsfUtil.getBean("managedBatch");
//				
//		FacesContext context = FacesContext.getCurrentInstance();
//		Application application = context.getApplication();
//		ManagedBatch profileBean = application.evaluateExpressionGet(context, "#{managedBatch}", ManagedBatch.class);
		managedBatch.addBatch(batch);
	}

	public String getJobID() {
		return jobID;
	}


}
