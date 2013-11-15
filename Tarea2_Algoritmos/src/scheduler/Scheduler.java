package scheduler;

import java.util.ArrayList;
import utils.SchedUtil;

/** 
 * This is the main class, this is the class witch the user should interact, this allows to add the jobs to the machines in 3 different ways. 
 * */
public class Scheduler {

	/** This is the list of machines*/
	private ArrayList<Machine> machines;
	private int nbOfJobs;

	/** This is the constructor, the argument is the amount of machines, it should be > 0*/
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

			for (Job job : almostMin.getJobs())
				merge.add(job);

			tree.add(merge);
		}

		for (int i = 0; i < tree.size(); i++)
			machines.get(i).add(tree.get(i));

		nbOfJobs += jobs.size();
	}

	public void addOfflineBrute(ArrayList<Job> jobs) {
		int minMachineStress = 0;
		ArrayList<Machine> auxmachines = new ArrayList<Machine>();

		for (int i = 0; i < this.nbOfMachines(); i++)
			auxmachines.add(new Machine("AuxMachine" + (i + 1)));

		for (Job job : jobs)
			minMachineStress += 2*job.getDuration();

		final int maxBinaryInt = SchedUtil.getMaxBinaryInt(auxmachines.size(), jobs.size());

		for (int i = jobs.size() - 1; i < maxBinaryInt; i++) {
			String thisConfig = SchedUtil.addPrefix(Integer.toBinaryString(i), auxmachines.size() * jobs.size());

			if(SchedUtil.nbOfOnes(thisConfig) == jobs.size()) {
				ArrayList<String> configuration = new ArrayList<String>();

				for (int j = 0; j < auxmachines.size(); j++)
					configuration.add(thisConfig.substring(j*jobs.size(), ((j + 1)*jobs.size())));

				if (!SchedUtil.validConfig(configuration))
					continue;					

				for (int k = 0; k < configuration.size(); k++) {
					String config = configuration.get(k);
					for (int j = 0; j < config.length(); j++) {
						if (config.charAt(j) == '1')
							auxmachines.get(k).add(jobs.get(j));
					}
				}

				int maxStress = 0;

				for (int l = 0; l < auxmachines.size(); l++) {
					Machine actualMachine = auxmachines.get(l);

					if (actualMachine.getStress() > maxStress)
						maxStress = actualMachine.getStress();
				}

				if(maxStress < minMachineStress) {
					minMachineStress = maxStress;

					for (int j = 0; j < machines.size(); j++) {
						machines.get(j).clean();

						for (Job job : auxmachines.get(j).getJobs())
							machines.get(j).add(job);
					}
				}
			}

			for (Machine machine : auxmachines)
				machine.clean();
		}

		nbOfJobs += jobs.size();
	}

	/** This algorithm inserts each job one-by-one in the least stressed machine */
	public void addOnline(ArrayList<Job> jobs) {
		for (Job job : jobs) {
			Machine leastStressed = machines.get(0);

			for (int i = 1; i < machines.size(); i++) {
				Machine actualMachine = machines.get(i);

				if (actualMachine.getStress() < leastStressed.getStress())
					leastStressed = actualMachine;
			}

			leastStressed.add(job);
		}

		nbOfJobs += jobs.size();
	}
	
	public void addOnlineStep(ArrayList<JobArray> jobs) {		
		for (JobArray jobArray : jobs) {
			assert(jobArray.size() == this.machines.size());
			
			/*Hay que verificar si se puede llenar gaps*/
			
			for (int i = 0; i < this.machines.size(); i++) {
				Job now = jobArray.getMin();
				int machinDex = now.getMach();
				
				machines.get(machinDex).add(now);
				
				for (Machine mach : machines) {
					if(machines.indexOf(mach) != machinDex)
						mach.add(new NullJob(now.getDuration()));
				}
			}
			
			/*Tengo que mergiar los gaps*/
		}
		
		nbOfJobs += jobs.size();
	}

	/** It returns the most "stressed" machine (the one which has the greater sum of duration of its jobs)*/
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
	
	/** This removes all the jobs from the machines*/
	public void clean() {
		for (Machine machine : machines) {
			machine.clean();
		}
	}

	/** Returns a String representation of the Scheduler*/
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Machine machine : machines) {
			builder.append(machine.getName() + " tiene " + machine.getJobs().size() + " trabajos, y en total tiene stress: " + machine.getStress() + ".\n");
		}

		return builder.toString();
	}
}
