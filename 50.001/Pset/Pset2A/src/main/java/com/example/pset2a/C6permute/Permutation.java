package com.example.pset2a.C6permute;

import java.util.ArrayList;

public class Permutation {
    private final String in;
    private ArrayList<String> a = new ArrayList<String>();

    Permutation(final String str){
        in = str;
    }


    public void permute(){
        permute2("",in);
    }

    private void permute2(String in1, String in2) {
        int n = in2.length();
        if (n==0){a.add(in1);}
        else{
            for (int i=0; i<n; i++){
                String str = in2.substring(0,i) + in2.substring(i+1, in2.length());
                permute2(in1+in2.charAt(i),str);
            }
        }
    }


    public ArrayList<String> getA() {return a; }

}

