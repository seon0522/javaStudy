package GUI;

public class CenericTest {
	public static void main(String[] args) {
//		<>�ȿ� ��������� Object��
//		�ٵ� �ڹ� 8���ĺ��ʹ� �ڿ� ������ �� ���൵ ��.
		GenericBox<String> box1 = new GenericBox<>();
		
		box1.setContent("���ع���");
//		box1.setContent(10); //����
		String s = box1.getContent();
		System.out.println(s);
		
		
		GenericBox<Integer> box2 = new GenericBox<Integer>();
		box2.setContent(100);
		int n = box2.getContent();
//		box2.getContent("���ع���"); //����
		
		GenericBox<Student> box3 = new GenericBox<Student>();
		box3.setContent(new Student());
		Student std = box3.getContent();
//		box3.setContent("���ع���"); //����
//		box3.setContent(100); //����
		
	}
}
