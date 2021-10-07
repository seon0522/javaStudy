package GUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class list_Test {
public static void main(String[] args) {
	test1();
}

private static void test1() {
//	List<String> list = new ArrayList<>();
	List<String> list = new LinkedList<>();
	String[] sArr = {"자동차","운동장","유치원","학교","사람"};
	
	for(String s : sArr) list.add(s);
	System.out.println(list);
	
	for(int i = 0; i < list.size(); i++) {
		System.out.print(list.get(i) + " ");
	}
	System.out.println();
	
//	foreach 문
	for(String s : list) System.out.print(s + " ");
	System.out.println();
	
//	원소에 접속하기 위해서 iterator객체를 만들어 주세요!
	Iterator<String> iter = list.iterator();
//	줄게 있으면 true, 없으면 false를 반환
	while(iter.hasNext()) {
		System.out.print(iter.next());
	}
	System.out.println();
	
//	overlodading된 add메소드로 원소를 중간에 삽입할 수 있다.
	list.add(1, "벚꽃");
	
//	이미 false의 상태로 못 돔. 다시 돌고 싶을 경우,다시 객체를 줘야함.
	iter = list.iterator();
	while(iter.hasNext()) {
		System.out.print(iter.next() + " ");
	}
	System.out.println();
	
//	list.remove(list.size()-1);
//	while(list.size() < 0){
//		list.remove(0);
//	}
	list.remove(3);
	System.out.print(list);
	
}


}
