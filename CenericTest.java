package GUI;

public class CenericTest {
	public static void main(String[] args) {
//		<>안에 비어있으면 Object임
//		근데 자바 8이후부터는 뒤에 생성때 안 써줘도 됨.
		GenericBox<String> box1 = new GenericBox<>();
		
		box1.setContent("동해물과");
//		box1.setContent(10); //오류
		String s = box1.getContent();
		System.out.println(s);
		
		
		GenericBox<Integer> box2 = new GenericBox<Integer>();
		box2.setContent(100);
		int n = box2.getContent();
//		box2.getContent("동해물과"); //오류
		
		GenericBox<Student> box3 = new GenericBox<Student>();
		box3.setContent(new Student());
		Student std = box3.getContent();
//		box3.setContent("동해물과"); //오류
//		box3.setContent(100); //오류
		
	}
}
