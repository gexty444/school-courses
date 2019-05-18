package com.example.H4Triangle;

public class TestTriangle {
    public static void main(String[] args) {
        Triangle myTri = new Triangle();
//        myTri.setColor("red");
//        myTri.setFilled(true);
        System.out.println(myTri);
//        System.out.println(myTri.isFilled());

        Triangle myTri2 = new Triangle(2.0, 4.0, 5.5);
        System.out.println(myTri2);
        System.out.println("area : " + myTri2.getArea() + " perimeter: " + myTri2.getPerimeter());
        System.out.println("area check " + Boolean.toString(myTri2.getArea() == 3.0714155938264036 ));

    }
}
