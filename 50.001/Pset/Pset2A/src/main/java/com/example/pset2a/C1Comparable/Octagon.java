package com.example.pset2a.C1Comparable;

public class Octagon implements Comparable<Octagon>{
    private double side;

    public Octagon(double side){
        this.side = side;
    }

    public double getSide(){
        return side;
    }

    @Override
    public int compareTo(Octagon octagon) {
        if (side == octagon.side){
            return 0;
        }

        else if (side > octagon.side){
            return 1;
        }

        else {return -1;}
    }
}





