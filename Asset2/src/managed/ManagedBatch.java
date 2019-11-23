package managed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import beans.Batch;

public class ManagedBatch {
	private List<Batch> elencoBatch = new ArrayList<Batch>();

	private Batch selectedBatch;

	public void addBatch(Batch b) {

		String id = b.getJob().getJobID();

		for (Batch batch : elencoBatch) {
			String idInElenco = batch.getJob().getJobID();

			if (idInElenco.equals(id) && !batch.isDone()) {
				System.out.println("job " + id + " gia' in esecuzione");
				return;
			}
		}

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Callable<Integer> callable = b.getCallable();
		Future<Integer> future = executor.submit(callable);
		b.setFuture(future);

		elencoBatch.add(b);
	}

	public List<Batch> getElencoBatch() {
		return elencoBatch;
	}

	public void setElencoBatch(List<Batch> elencoBatch) {
		this.elencoBatch = elencoBatch;
	}

	public Batch getSelectedBatch() {
		return selectedBatch;
	}

	public void setSelectedBatch(Batch selectedBatch) {
		this.selectedBatch = selectedBatch;
	}
}
