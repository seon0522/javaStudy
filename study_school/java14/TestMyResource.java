package GUI;

public class TestMyResource {
	public static void main(String[] args) {
		
//		test1();
		test2();
	}
	
//	Autocolsable�� implements �ϸ� finally�� �� �ص� close�� ��
	
	public static void test1(){
	MyREsource r = new MyREsource();
		
		try {
			System.out.println(r.getValue());
			System.out.println("Test: ����ó��");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Test: ���� ����");
		}finally {
			r.close();
		}
	}
	
	public static void test2(){
		try (MyREsource r = new MyREsource()){
			System.out.println(r.getValue());
			System.out.println("����ó��");
			
		} catch (OutOfResourcesException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
