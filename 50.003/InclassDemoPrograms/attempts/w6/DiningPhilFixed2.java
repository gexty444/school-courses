package w6;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilFixed2 {
    // second fix: use Try Lock
    // deadlock happens when all philo are holding on to a fork on the
    // same hand and trying to get a fork on their other hand.
    // each fork has a lock which acts as a check for the availability
    // of the fork. A timeout is implemented for the trylock.

    private static int N = 5;

    public static void main (String[] args) throws Exception {
        final ReentrantLock reentrantLock = new ReentrantLock();

        Philosopher2[] phils = new Philosopher2[N];
        Fork2[] forks = new Fork2[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork2(i);
        }

        for (int i = 0; i < N; i++) {
            phils[i] = new Philosopher2 (i, forks[i], forks[(i+N-1)%N]);
            phils[i].start();
        }
    }
}
class Philosopher2 extends Thread {
    private final int index;
    private final Fork2 left;
    private final Fork2 right;
    boolean rightFlag;
    boolean leftFlag;

    public Philosopher2 (int index, Fork2 left, Fork2 right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public void run() {
        Random randomGenerator = new Random();
        try {
            while (true) {
                Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
                System.out.println("Phil " + index + " finishes thinking.");

                // try lock left fork
                leftFlag = left.reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);

                if (!leftFlag){
                    break;
                }

                left.pickup();
                System.out.println("Phil " + index + " picks up left fork.");

//                Thread.sleep(randomGenerator.nextInt(100));   // attempt to force deadlock

                // trylock on right fork
                rightFlag = right.reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
                if (!rightFlag) {
                    left.putdown();     // puts down the left fork if you can't get the right fork
                    break;
                }
                right.pickup();
                System.out.println("Phil " + index + " picks up right fork.");

                Thread.sleep(randomGenerator.nextInt(100)); //eating
                System.out.println("Phil " + index + " finishes eating.");
                left.reentrantLock.unlock();
                left.putdown();
                System.out.println("Phil " + index + " puts down left fork.");
                right.reentrantLock.unlock();
                right.putdown();
                System.out.println("Phil " + index + " puts down right fork.");
            }
        } catch (InterruptedException e) {
            System.out.println("Don't disturb me while I am sleeping, well, thinking.");
        }
    }
}

class Fork2 {
    private final int index;
    private boolean isAvailable = true;

    public ReentrantLock reentrantLock = new ReentrantLock();

    public Fork2 (int index) {
        this.index = index;
    }

    public void pickup () throws InterruptedException {
        isAvailable = false;
    }

    public void putdown() throws InterruptedException {
        isAvailable = true;
    }

    public String toString () {
        if (isAvailable) {
            return "Fork " + index + " is available.";
        }
        else {
            return "Fork " + index + " is NOT available.";
        }
    }
}