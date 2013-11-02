package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import scheduler.Scheduler;

public class TestScheduler {

	public static final int MACHINES_NUM = 5;
	
	public Scheduler sched;
	
	@Before
	public void setUp() {
		sched = new Scheduler(MACHINES_NUM);
	}
	
	@Test
	public void testInit() {
		assertTrue(sched.nbOfMachines() == 5);
	}

}
