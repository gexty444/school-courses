package w6;

import java.util.concurrent.atomic.AtomicInteger;

public class ExperimentFix2 {
    private static int MY_INT = 0;
    private static final Object lock = new Object();
//    private static AtomicInteger MY_INT = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException
    {
        new ChangeListener().start();
        System.out.println("Waiting two seconds so the JIT will probably optimize ChangeListener");
        Thread.sleep(2000);

        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        public void run() {
            int local_value;
            synchronized (lock) {
                local_value = MY_INT;
            }
            while (local_value < 5) {
                synchronized (lock) {
                    if (local_value != MY_INT) {
                        System.out.println("Got Change for MY_INT : " + MY_INT);
                        local_value = MY_INT;
                    }
                }
            }
        }
    }

    static class ChangeMaker extends Thread{
        public void run() {
            int local_value;
            synchronized (lock) {
                local_value = MY_INT;
            }
            while (MY_INT < 5) {
                synchronized (lock) {
                    System.out.println("Incrementing MY_INT to " + (local_value + 1));
                    MY_INT = ++local_value;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
