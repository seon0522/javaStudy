package GUI;

import java.util.StringTokenizer;

public class StackTest2 {
	public static void main(String[] args) {
		String str = "apple, banana, carrot, pineapple, grape, mango,"
				+ " strawberry, watermelon, orange, coconut,"
				+ "kiwi, lemon, tomato, cherry, blueberry, peach, cramberry";
		
		/*구분자를 입력으로 주고, 구분자로 구분되는 문자열을 나눠주는 것
		 * java.util 패키지의 클래스 중 StringTokenizer 
		 * */
		
//		안주면 공백이 구분자
		StringTokenizer st = new StringTokenizer(str, ", ");
		
		//1. while 돌리기
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
