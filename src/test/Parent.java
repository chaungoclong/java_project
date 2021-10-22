package test;

public class Parent {
	protected String str;
	
	public Parent() {
		this.str = this.getClass().getSimpleName().toString() + "Hello";
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void show() {
		System.out.println(this.str);
	}
}
