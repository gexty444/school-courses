package com.example.pset2a.C2Comparator;

import java.util.Comparator;

public class OctagonComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Octagon x1 = (Octagon) o1;
        Octagon x2 = (Octagon) o2;

        if (x1.getSide() == x2.getSide()){
            return 0;
        }
        else if (x1.getSide() > x2.getSide()){
            return 1;
        }
        else {return -1;}
    }
}
