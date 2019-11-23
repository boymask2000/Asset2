package managed;

import batchjob.GenericJob;

public class BatchStarter {
	public void exec(String className) {
		
		try {
			GenericJob job = (GenericJob) Class.forName(className).newInstance();
			job.go();
		} catch (Exception e) {
	
			e.printStackTrace();
		}

	}
}
