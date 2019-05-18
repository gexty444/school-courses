package Week11;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class AddRemoveTest {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;
    protected final Map<Integer, Integer> map;
    protected final int nTrials, nPairs;

    public static void main(String[] args) throws Exception {
        new AddRemoveTest(10, 10, 100000).test(); // sample parameters
        pool.shutdown();
    }
    
    public AddRemoveTest(int capacity, int npairs, int ntrials) {
        this.map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new AddWorker());
                pool.execute(new RemoveWorker());
            }
            barrier.await(); // wait for all threads to be ready
            barrier.await(); // wait for all threads to finish
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class AddWorker implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                barrier.await();
                for (int i = nTrials; i > 0; --i) {                	
                    map.put(seed, seed);
                    seed = xorShift(seed);
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    class RemoveWorker implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                	Object[] keys = map.keySet().toArray();
                	if (keys.length > 0) {
                    	map.remove(keys[Math.abs(seed)%keys.length]);                 		
                	}
                    seed = xorShift(seed);
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}