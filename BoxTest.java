package GUI;

public class BoxTest {
	
public static void main(String[] args) {
// Box box = new Box();
// 
// box.setConend("���ع��� ��λ���");
// String cont = box.getContend();
// 
// System.out.println(cont);
// 
// Box2 box2 = new Box2();
// box2.setContent(100);
// Integer cont2 = box2.getContend();
// System.out.println(cont2);
	
//	Box3 box3 = new box3();
//	Student std = new Student();
//	std.setName("ȫ�浿");
//	std.setDept("ȫ�浿");
//	std.setGrade(100);
//	
//	box3.setContent(std);
//	std = box3.getContent();
//	
//	System.out.println(std.getName() + ":" + std.getGrade());
	
	
	Box4 box = new Box4();
//	box.setContent(new Student());
//	box.setContent(100);
	box.setContent("������ �⵵��");
	process(box);
	
	}
//Object�� �ϸ� ����� ������ ���ĸ�

	public static void process(Box4 box) {
//		...
//		�ڽ��� ó�����ִ� �ڵ带 ®�µ� (Student�� �����ϰ� «)
//		������ �� student�� �ƴѵ� student�� Ÿ��ĳ�����ϴ� ���� ��Ÿ�ӿ��� �߻�
//		���� �޾ƿ� �� ������ ����
		Object obj = box.getContent();
//		return�� Object
	
		if(obj instanceof Student) {
			Student std = (Student)obj;
		
		String s = std.getName();
		int n = std.getGrade();
		
//		DB���̺꿡 ����
		
		}else if(obj instanceof String) {
//			���ڿ�ó��
		}else if(obj instanceof Integer) {
//			����ó��
		}
		
	}
}
