package com.example.pset2a.C6permute;

import java.util.ArrayList;

public class TestPermutation {
    public static void main(String[] args) {
        ArrayList<String> v;

        Permutation p = new Permutation("11a");
        p.permute();
        v = p.getA();
        System.out.println(v);
        System.out.println("hat,hta,aht,ath,tha,tah");
    }
}
