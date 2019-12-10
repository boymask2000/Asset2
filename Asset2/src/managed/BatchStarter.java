package managed;

import batchjob.GenericJob;

public class BatchStarter {
	public void callexec() {
		System.out.println("callExec");
	}

	public void exec(String className) {
		System.out.println("starting " + className);
		try {
			GenericJob job = (GenericJob) Class.forName(className).newInstance();
			job.go();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
