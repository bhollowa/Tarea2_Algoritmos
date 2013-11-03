package scheduler;

public class Job {
	
	private int duration;
	
	public Job(int total_dur) {
		this.duration = total_dur;
	}

	public int getDuration() {
		return duration;
	}
}
