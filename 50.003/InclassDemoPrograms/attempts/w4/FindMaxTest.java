package w4;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FindMaxTest {
    @Test
    public void testMaxPass(){
        int[] array = {5,6,17,8,2};
        int maxVal = FindMax.max(array);
        assertEquals(17,maxVal);
    }

    @Test
    public void testMaxFail(){
        int[] array = {1,2,3,5,6};
        int maxVal = FindMax.max(array);
        assertEquals(6, maxVal);
    }


    @Test
    public void testMaxError(){
        int[] array = {};
        try{
            int maxVal = FindMax.max(array);
            fail("Error expected did not occur!");
        }
        catch(Exception e){
            System.out.println("Error Message:");
            System.out.println(e.toString());
        }
    }
}
