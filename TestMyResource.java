package GUI;

public class TestMyResource {
	public static void main(String[] args) {
		
//		test1();
		test2();
	}
	
//	Autocolsable를 implements 하면 finally를 안 해도 close를 붊
	
	public static void test1(){
	MyREsource r = new MyREsource();
		
		try {
			System.out.println(r.getValue());
			System.out.println("Test: 정상처리");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Test: 값이 없대");
		}finally {
			r.close();
		}
	}
	
	public static void test2(){
		try (MyREsource r = new MyREsource()){
			System.out.println(r.getValue());
			System.out.println("정상처리");
			
		} catch (OutOfResourcesException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
