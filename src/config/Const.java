package config;

public interface Const {
	// =================================== PATH ================================ //
	public static final String WEB_PATH = "/PROJECT/";
	public static final String VIEW_PATH = "/views/";
	// =================================== PATH ================================ //
	
	// =================================== DATABASE ================================ //
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/java_project";
	public static final String USERNAME = "long";
	public static final String PASSWORD = "tnt";
	// =================================== DATABASE ================================ //
	
	public static void main(String[] args) {
		System.out.println(Const.VIEW_PATH);
	}
}
