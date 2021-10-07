package GUI;
//5월 3일 
//첫번째 시간

//우리가 사용할 수 있는 편의 기능들
import java.util.Collections;
import java.util.Comparator;
//편리하게 사용가능한 api -> 이를 이용해 리스트로 바꿀 수 있다.
import java.util.Arrays;

import java.util.List;

public class CollectionsAPITest {
	private String name;
	
	public CollectionsAPITest(String name) {
		this.name = name;
	}
	
	//static or instance method? - instance 생성될 때 마다의 객체의 접근하기 위해서
	public String getName() {
		return name;
	}

	
	
	public static void main(String[] args) {
		/*		Comparable - 원소의 타입 클래스를 내가 변경할 수 있으면
		 * 
				Comparator - 원소의 타입 클래스를 내가 변경할 수 없으면
				EX) String, Integer
				Comparator클래스를 구현해서 정렬 방법을 알려줘야 함. or 
				원소의 타입 클래스를 변경할 수 있으나,변경하지 않고 
				정렬방법 변경하고자 할 때 comparator클래스 구현
		*/	
		
		String[] sample = {"i", "walk", "the","line"};
		List<String> list = Arrays.asList(sample); //배열을 리스트로 변경
		
		//Collection의 sort메서드는 List 타입을 인자로 가진다.
		System.out.println("정렬 전 : " + list);
		Collections.sort(list);
		System.out.println("오름차순 정렬 후 : " +list);
		
		//Collection.sort(list STring주면  정렬을 함)
//		내림차순으로 정렬하고 싶을 때
		Collections.sort(list, new MyStringComparator()); 
		System.out.println("내림차순 정렬 후 : " + list);
		
	
		
	}
	
}
