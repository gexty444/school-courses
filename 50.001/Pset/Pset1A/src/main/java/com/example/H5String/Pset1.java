package com.example.H5String;

import java.util.Arrays;

public class Pset1 {
    public static boolean isAllCharacterUnique(String sIn) {
        if (sIn.length() > 128){
            return false;
        }
        for (char i: sIn.toCharArray()){
            if (sIn.indexOf(i) != sIn.lastIndexOf(i)){
                return false;
            }
        }
        return true;

    }

    public static boolean isPermutation(String sIn1, String sIn2) {
        if (sIn1.length() != sIn2.length()){
            return false;
        }
        char [] ls1 = sIn1.toCharArray();
        char [] ls2 = sIn2.toCharArray();

        Arrays.sort(ls1);
        Arrays.sort(ls2);

        return (Arrays.equals(ls1,ls2));

    }
}
