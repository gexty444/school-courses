package w10.evosuite;

public class Harder {

    public void test(double x, double y) {
        if (x == 0) {
            if (y == 0){
                System.out.println("check2");
            }
        }
        System.out.println("timetaken: "+ System.currentTimeMillis());
    }
}
