// overriding vs hiding in Java

public class OverrideHideTest {
    public static void main(String[] args) {
    	GeometricObject f = new Circle();
        f.instanceMethod();
        f.classMethod();
        
    }
}

class GeometricObject {
    public static void classMethod() {
        System.out.println("classMethod() in GeometricObject");
    }

    public void instanceMethod() {
        System.out.println("instanceMethod() in GeometricObject");
    }
}

class Circle extends GeometricObject {
	
    public static void classMethod() {
        System.out.println("classMethod() in Circle");
    }

    public void instanceMethod() {
        System.out.println("instanceMethod() in Circle");
    }
}
 
