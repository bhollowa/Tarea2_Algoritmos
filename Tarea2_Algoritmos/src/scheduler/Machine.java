package scheduler;

import java.util.ArrayList;

public class Machine {

	private ArrayList<Job> jobs;
	
	private final String name;
	
	public Machine(String newName) {
		jobs = new ArrayList<Job>();
		this.name = newName;
	}

	public int amountOfJobs() {
		return jobs.size();
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public String getName() {
		return name;
	}

	public int getStress() {
		int stress = 0;
		
		for (Job job : jobs) {
			stress += job.getDuration();
		}
		
		return stress;
	}

	public void add(Job job) {
		jobs.add(job);
	}
	
	public void add(JobArray jobArray) {
		for (Job job : jobArray.getJobs()) {
			this.add(job);
		}
	}

	public void clean() {
		jobs = new ArrayList<Job>(); 
	}

}
