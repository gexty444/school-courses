package com.example.H3LinearEqn;

public class LinearEquation {
    private double a; private double b; private double c;
    private double d; private double e; private double f;

    public LinearEquation(double a, double b, double c, double d, double e, double f){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public boolean isSolvable(){
        if ((a*d-b*c)!=0){return true;}
        else {return false;}
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }

    public double getY(){
        return ((f-(c*e)/a)/(d-(b*c)/a));
    }

    public double getX(){
        return ((e/a) - (b/a)*getY());
    }

}
