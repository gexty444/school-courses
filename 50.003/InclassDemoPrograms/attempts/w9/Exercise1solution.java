package w9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*
 * Apply SPMD (Single Program, Multiple Data) design pattern for concurrent programming to parallelize the program which
 * approximates $\pi$ by integrating the following formula $4/(1+x^2 )$. Hint: In the SPMD design pattern, all threads
 * run the same program, operating on different data.
 */
public class Exercise1solution {
    public static void main(String[] args) throws Exception {
        int NTHREADS = 5;
        ExecutorService exec = Executors.newFixedThreadPool(NTHREADS - 1);
        // todo: complete the program by writing your code below.

        final double ratio = 1.0/NTHREADS;
        double pi = 0.0;

        final double [] results = new double[NTHREADS];         // array so that elements can be modified
        for (int i=0; i<NTHREADS; i++){
            final double a = i*ratio;
            final double b = (i+1)*ratio;

            final int finalI = i;
            Runnable task = new Runnable() {
                public void run() {
                    results[finalI] = integrate(a,b);
//                    System.out.println("results[" + finalI + "] is " + results[finalI]);
                }
            };
            exec.execute(task);
        }

        exec.shutdown();
        exec.awaitTermination(100000, TimeUnit.MILLISECONDS);        // wait for termination of the threads
        for (int i=0; i<NTHREADS; i++){
            pi += results[i];
//            System.out.println(pi);
        }

        System.out.println("Value of pi is " + pi);

    }

    public static double f(double x) {
        return 4.0 / (1 + x * x);
    }

    // the following does numerical integration using Trapezoidal rule.
    public static double integrate(double a, double b) {
        int N = 10000; // preciseness parameter
        double h = (b - a) / (N - 1); // step size
        double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

        for (int i = 1; i < N - 1; i++) {
            double x = a + h * i;
            sum += f(x);
        }

        return sum * h;
    }
}
