package scheduler;

import java.util.ArrayList;

public class Scheduler {

	private ArrayList<Machine> machines;
	
	public Scheduler(int machinesNum) {
		machines = new ArrayList<Machine>();
		
		for (int i = 0; i < machinesNum; i++) {
			machines.add(new Machine());
		}
	}

	public int nbOfMachines() {
		return machines.size();
	}
}
