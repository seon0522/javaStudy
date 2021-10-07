package GUI;

import java.util.ArrayList;

public class Test2 {
	public static void main(String[] args) {
//		String[] arr1 = {"동해", "바다", "고래"};
//		arr1[3] = "거북이";//runtimeException발생
		
//		원소가 100 ~ 10만개 / 배열은 크기가 고정되어 있다는 단점
//		이를 극복하기 위해서 arraylist를 제공
		
//		100쓰는데 더 들어오면 자동으로 더 늘려 줌; 줄어들면 공간을 또 반납함
		ArrayList<String> strList = new ArrayList<String>();
		
		for(int i = 0; i < 200; i++) {
//			문자열로 바꿔서 넣어줌
//			strList.add(String.valueOf(i));
			
			strList.add(""+i);
		}
		
		for(int j = 0; j < strList.size(); j++) {
			System.out.println(strList.get(j));
		}
		
		ArrayList<Student> stdList = new ArrayList<Student>();
		for(int i = 0; i < 100; i++) {
			stdList.add(new Student());
		}
	}
}
