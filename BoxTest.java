package GUI;

public class BoxTest {
	
public static void main(String[] args) {
// Box box = new Box();
// 
// box.setConend("동해물과 백두산이");
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
//	std.setName("홍길동");
//	std.setDept("홍길동");
//	std.setGrade(100);
//	
//	box3.setContent(std);
//	std = box3.getContent();
//	
//	System.out.println(std.getName() + ":" + std.getGrade());
	
	
	Box4 box = new Box4();
//	box.setContent(new Student());
//	box.setContent(100);
	box.setContent("마르고 닳도록");
	process(box);
	
	}
//Object로 하면 생기는 문제가 뭐냐면

	public static void process(Box4 box) {
//		...
//		박스를 처리해주는 코드를 짰는데 (Student로 가정하고 짬)
//		들어오는 게 student가 아닌데 student로 타입캐스팅하는 순간 런타임에러 발생
//		내가 받아온 게 뭔지를 몰라
		Object obj = box.getContent();
//		return이 Object
	
		if(obj instanceof Student) {
			Student std = (Student)obj;
		
		String s = std.getName();
		int n = std.getGrade();
		
//		DB테이브에 저장
		
		}else if(obj instanceof String) {
//			문자열처리
		}else if(obj instanceof Integer) {
//			정수처리
		}
		
	}
}
