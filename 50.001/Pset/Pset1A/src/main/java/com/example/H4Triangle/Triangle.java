package com.example.H4Triangle;

//public class Triangle extends GeometricObject{
public class Triangle{
    double side1; double side2; double side3;

    public Triangle(){
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    public Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getArea() {
        double p = (side1 + side2 + side3) / 2;
        return (Math.sqrt(p * (p - side1) * (p - side2) * (p - side3)));

    }

    public double getPerimeter(){
        return (side1 + side2 + side3) ;
    }

    public String toString(){
        return "Triangle: side1 = " + Double.toString(side1) + " side2 = " + Double.toString(side2) + " side3 = " + Double.toString(side3);
    }
}
