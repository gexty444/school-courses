package Week9;

import org.junit.Test;

public class DLExampleTest {
	
	@Test
	public void test1 () {
		Dispatcher dis = new Dispatcher();
		Taxi taxi = new Taxi(dis);
		dis.addTaxi(taxi);
		
		Thread thread1 = new Thread () {
			public void run () {
				dis.getImage();
			}		
		};
		thread1.start();
		
		Thread thread2 = new Thread () {
			public void run () {
				taxi.setLocation(new Point());
			}		
		};	
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		}
		catch (Exception e) {
			System.out.println("some thread is not finished.");
		}
	}
}
