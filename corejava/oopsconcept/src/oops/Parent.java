package oops;

class A{}


class B extends A{}


public class Parent {
	
	public A m1() {
		System.out.println("I am method one of parent");
		return new A();
	}

}



class Child extends Parent{
	
	public B m1() {
		System.out.println("i am method from child");
		return new B();
	}
	
}