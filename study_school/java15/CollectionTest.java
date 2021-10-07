package GUI;

import java.util.*;
import java.util.stream.IntStream;

//list부터

public class CollectionTest {
	
	public static void main(String[] args) {
//		test2();
//		test3();
		setTest1();
		
	}

	
	public static void setTest1() {
//		중복돼서 안 들어감
		Set<String> set = new HashSet<>();
		String[] strArr = {"단어", "중복","구절","중복"};
		
		for(String s : strArr) {
			if(set.add(s) == false) {
				System.out.println(s + "값은 이미 존재");
			}
			
		}
		System.out.println(set);
		
		
	}
	
	public static void test3() {
		ArrayList<String> list = new ArrayList<>();
		list.add("MILK");
		list.add("BREAD");
		list.add("BUTTER");
		System.out.println(list);
		list.add(1, "APPLE");
		System.out.println("APPLE을 1번 인덱스에 추가한 후 : " + list);
		list.add(2, "GRAPE");
		System.out.println("2번 인덱스의 원소를 GRAPE로 변경한 후 : " + list);
		
		list.remove(3);
		System.out.println("3번 인덱스의 원소를 삭제한 후: " + list);
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}

//		다 돌았는데 불구하도 더 돌려고 하면 exception 발생
//		iter.next();
		System.out.println("끝...");

	}
	
	public static void test2() {
		List<Integer> list = new ArrayList<>();
//		List<Integer> list = new LinkedList<>();
//		이렇게 할 수도 있고
//		for(int i = 0; i < 10000000; i++) {
//			list.add(i + 1);
//		}
		
//		시작값은 포함 끝값은 미 포함
//		IntStream.range(startInclusive, endExclusive)
//		시작값은 포함 끝값도 포함. 람다식
		IntStream.rangeClosed(1, 10000000).forEach(i -> list.add(i));
		
//		시간 차이가 얼마나 나는지 
//		1970년 1월 1일부터 몇ms가 걸렸는지를 리턴해줌
		long start = System.currentTimeMillis();
		
//		for(int i = 0; i < 10000; i++) {
//		list.add(30, 10000);
//			}
		
		for(int i = 0; i < list.size(); i++) {
			list.get(i);
				}
		
		long end = System.currentTimeMillis();
		
		System.out.println((end - start) + "ms Elapsed...");
	}
	
	public static void test1() {
		/*
		 * list : 순서가 있고 중복 허용되는 자료구조
		 * 
		 * LinkedList
		 * 
		 * */

		List<Integer> list01 = new ArrayList<>();
		List<String> list02 = new LinkedList<>();
		List<Double> list03 = new Vector<>();
		List<Integer> list04 = new Stack<>();
		
		for(int i = 0; i < 10; i++) {
			list01.add(i+1);
			list02.add(String.valueOf(i+1));
			list03.add(i+1*1.0);
			list04.add(10-i);
		}
		
		System.out.println(list01);
		System.out.println(list02);
		System.out.println(list03);
		System.out.println(list04);
		
//		1
		for(int i = 0; i < 10; i++) {
			System.out.println(list01.get(i)+" ");
		}
		
//		2	
		for(String s : list02) {
			System.out.println(s + " ");
		}
		
//		3
		Iterator<Double> iter = list03.iterator();
		while(iter.hasNext()) {
			Double d = iter.next();
			System.out.println(d + " ");
		}
//		없는데 빼려고 하면 exception이 발생
		

	//  어떤 구조를 쓰느냐에 따라서 속도차이가 발생
		
	}
}
