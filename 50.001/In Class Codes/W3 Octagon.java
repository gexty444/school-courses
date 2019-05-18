
public class Octagon {
    private double side;


    public static void main(String[] args) {
        System.out.println( (new Octagon(5)).getSide());
    }
    public Octagon(double side){
        this.side = side;
    }
    public double getSide() {
        return side;
    }
}
