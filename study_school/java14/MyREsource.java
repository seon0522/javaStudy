package GUI;

public class MyREsource implements AutoCloseable {
	public MyREsource() {
		System.out.println("MyResource ����");
	}
	
	public int getValue() throws OutOfResourcesException {
		
		int random = (int)(Math.random()*2);
		if(random == 0) {
			throw new OutOfResourcesException("myresource : �ڿ���");
		}else {
			System.out.println("myresource : �ڿ� ���");
		}
		
		return random;
		
	}
	
	public void close() {
		System.out.println("�ڿ� �ݳ�..");
	}
}
