package GUI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetTest_tra {
	public static void main(String[] args) {
		test1();
		test2();
	}
	
	public static void test2() {
//		linkedHashSet - 입력순서가 유지되는 것
//		TreeSet - 오른차순 대로
		List<Integer> list = Arrays.asList(10,9,8,7,6,235,4,3,2);
		
		MyComparator mc = new MyComparator();
		
		Set<Integer> set1 = new TreeSet<>(mc);
		
		set1.addAll(list);
		Iterator<Integer> iter =set1.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	public static void test1() {
		/*
		 * set1 = {2,3,4,5,6,8,9,10}
		 * set2 = {1,3,5,7,9}
		 * 합집합 : set1.addAll(set2) = {1,2,3,4,5,6,7,8,9,10}
		 * 교집합 : set1.retainAll(set2) = {3,5,9}
		 * 차집합 :  = set1 - set2 : set1.removeAll(set2) => {2,4,6,8,10}
		 * 		   = set2 - set1 => : set2.removeAll(set1) {1,7}
		 * 
		 * 원본이 변경됨
		 * */
		
//		generic 클래스 객체를 생성할 때 타입 파라미터는 프리미티브 타입(int, long, String 등등)을 줄 수 없다.
//		객체 타입만 줄 수 있다.
		
//		int의 해당 integer
		
		Set<Integer> set1 = new HashSet<>();
/*		set1 = {2,3,4,5,6,8,9,10};
		set1.add(2); -> 해야할 부분이 많음. 한 번에 하는 것 set1.addAll(c)
		
		문제 발생 -> c가 콜렉션 타입
		
		Integer[] arr = {2,3,4,5,6,8,9,10};  
		배열을 collection 객체로 만들어 주는 메소드가 있다.
		매개변수의 수가 임의의 변수로 있는 것이 있음
		
		List<Integer> list1 = Arrays.asList(2,3,4,5,6,8,9,10);
		List<Integer> list1 = Arrays.asList(10,9,8,7,6,5,4,3,2);
		
		set1.addAll(list1);
		
		set1.add(2);
*/		
		
//	set1.union.set2 어떤 순서로 해도 같은 결과. 차집합은 순서에 따라서 결과가 달라짐
		Set<Integer> set2 = new HashSet<>();
		
		List<Integer> list2 = Arrays.asList(1,3,5,7,9);
		set2.addAll(list2);
		
		System.out.println("set1 : " + set1);
		System.out.println("set2 : " + set2);
		
		
		Set<Integer> unionset = new HashSet<>(set1);
		unionset.addAll(set2);//블린 값 
		System.out.println("set1 합집합 set2 : " + unionset);
		
		Set<Integer> intersectinoset= new HashSet<>(set1);
		intersectinoset.retainAll(set2);
		System.out.println("set1 교집합 set2: " + intersectinoset);
		
		Set<Integer> deffset = new HashSet<>(set1);
		deffset.removeAll(set2);
		System.out.println("set1 차집합 set2: " + deffset );
		
		Set<Integer> deffset2 = new HashSet<>(set2);
		deffset2.removeAll(set1);
		System.out.println("set2 차집합 set1: " + deffset2 );
		

//		for(Integer i: deffset) {
//				System.out.println(i + " ");
//		}
		
		System.out.println("############################3");
		
//		Iterator<Integer> iter = diffSet.iterator();
		
//		Iterator<Integer> iter = set1.iterator();
//		while(iter.hasNext()){
//			Integer val = iter.next();
//			System.out.println(val + " ");
//		}
		
		
		System.out.println("############################3");
		
//		Set<Integer> diffSet2= new HashSet<Integer>();
		
	}
}

class MyComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
	
//		같으면 0
//		앞에 인자로 전달된 놈이 크면 양수
//		뒤의 인자로 전달된 놈이 크면 음수
		
//		내림차순 정렬 되는 것
		return o2 - o1;
	}
	
}
