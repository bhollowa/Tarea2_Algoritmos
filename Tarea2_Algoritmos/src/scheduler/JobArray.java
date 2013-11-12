package scheduler;

import java.util.ArrayList;

public class JobArray {
	
	private ArrayList<Job> jobs;
	private int stress;
	
	public JobArray() {
		super();
		
		this.jobs = new ArrayList<Job>();
		this.stress = 0;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		this.stress = stress;
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}
	
	public void add(Job j) {
		jobs.add(j);
		
		this.setStress(this.getStress() + j.getDuration());
		}

}
