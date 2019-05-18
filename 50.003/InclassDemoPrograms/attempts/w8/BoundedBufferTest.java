package w8;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class BoundedBufferTest {	
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;

    // Tests the BoundedBuffer is empty after calling take()
    // Put a random Integer in and take it out
    // Assert that the output of the take == input random value
    // Assert that BoundedBuffer is empty (and not full)
    @Test
    public void testTakeSomething(){
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        try {
            Integer randomvalue = new Random().nextInt();
            bb.put(Integer.valueOf(randomvalue));
            Integer output = bb.take();
            assertTrue(output.equals(Integer.valueOf(randomvalue)));
        } catch (InterruptedException unexpected) {
            assertFalse(true);
        }
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }


    // Tests that the threads block when you try to put to a full BoundedBuffer
    // Create 10 threads to put into the BoundedBuffer to make it full
    // Use timeout to 'decide' if threads are blocked and thus interrupt them so that test can end
    // Assert that threads are not alive
    @Test
    public void testPutWhenFull(){
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);

        Runnable task = new Runnable () {
            public void run() {
                try {
//                    System.out.println("Putting in Buffer");
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread[] threads = new Thread[10];

        try {
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(task);
                threads[i].start();
            }
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);

            for (int i = 0; i < 10; i++) {
                threads[i].interrupt();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].join(LOCKUP_DETECT_TIMEOUT);
                assertFalse(threads[i].isAlive()); //the thread should not be alive for some time
            }

        } catch (Exception unexpected) {    // should not happen
            assertTrue(false);
        }
    }


	@Test
	public void testIsEmptyWhenConstructued () {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	@Test
	public void testIsFullAfterPuts () throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		
		Runnable task = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};

		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread (task);
			threads[i].start();
		}

		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}

		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	@Test
	public void testTakeBlocksWhenEmpty () {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();     // nothing to take so will be waiting forever
					assertTrue(false);  // this line will never be executed
				} catch (InterruptedException success) {} //if interrupted, the exception is caught here
			}
		};
		
		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();      // terminate
			/*The timed join ensures that the test completes even if take gets stuck in some unexpected way.
			 * This test method tests several properties of takenot only that it blocks but that, when
			 * interrupted, it throws InterruptedException. This is one of the few cases in which it is
			 * appropriate to subclass Thread explicitly instead of using a Runnable in a pool: in
			 * order to test proper termination with join. The same approach can be used to test
			 * that the taker thread unblocks after an element is placed in the queue by the main thread.*/
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive()); //the taker should not be alive for some time
		} catch (Exception unexpected) {
			assertTrue(false);
		}
	}
}
