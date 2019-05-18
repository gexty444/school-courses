package com.example.C2Iterator;


import java.util.List;

public class IteratingExamples {
    public static int Act2Iterator(List<Integer> integers) {
        int sum = 0;
        for (int i=0;i<integers.size();i++){
            sum += integers.get(i);
        }

        return sum;
    }

    public static int Act2ForEach(List<Integer> integers){
        int sum = 0;
        for (int x: integers){
            sum += x;
        }
        return sum;


    }

}
