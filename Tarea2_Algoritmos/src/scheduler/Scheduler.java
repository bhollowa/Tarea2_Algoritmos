package scheduler;

import java.util.ArrayList;

public class Scheduler {

	private ArrayList<Machine> machines;
	private int nbOfJobs;

	public Scheduler(int machinesNum) {
		machines = new ArrayList<Machine>();

		for (int i = 0; i < machinesNum; i++) {
			machines.add(new Machine("Machine" + (i + 1)));
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
		ArrayList<JobArray> tree = new ArrayList<JobArray>();
		
		for (Job job : jobs) {
			JobArray a = new JobArray();
			a.add(job);
			
			tree.add(a);
		}
		
		while(tree.size() > machines.size()) {
			JobArray min = null, almostMin = null;
			int minStress = 0, almostMinStress = 0;
			
			for (Job job : jobs) {
				minStress += 2*job.getDuration();
				almostMinStress += 2*job.getDuration();
			}
			
			for (JobArray jobArray : tree) {
				if(jobArray.getStress() < minStress) {
					almostMinStress = minStress;
					almostMin = min;
					
					minStress = jobArray.getStress();
					min = jobArray;
				} else if (jobArray.getStress() < almostMinStress) {
					almostMinStress = jobArray.getStress();
					almostMin = jobArray;
				}
			}
			
			tree.remove(min);
			tree.remove(almostMin);
			
			JobArray merge = min;
			
			for (Job job : almostMin.getJobs()) {
				merge.add(job);
			}

			tree.add(merge);
		}
		
		for (int i = 0; i < tree.size(); i++) {
			machines.get(i).add(tree.get(i));
		}
		
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

	public Machine getMostStressedMachine() {
		Machine ret = machines.get(0);
		int maxStress = 0;

		for (int i = 0; i < machines.size(); i++) {
			Machine actualMachine = machines.get(i);

			if (actualMachine.getStress() > maxStress) {
				maxStress = actualMachine.getStress();
				ret = actualMachine;
			}
		}

		return ret;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Machine machine : machines) {
			builder.append(machine.getName() + " tiene " + machine.getJobs().size() + " trabajos, y en total tiene stress: " + machine.getStress() + ".\n");
		}

		return builder.toString();
	}
}
