package GUI;

import java.util.StringTokenizer;

public class StackTest2 {
	public static void main(String[] args) {
		String str = "apple, banana, carrot, pineapple, grape, mango,"
				+ " strawberry, watermelon, orange, coconut,"
				+ "kiwi, lemon, tomato, cherry, blueberry, peach, cramberry";
		
		/*�����ڸ� �Է����� �ְ�, �����ڷ� ���еǴ� ���ڿ��� �����ִ� ��
		 * java.util ��Ű���� Ŭ���� �� StringTokenizer 
		 * */
		
//		���ָ� ������ ������
		StringTokenizer st = new StringTokenizer(str, ", ");
		
		//1. while ������
//		while(st.countTokens() > 0) {
//			String s = st.nextToken();
//			System.out.println(s);
//		}
		
		int length = st.countTokens();
		
		for(int i = 0; i < length; i++) {
			String s = st.nextToken();
			System.out.println(s);
		}
		
//		st.hashMoreTokens()
		
//		while(st.hasMoreTokens()) {
//			String Token = st.nextToken();
//			System.out.println("[" + Token + "]");
//		}
//		
		
	}
}
