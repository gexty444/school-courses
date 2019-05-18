package com.example.pset2a.C5Palindrome;

import java.util.Arrays;

public class Palindrome {

    public static boolean isPalindrome (char[] S) {
        if (S.length == 1 || S.length == 0){
            return true;
        }
        else {
            int lastindex = S.length - 1;
            if (S[0] == S[lastindex]){
                S = Arrays.copyOfRange(S,1,lastindex);
                return isPalindrome(S);
            }
            else {return false;}
        }

    }


//
//    public String check (String s) {
//        if (palindrome(s) == false) {
//            return (s + " is not a palindrome.");
//        } else{
//            return (s + " is a palindrome.");
//        }
//    }

//    public boolean palindrome(String s){
//        char[] charls = s.toCharArray();
//
//        if (charls.length == 1){
//            return true;
//        }
//
//        else {
//            int lastindex = charls.length - 1;
//            if (charls[0] == charls[lastindex]){
//                charls = Arrays.copyOfRange(charls,1,lastindex);
//                String outstr = String.valueOf(charls);
//                return palindrome(outstr);
//            }
//
//            else {return false;}
//
//        }
//
//    }
}
