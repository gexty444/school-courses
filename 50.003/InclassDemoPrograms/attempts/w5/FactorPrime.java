package w5;
import java.math.BigInteger;

public class FactorPrime {	
	//Precondition: n is a semi-prime number.
	//Postcondition: the returned value is a prime factor of n;
	public static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false); //if this is reached, an error occurs.
		return null;
	}



    public static void main (String[] args) {

        BigInteger n = new BigInteger("1127451830576035879");

        Thread2 thread2 = new Thread2(n);
        Thread1 thread1 = new Thread1(n);
        thread1.start();
        thread2.start();

        System.out.println("Value of Expression: " + thread1.getValue());

    }


	static class Thread1 extends Thread{

	    private BigInteger n, value;
        private BigInteger i = new BigInteger("2");
	    private BigInteger zero = new BigInteger("0");

	    Thread1(BigInteger n){
	        this.n = n;
        }

        public void run () {
            while (i.compareTo(n) < 0) {
                if (n.remainder(i).compareTo(zero) == 0) {
                    value = i;
                    break;
                }
                i = i.add(new BigInteger("2"));
            }
        }

        public BigInteger getValue() {
            return value;
        }



    }

    static class Thread2 extends Thread{

        private BigInteger n, value;
        private BigInteger i = new BigInteger("3");
        private BigInteger zero = new BigInteger("0");

        Thread2(BigInteger n){
            this.n = n;
        }

        public void run () {
            while (i.compareTo(n) < 0) {
                if (n.remainder(i).compareTo(zero) == 0) {
                    value = i;
                    break;
                }
                i = i.add(new BigInteger("2"));
            }
        }

        public BigInteger getValue() {
            return value;
        }



    }
}
