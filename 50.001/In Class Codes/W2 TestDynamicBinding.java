
public class TestDynamicBinding {
	public static void main(String[] args) {
		printInfo(new GraduateStudent());
		printInfo(new Student());
		printInfo(new Person());
		printInfo(new Object());
		
		
	}
	
	public static void printInfo(Object x){
		System.out.println(x.toString());
	}

}

class Person extends Object {
	@Override
	public String toString() {
		return "Person";
	}
}

class Student extends Person {
	@Override
	public String toString() {
		return "Student";
	}
}

class GraduateStudent extends Student {

}

