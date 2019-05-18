package com.example.javatest;

import java.util.Random;

public class DiningPhilFixed1 {
    // first fix: apply a global ordering
    // deadlock occurs when each philo takes left and no right left for anyone to take
    // hence, let the last philo take from right first and then left

    private static int N = 5;

    public static void main (String[] args) throws Exception {
        Philosopher1[] phils = new Philosopher1[N];
        Fork1[] forks = new Fork1[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork1(i);
        }

        for (int i = 0; i < N; i++) {
            phils[i] = new Philosopher1 (i, forks[i], forks[(i+N-1)%N]);
            phils[i].start();
        }
    }
}
class Philosopher1 extends Thread {
    private final int index;
    private final Fork1 left;
    private final Fork1 right;

    public Philosopher1 (int index, Fork1 left, Fork1 right) {
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



                if (index != 0){
                    left.pickup();
                    System.out.println("Phil " + index + " picks up left fork.");
                    right.pickup();
                    System.out.println("Phil " + index + " picks up right fork.");
                }

                // let the last philosopher take from right first
                else{
                    right.pickup();
                    System.out.println("Phil " + index + " picks up right fork.");
                    left.pickup();
                    System.out.println("Phil " + index + " picks up left fork.");
                }



                Thread.sleep(randomGenerator.nextInt(100)); //eating
                System.out.println("Phil " + index + " finishes eating.");
                left.putdown();
                System.out.println("Phil " + index + " puts down left fork.");
                right.putdown();
                System.out.println("Phil " + index + " puts down right fork.");
            }
        } catch (InterruptedException e) {
            System.out.println("Don't disturb me while I am sleeping, well, thinking.");
        }
    }
}

class Fork1 {
    private final int index;
    private boolean isAvailable = true;

    public Fork1 (int index) {
        this.index = index;
    }

    public synchronized void pickup () throws InterruptedException {
        while (!isAvailable) {
            wait();
        }

        isAvailable = false;
        notifyAll();
    }

    public synchronized void putdown() throws InterruptedException {
        while (isAvailable) {
            wait();
        }

        isAvailable = true;
        notifyAll();
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