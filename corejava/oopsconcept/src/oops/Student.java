package oops;

public class Student {
	
	//Data : data  members: instance Variable
	int studentId;
	String studentName;
	String studentCity;
	
	//behaviour: member methods: methods : functions
	
	public void study() {
		System.out.println(studentName + " is studying");
	}
	
	public void getStudentDetails() {
		System.out.println("name is "+ studentName);
		System.out.println("Id is "+ studentId);
		System.out.println("City is "+ studentCity);
		}

	public void Kill() {
		System.out.println("***creatn const****");
	}
	
//	public void hello(int studentId, String studentName, String studentCity) {
//		this.studentId= studentId;
//		this.studentName= studentName;
//		this.studentCity= studentCity;
//	}

	
	
	
	
}
