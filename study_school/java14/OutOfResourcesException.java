package GUI;

public class OutOfResourcesException extends Exception {
	public OutOfResourcesException() {
		System.out.println("OutOfResourcesExcepion ��ü ����");
	}
	
	public OutOfResourcesException(String s) {
		super(s);
		System.out.println("OutOfResourcesExcepion ��ü ����");
	}
}
