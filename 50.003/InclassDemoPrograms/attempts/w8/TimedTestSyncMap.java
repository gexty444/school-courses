package w8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimedTestSyncMap extends PutRemoveTest {
    private BarrierTimer2 timer = new BarrierTimer2();

    public TimedTestSyncMap(int cap, int pairs, int trials){
        super(cap, pairs, trials);
        barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    public void test(){
        try{
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new PutRemoveTest.ProducerMap());
                pool.execute(new PutRemoveTest.ConsumerMap());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
            System.out.print("Throughput: " + nsPerItem + " ns/item");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100; // trials per thread
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                TimedTestSyncMap t = new TimedTestSyncMap(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        PutRemoveTest.pool.shutdown();
    }
}

class BarrierTimer2 implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}

class PutRemoveTest {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;
    protected final Map<Integer, Integer> map;
    protected final int nTrials, nPairs;

    public PutRemoveTest(int capacity, int npairs, int ntrials){
        this.map = Collections.synchronizedMap(new HashMap<Integer, Integer>());
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    public static void main(String[] args) throws Exception {
        new PutRemoveTest(10, 10, 100).test(); // sample parameters
        pool.shutdown();
    }

    void test(){
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new ProducerMap());
                pool.execute(new ConsumerMap());
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

    class ProducerMap implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    map.put(seed, seed);
                    seed = xorShift(seed);  // reshuffle
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class ConsumerMap implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    Object[] keys = map.keySet().toArray();
                    if (keys.length > 0){
                        // % length so that won't get out of array
                        map.remove(keys[Math.abs(seed % keys.length)]);
                    }
                    seed = xorShift(seed); // reshuffle
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}