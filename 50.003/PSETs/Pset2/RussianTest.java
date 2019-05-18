package Week4;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RussianTest {	
	@Test
	public void testBlack () {
		assertTrue(Russian.multiply(5, 7)==35);
	}

	@Test
	public void testWhiteBranchCoverage1 () {
		assertTrue(Russian.multiply(237, 5968)==1414426);
	}

	@Test
	public void testWhiteBranchCoverage2 () {
		assertTrue(Russian.multiply(237, 0)==0);
	}
	
	@Test
	public void testFaultBasedTesting1 () {
		assertTrue(Russian.multiply(-1, -1)==1);
	}
	
	@Test
	public void testFaultBasedTesting2 () {
		assertTrue(Russian.multiply(Integer.MAX_VALUE, 2) > 0);
	}
}
