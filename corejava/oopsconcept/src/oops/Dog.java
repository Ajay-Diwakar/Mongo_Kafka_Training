package oops;

public class Dog extends Animal{
	
	int x=4;
	
	public Dog() {
		super();
		System.out.println("works");
	}
	
	public void speak() {
		System.out.println(this.x);
	}

}
