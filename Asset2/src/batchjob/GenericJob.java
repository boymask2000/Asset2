package batchjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import beans.Batch;
import managed.ManagedBatch;

public abstract class GenericJob {
	protected final BlockingQueue<String> queue;
	private Batch batch = new Batch();

	public GenericJob() {
		queue = new LinkedBlockingQueue<String>();
	}

	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public abstract void go();

	private String lastGood = "";

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
		return val;

	}

	protected void submit(Callable<Integer> callable, String desc) {

		batch.setDescription(desc);
		batch.setCallable(callable);
		batch.setJob(this);
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ManagedBatch profileBean = application.evaluateExpressionGet(context, "#{managedBatch}", ManagedBatch.class);
		profileBean.addBatch(batch);
	}
}
