package scheduler;

import java.util.ArrayList;
import java.util.Iterator;

public class Scheduler {

	private ArrayList<Machine> machines;
	private int nbOfJobs;
	
	public Scheduler(int machinesNum) {
		machines = new ArrayList<Machine>();
		
		for (int i = 0; i < machinesNum; i++) {
			machines.add(new Machine("Machine" + i + 1));
		}
	}
	
	public ArrayList<Machine> getMachines() {
		return machines;
	}
	
	public int nbOfJobs() {
		return nbOfJobs;
	}

	public int nbOfMachines() {
		return machines.size();
	}

	public void addOffline(ArrayList<Job> jobs) {		
		nbOfJobs += jobs.size();
	}

	public void addOnline(ArrayList<Job> jobs) {
		for (Job job : jobs) {
			Machine leastStressed = machines.get(0);
			
			for (int i = 1; i < machines.size(); i++) {
				Machine actualMachine = machines.get(i);
				
				if (actualMachine.getStress() < leastStressed.getStress()) {
					leastStressed = actualMachine;
				}
			}
			
			leastStressed.add(job);
		}
		
		nbOfJobs += jobs.size();
	}
}
