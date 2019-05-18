package w5;

import java.util.concurrent.atomic.AtomicInteger;

public class LockStaticVariablesFixed {
    public static AtomicInteger saving = new AtomicInteger(5000);
    public static AtomicInteger cash = new AtomicInteger(0);

    public static void main(String args[]){
        int numberofThreads = 10000;
        WDfixed[] threads = new WDfixed[numberofThreads];

        for (int i = 0; i < numberofThreads; i++) {
            threads[i] = new WDfixed();
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberofThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("some thread is not finished");
        }

        System.out.print("The result is: " + LockStaticVariables.cash);
    }
}

class WDfixed extends Thread {
    public void run () {
        synchronized (LockStaticVariables.saving){
            if (LockStaticVariables.saving.get() >= 1000) {
                System.out.println("I am doing something.");
                LockStaticVariables.saving.set(LockStaticVariables.saving.get() - 1000);
                LockStaticVariables.cash.set(LockStaticVariables.cash.get() + 1000);
            }
        }
    }
}
