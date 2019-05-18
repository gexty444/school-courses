package com.cheung.lib001;

public class TstInterface {
    public static void main(String[] args) {
        Class_2 x = new Class_2();
        Interface_1_1 a = x;
        Interface_1_2 b = x;
        Interface_1 c = x;
        Interface_2_1 d = x;

        System.out.println(a.p1());


    }
}

interface  Interface_1_1 {
    int p1();
}

interface  Interface_1_2 {
    int p2();

}

interface Interface_1 extends Interface_1_1, Interface_1_2 {
    int p3();

}

interface Interface_2_1 {
    int q1();
}

abstract class Class_1 implements  Interface_1 {
}

class Class_2 extends  Class_1 implements Interface_2_1 {
    @Override
    public int p1() {return 0;}
    @Override
    public int p2() {return 0;}
    @Override
    public int p3() {return 0;}
    @Override
    public int q1() {return 0;}

}

