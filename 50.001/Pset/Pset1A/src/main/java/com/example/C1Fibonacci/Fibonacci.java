package com.example.C1Fibonacci;

public class Fibonacci {
    public static String fibonacci(int n) {
        String ans;
        int f1 = 0;
        int f2 = 1;

        if (n == 1) {
            return "0";
        } else if (n == 2) {
            return "1";
        } else {
            ans = "0,1";
            for (int i = 3; i <= n; i++) {
                int sum = f1 + f2;
                ans += ","+ Integer.toString(sum);
                f1 = f2;
                f2 = sum;
            }
            return ans;

        }
    }
}
