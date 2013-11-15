package scheduler;

import java.util.ArrayList;

/** @author Daniel
 * This class represents a machine in he scheduler, it just holds an array of jobs
 */
public class Machine {

	/** The list of jobs*/
	private ArrayList<Job> jobs;
	
	/** String to represent the name*/
	private final String name;
	
	public Machine(String newName) {
		jobs = new ArrayList<Job>();
		this.name = newName;
	}

	/** Returns the size of the list of jobs*/
	public int amountOfJobs() {
		return jobs.size();
	}

	/** Accesor*/
	public ArrayList<Job> getJobs() {
		return jobs;
	}

	/** Accesor*/
	public String getName() {
		return name;
	}

	/** It returns the sum of the duration of all the jobs in the machines*/
	public int getStress() {
		int stress = 0;
		int semistress = 0;
		
		for (Job job : jobs) {
			if(job.isNull()) {
				semistress += job.getDuration();
			} else {
				stress += job.getDuration() + semistress;
				semistress = 0;
			}
		}
		
		return stress;
	}

	/** Adds a job to the list*/
	public void add(Job job) {
		jobs.add(job);
	}
	
	/** Adds a JobArray to the list, witch means it adds every job in the array*/
	public void add(JobArray jobArray) {
		for (Job job : jobArray.getJobs()) {
			this.add(job);
		}
	}

	/** It removes all the jobs from the list*/
	public void clean() {
		jobs = new ArrayList<Job>(); 
	}

}
