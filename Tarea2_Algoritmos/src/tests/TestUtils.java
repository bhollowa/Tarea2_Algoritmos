package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utils.SchedUtil;

public class TestUtils {

	@Test
	public void maxInt() {
		int a = SchedUtil.getMaxBinaryInt(5, 3);
		
		assertTrue(a == 32767);
	}
	
	@Test
	public void nbOfOnes() {
		String a = "111010101010110101010101asdbsbs123";
		
		assertTrue(SchedUtil.nbOfOnes(a) == 15);
	}
	
	@Test
	public void addPrefix() {
		String a = "11111111";
		
		assertTrue(SchedUtil.addPrefix(a, 16).equals("0000000011111111"));
	}
	
	@Test
	public void stringSum() {
		ArrayList<String> a = new ArrayList<String>();
		
		a.add("0100");
		a.add("1010");
		a.add("0001");

		assertTrue(SchedUtil.validConfig(a));
		
		ArrayList<String> b = new ArrayList<String>();
		
		b.add("0100");
		b.add("1011");
		b.add("0001");

		assertFalse(SchedUtil.validConfig(b));
	}

}