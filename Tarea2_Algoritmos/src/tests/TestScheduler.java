package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import scheduler.Job;
import scheduler.Scheduler;

public class TestScheduler {

	public static final int MACHINES_NUM = 5;
	
	public Scheduler sched;
	public ArrayList<Job> jobs;
	
	@Before
	public void setUp() {
		sched = new Scheduler(MACHINES_NUM);
		jobs = new ArrayList<Job>();
	}
	
	@Test
	public void testInit() {
		assertTrue(sched.nbOfMachines() == 5);
	}
	
	@Test
	public void addOffJobBasicBrute() {
		Job steve = new Job(10);
		jobs.add(steve);
		
		sched.addOfflineBrute(jobs);
		
		assertTrue(sched.nbOfJobs() == 1);
		assertTrue(sched.getMachines().get(4).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(4).getStress() == 10);
	}
	
	@Test
	public void addOffTwoJobBrute() {
		Job steve = new Job(10);
		Job jennifer = new Job(5);

		jobs.add(steve);
		jobs.add(jennifer);
		
		sched.addOfflineBrute(jobs);
				
		assertTrue(sched.nbOfJobs() == 2);
		assertTrue(sched.getMachines().get(4).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(4).getStress() == 10);
		assertTrue(sched.getMachines().get(3).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(3).getStress() == 5);
	}

	@Test
	public void addOffJobBasicTree() {
		Job steve = new Job(10);
		jobs.add(steve);
		
		sched.addOffline(jobs);
		assertTrue(sched.nbOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 10);
	}
	
	@Test
	public void addOffTwoJobsTree() {
		Job steve = new Job(10);
		Job jennifer = new Job(5);

		jobs.add(steve);
		jobs.add(jennifer);
		
		sched.addOffline(jobs);
				
		assertTrue(sched.nbOfJobs() == 2);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 10);
		assertTrue(sched.getMachines().get(1).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(1).getStress() == 5);
	}
	
	@Test
	public void addOffOverflowJobsTree() {
		Job steve = new Job(3);
		Job jennifer = new Job(5);
		Job reed = new Job(6);
		Job christopher = new Job(8);
		Job camille = new Job(10);
		Job john = new Job(15);

		jobs.add(steve);
		jobs.add(jennifer);
		jobs.add(reed);
		jobs.add(christopher);
		jobs.add(camille);
		jobs.add(john);
		
		sched.addOffline(jobs);
				
		assertTrue(sched.nbOfJobs() == 6);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 6);
		assertTrue(sched.getMachines().get(1).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(1).getStress() == 8);
		assertTrue(sched.getMachines().get(2).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(2).getStress() == 10);
		assertTrue(sched.getMachines().get(3).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(3).getStress() == 15);
		assertTrue(sched.getMachines().get(4).amountOfJobs() == 2);
		assertTrue(sched.getMachines().get(4).getStress() == 8);
	}
	
	@Test
	public void addOnJobBasic() {
		Job steve = new Job(10);
		jobs.add(steve);
		
		sched.addOnline(jobs);
		assertTrue(sched.nbOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 10);
	}
	
	@Test
	public void addOnTwoJobs() {
		Job steve = new Job(10);
		Job jennifer = new Job(5);

		jobs.add(steve);
		jobs.add(jennifer);
		
		sched.addOnline(jobs);
		
		assertTrue(sched.nbOfJobs() == 2);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(0).getStress() == 10);
		assertTrue(sched.getMachines().get(1).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(1).getStress() == 5);
	}
	
	@Test
	public void addOnOverflowJobs() {
		Job steve = new Job(3);
		Job jennifer = new Job(5);
		Job reed = new Job(6);
		Job christopher = new Job(8);
		Job camille = new Job(10);
		Job john = new Job(15);

		jobs.add(steve);
		jobs.add(jennifer);
		jobs.add(reed);
		jobs.add(christopher);
		jobs.add(camille);
		jobs.add(john);
		
		sched.addOnline(jobs);
		
		assertTrue(sched.nbOfJobs() == 6);
		assertTrue(sched.getMachines().get(0).amountOfJobs() == 2);
		assertTrue(sched.getMachines().get(0).getStress() == 18);
		assertTrue(sched.getMachines().get(1).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(1).getStress() == 5);
		assertTrue(sched.getMachines().get(2).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(2).getStress() == 6);
		assertTrue(sched.getMachines().get(3).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(3).getStress() == 8);
		assertTrue(sched.getMachines().get(4).amountOfJobs() == 1);
		assertTrue(sched.getMachines().get(4).getStress() == 10);
	}
	
	@Test
	public void getMaxStress() {
		this.addOnOverflowJobs();
		assertTrue(sched.getMostStressedMachine().getStress() == 18);
	}
}
