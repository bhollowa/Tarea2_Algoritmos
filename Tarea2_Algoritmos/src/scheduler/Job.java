package scheduler;

public class Job {
	
	private int duration;
	private int mach;
	
	public int getMach() {
		return mach;
	}

	public void setMach(int mach) {
		this.mach = mach;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Job(int total_dur) {
		this.duration = total_dur;
	}

	public int getDuration() {
		return duration;
	}

	public boolean isNull() {
		return false;
	}
}
