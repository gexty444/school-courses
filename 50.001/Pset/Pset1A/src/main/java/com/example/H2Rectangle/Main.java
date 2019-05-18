package com.example.H2Rectangle;

public class Main {
    public static void main(String[] args) {
        double x1 = 2.0; double y1 = 1.0;
        double height1 = 1.0; double width1 = 1.0;

        MyRectangle2D r1 = new MyRectangle2D(1, 1, 6, 4);
        MyRectangle2D r2 = new MyRectangle2D(x1,y1,width1,height1);
        System.out.printf("Area is %.3f\n", r1.getArea());
        System.out.printf("Perimeter is %.3f\n", r1.getPerimeter());
        System.out.println(r1.contains(4,3));
        if (r1.contains(r2)) {
            System.out.println("contain"); }
        else if (r1.overlaps(r2)) {
            System.out.println("overlaps");
        }
        else {
            System.out.println("no overlap");
        }
    }
}
