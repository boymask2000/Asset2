package batchjob.system;

public class ControlloScadenzeInterventi extends SystemJob {

	public ControlloScadenzeInterventi() {
		super("batchjob.system.ControlloScadenzeInterventi", null, "ControlloScadenzeInterventi");

	}

	@Override
	public int makeJob() {

		for (int i = 0; i < 3; i++) {

			try {
				System.out.println("ControlloScadenzeInterventi");
				Thread.sleep(5000);
			} catch (Throwable e) {
				System.out.println("got interrupt");
				break;
			}
		}
		return 0;

	}

}
