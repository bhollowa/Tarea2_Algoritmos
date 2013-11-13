package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import scheduler.JobArray;
import scheduler.Scheduler;

public class TestSchedulerStep {

	public static final int MACHINES_NUM = 3;
	
	public Scheduler sched;
	public ArrayList<JobArray> jobs;
	
	@Before
	public void setUp() {
		sched = new Scheduler(MACHINES_NUM);
		jobs = new ArrayList<JobArray>();
	}
	
	@Test
	public void addOnJobBasic() {
		int dur[] = {1,2,3};
		
		JobArray steve = new JobArray(dur);
		jobs.add(steve);
		
		sched.addOnlineStep(jobs);
		assertTrue(sched.nbOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 1);
		assertTrue(sched.getMachines().get(1).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(1).getStress() == 3);
		assertTrue(sched.getMachines().get(2).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(2).getStress() == 6);
	}
}
