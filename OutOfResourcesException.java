package GUI;

public class OutOfResourcesException extends Exception {
	public OutOfResourcesException() {
		System.out.println("OutOfResourcesExcepion 梓端 持失");
	}
	
	public OutOfResourcesException(String s) {
		super(s);
		System.out.println("OutOfResourcesExcepion 梓端 持失");
	}
}
