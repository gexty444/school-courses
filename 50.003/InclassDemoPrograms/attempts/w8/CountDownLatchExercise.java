package w8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExercise {

    private static final int numberOfThreads = 15;

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(7); // 7 "F"s only
        String [] grades = {"F", "A", "B","C","A","F","B","C","F","A","A","A","A","B",
                "A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F",
                "A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F",
                "A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F"};

//        String[] grades = {"F","A","A","B","B","B","F","F","F","F"};

        Check[] checks = new Check[numberOfThreads];

        int segment = grades.length / numberOfThreads;

        for (int i=0; i<numberOfThreads; i++){
            if (i == 0){
                checks[i] = new Check(i, 0, segment, grades, latch);
            }
            else if (i < numberOfThreads-1){
                checks[i] = new Check(i, segment*i, segment*(i+1), grades, latch);
            }
            else{   // last thread takes all
                checks[i] = new Check(i, segment*i, grades.length, grades, latch);
            }
            checks[i].start();
        }

        try{
            latch.await();  // main thread is waiting on CountDownLatch to finish
            for (Check thread: checks){
                thread.interrupt();
                System.out.println("Interrupted a thread");
            }
            Thread.sleep(1000);
            System.out.println("7 Fs have been found");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<numberOfThreads; i++){
            try{
                checks[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Check extends Thread{
    private final int name;
    private int start;
    private int end;
    private final String[] grades;
    private final CountDownLatch latch;

    public Check(int name, int start, int end, String[] grades, CountDownLatch latch){
        this.name = name;
        this.start = start;
        this.end = end;
        this.grades = grades;
        this.latch = latch;
    }

    @Override
    public void run() {
        if (latch.getCount() == 0){
            return;
        }
        for (int i=start; i<end; i++){
            if (grades[i].equals("F")){
                System.out.println("Thread " + name + " found a F");
                latch.countDown();
                System.out.println("Latch is now reduced to " + latch.getCount());
            }
            if (isInterrupted()){
                break;
            }
        }
    }
}
