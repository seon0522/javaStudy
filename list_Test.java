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
	String[] sArr = {"�ڵ���","���","��ġ��","�б�","���"};
	
	for(String s : sArr) list.add(s);
	System.out.println(list);
	
	for(int i = 0; i < list.size(); i++) {
		System.out.print(list.get(i) + " ");
	}
	System.out.println();
	
//	foreach ��
	for(String s : list) System.out.print(s + " ");
	System.out.println();
	
//	���ҿ� �����ϱ� ���ؼ� iterator��ü�� ����� �ּ���!
	Iterator<String> iter = list.iterator();
//	�ٰ� ������ true, ������ false�� ��ȯ
	while(iter.hasNext()) {
		System.out.print(iter.next());
	}
	System.out.println();
	
//	overlodading�� add�޼ҵ�� ���Ҹ� �߰��� ������ �� �ִ�.
	list.add(1, "����");
	
//	�̹� false�� ���·� �� ��. �ٽ� ���� ���� ���,�ٽ� ��ü�� �����.
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
