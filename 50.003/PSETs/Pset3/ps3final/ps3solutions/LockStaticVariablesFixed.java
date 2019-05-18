package Week9;

public class LockStaticVariablesFixed {
	public static int saving = 5000;
	public static int cash = 0;
	
	public static void main(String args[]){   	
		int numberofThreads = 10000;
		WDFixed[] threads = new WDFixed[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WDFixed();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is: " + LockStaticVariablesFixed.cash);
	}
}

class WDFixed extends Thread {	
	public void run () {
		synchronized (LockStaticVariablesFixed.class) {
			if (LockStaticVariablesFixed.saving >= 1000) {
				System.out.println("I am doing something.");	
	 			LockStaticVariablesFixed.saving = LockStaticVariablesFixed.saving - 1000;
				LockStaticVariablesFixed.cash = LockStaticVariablesFixed.cash + 1000;				
			}
		}		
	}	
}

