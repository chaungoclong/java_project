package test;

public class Child extends Parent {
	public static void main(String[] args) {
		Parent p = new Parent();
		p.show();
		
		Child c = new Child();
		c.show();
	}
}
