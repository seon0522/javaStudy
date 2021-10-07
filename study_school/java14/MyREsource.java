package GUI;

public class MyREsource implements AutoCloseable {
	public MyREsource() {
		System.out.println("MyResource 생성");
	}
	
	public int getValue() throws OutOfResourcesException {
		
		int random = (int)(Math.random()*2);
		if(random == 0) {
			throw new OutOfResourcesException("myresource : 자원고갈");
		}else {
			System.out.println("myresource : 자원 충분");
		}
		
		return random;
		
	}
	
	public void close() {
		System.out.println("자원 반납..");
	}
}
