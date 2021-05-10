package GUI;
// 5월 3일
//2번째 시간

import java.util.Comparator;

public class MyStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// o1이 크면 양수 반환, o1과 o2가 같으면 0반환, o2가 크면 음수반환
		
		//문자열 값의 비교는 Comparable 인터페이스를 구현하고 있는 String클래스의 
		//compare 메소드를 호출하면 됨
		// o2가 크면 음수, 
		return o1.compareTo(o2) * (-1); //이를 아까 콜렉션에 주면 오름차순 정렬 됨
	}
	
	public static void main(String[] args) {
		
	}
	
}
