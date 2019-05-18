package w10.evosuite;

public class Easier {

    public void test(double x, double y) {
        if (x > 0) {
            if (y > 0){
                System.out.println("check");
            }
        }
        System.out.println("time: "+ System.currentTimeMillis());
    }
}