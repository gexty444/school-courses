package com.example.pset2a;

import java.util.ArrayList;


public class MyClass {
}
//    public int multiply(int a, int b){
//        // eg 4x4 = 3x4+4= 4x3+4 = 3x3+4+3
//        if (a!=0){
//            return (b + multiply(a-1,b));
//        }
//        else if (b>a){
//            return (multiply(b,a));
//        }
//        else{
//            return 0;
//        }
//    }
//
//    public static void main(String[] args) {
//        MyClass c = new MyClass();
//        int x = c.multiply(5,-3);
//        System.out.println(x);
//
//    }
//}
//
//
//
//    private String courseName;
//    private int numberOfStudents;
//    private ArrayList<String> students;
//
//    public Course(String courseName){
//        this.courseName = courseName;
//        this.students = new ArrayList<String>();
//        this.numberOfStudents = 0;
//    }
//
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public int getNumberOfStudents() {
//        return numberOfStudents;
//    }
//
//    public ArrayList<String> getStudents() {
//        return students;
//    }
//
//
//    public void addStudent(String student){
//        numberOfStudents++;
//        students.add(student);
//    }
//
//    public void dropStudent(String student){
//        numberOfStudents--;
//        int ind = students.indexOf(student);
//        students.remove(ind);
//    }
//
//}
////(a)	The "students" instance variable is an ArrayList that stores student names as strings.
////        When the constructor is called, "students" should be instantiated and the "numberOfStudents"
////        variable should be initialized to zero.
////        (b)	Add the getter methods for all the instance variables.
////        (c)	Complete addStudent(). A student name as a string is passed to it and this method
////        adds it to the end of the "students" ArrayList. You are reminded that "numberOfStudents"
////        should be incremented.
////        (d)	Complete dropStudent(). A student name as a string is passed to it and this method
////        removes this particular student name from the "students" ArrayList.
