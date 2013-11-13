package scheduler;

import java.util.ArrayList;

/**
 * This is an array of jobs, it just holds a list of jobs and it is useful for the offline aproximization algorithm
 * */
public class JobArray {
	
	/** The list of jobs*/
	private ArrayList<Job> jobs;
	
	/** The sum of all the job duration*/
	private int stress;
	
	public JobArray() {
		super();
		
		this.jobs = new ArrayList<Job>();
		this.stress = 0;
	}
	
	public JobArray(int [] jobsDur) {
		super();
		
		this.jobs = new ArrayList<Job>();
		this.stress = 0;
		
		for (int i = 0; i < jobsDur.length; i++) {
			Job a = new Job(jobsDur[i]);
			this.add(a);
		}
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
	
	/** This add a job to the list and adds its stress to the Array stress*/
	public void add(Job j) {
		jobs.add(j);
		
		this.setStress(this.getStress() + j.getDuration());
		}

}
