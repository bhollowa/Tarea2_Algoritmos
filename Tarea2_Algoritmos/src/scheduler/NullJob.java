package scheduler;

public class NullJob extends Job {

	public NullJob(int duration) {
		super(duration);
	}
	
	@Override
	public boolean isNull() {
		return true;
	}

}
