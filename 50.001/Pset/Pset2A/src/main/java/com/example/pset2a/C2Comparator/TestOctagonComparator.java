package com.example.pset2a.C2Comparator;

import java.util.ArrayList;
import java.util.Collections;

public class TestOctagonComparator {
    public static void main(String[] args) {
        ArrayList<Octagon> l = new ArrayList<Octagon>();
        l.add(new  Octagon(2));
        l.add(new  Octagon(3));
        l.add(new  Octagon(1));
        Collections.sort(l,  new  OctagonComparator());
        for (Octagon o:l){
            System.out.println(o.getSide());
        }

    }
}
