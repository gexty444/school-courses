package w4;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RussianTest {

    // ================================
    // Black-box Testing
    // ================================
    @Test
    public void testNegative(){
        assertEquals (-8, Russian.multiply(-2,4));
    }

    @Test
    public void testNegative2(){
        assertEquals (-8, Russian.multiply(2,-4));
    }

    @Test
    public void testNegative3(){
        assertEquals (8, Russian.multiply(-2,-4));
    }

    @Test
    public void testZero(){
        assertEquals (0, Russian.multiply(0,4));
    }

    @Test
    public void testZero2(){
        assertEquals (0, Russian.multiply(2,0));
    }

    @Test
    public void testZero3(){
        assertEquals (0, Russian.multiply(0,0));
    }

    @Test
    public void testNormal(){
        assertEquals (8, Russian.multiply(2,4));
    }

    @Test
    public void testBigInput(){ //2147483647 is 2^32 - 1, max value of int
        assertEquals (Integer.MAX_VALUE*4, Russian.multiply(Integer.MAX_VALUE,4));
    }

    @Test
    public void testNegBigInput(){ //2147483647 is 2^32 - 1, max value of int
        assertEquals (-Integer.MAX_VALUE*4, Russian.multiply(Integer.MAX_VALUE,-4));
    }


    // ================================
    // White-box Testing
    // ================================

    // branch n>0 is false
    @Test
    public void testNlessthan0(){
        assertEquals (-10, Russian.multiply(2,-5));
    }

    // branch n>0 is true && n is odd
    @Test
    public void testNisOdd(){
        assertEquals (12, Russian.multiply(4,3));
    }


    // branch n>0 is true && n is even
    @Test
    public void testNisEven(){
        assertEquals (12, Russian.multiply(6,2));
    }



    // ================================
    // fault-based testing
    // ================================

    @Test
    public void testNnegative(){    // negative n input
        assertEquals (-10, Russian.multiply(5,-2));
    }

    @Test
    public void testNull1(){        // m as null
        Integer nullInteger = new Integer(null);
        assertEquals (null, Russian.multiply(nullInteger,10));
    }

    @Test
    public void testNull2(){        // n as null
        Integer nullInteger = new Integer(null);
        assertEquals (null, Russian.multiply(10, nullInteger));
    }

    @Test
    public void testBigInput1(){    // n is the max value of int
        assertEquals(Integer.MAX_VALUE*5, Russian.multiply(5, Integer.MAX_VALUE));
    }

    @Test
    public void testNegBigInput1(){     // n is negative max value of int
        assertEquals(-Integer.MAX_VALUE*5, Russian.multiply(5, -Integer.MAX_VALUE));
    }
}
