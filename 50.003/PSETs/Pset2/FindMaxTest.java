

import static org.junit.Assert.*;

import org.junit.Test;

public class FindMaxTest { 
   @Test
   public void TestFailure () { 
	   assertTrue(FindMax.max(new int[] {1,2,3})==3);
   }
   @Test
   public void TestPass () { 
	   assertTrue(FindMax.max(new int[] {4,1,2,3})==4);
   }
   @Test
   public void TestError () { 
	   assertTrue(FindMax.max(new int[] {})==0);
   }

}
