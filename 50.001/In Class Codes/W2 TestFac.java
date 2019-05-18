
public class TestFac {

	public static void main(String[] args) {
		new Faculty();

	}

}


class Person {
	Person (){
		System.out.println("Person");
		
	}
}

class Employee extends Person {
	Employee(){
		System.out.println("Employee");
	}
	
}

class Faculty extends Employee {
	Faculty() {
		System.out.println("Faculty");
		
	}
	

}

