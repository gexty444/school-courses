//package Week4;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)

public class QuickSortParameterizedTest {
	public int[] expected, actual;
    
	public QuickSortParameterizedTest (int[] expected, int[] actual) { 
    	this.expected = expected; 
    	this.actual = actual; 
    }

   @Parameters public static Collection<Object[]> parameters() {
        return Arrays.asList (new Object [][] 
				{
				 {new int[]{0,1,2}, new int[]{0, 2, 1}}, 
				 {new int[]{0,3,5}, new int[]{5, 3, 0}}
				}); 
   }

   @Test public void additionTest() { 
	   new QuickSort().sort(actual);
	   assertTrue(testArrayEqual(expected, actual)); 
   }
   
   private boolean testArrayEqual (int[] one, int[] two) {
	   if (one == null && two == null) {
		   return true;
	   }
	   
	   if (one == null || two == null) {
		   return false;
	   }
	   
	   if (one.length != two.length) {
		   return false;
	   }
	   
	   for (int i = 0; i < one.length; i++) {
		   if (one[i] != two[i]) {
			   return false;
		   }
	   }
	   
	   return true;
   }
}
