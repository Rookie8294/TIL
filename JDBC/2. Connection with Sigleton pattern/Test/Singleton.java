package day0814;

public class Singleton {
	private static Singleton single;
	private Singleton() {
		System.out.println("생성자");
	}//Singleton
	
	public static Singleton getInstance() {
		if(single == null ) {
			single = new Singleton();
		}		
		return single;
	}//getInstance
}//class


