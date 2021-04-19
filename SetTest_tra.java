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
//		linkedHashSet - �Է¼����� �����Ǵ� ��
//		TreeSet - �������� ���
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
		 * ������ : set1.addAll(set2) = {1,2,3,4,5,6,7,8,9,10}
		 * ������ : set1.retainAll(set2) = {3,5,9}
		 * ������ :  = set1 - set2 : set1.removeAll(set2) => {2,4,6,8,10}
		 * 		   = set2 - set1 => : set2.removeAll(set1) {1,7}
		 * 
		 * ������ �����
		 * */
		
//		generic Ŭ���� ��ü�� ������ �� Ÿ�� �Ķ���ʹ� ������Ƽ�� Ÿ��(int, long, String ���)�� �� �� ����.
//		��ü Ÿ�Ը� �� �� �ִ�.
		
//		int�� �ش� integer
		
		Set<Integer> set1 = new HashSet<>();
/*		set1 = {2,3,4,5,6,8,9,10};
		set1.add(2); -> �ؾ��� �κ��� ����. �� ���� �ϴ� �� set1.addAll(c)
		
		���� �߻� -> c�� �ݷ��� Ÿ��
		
		Integer[] arr = {2,3,4,5,6,8,9,10};  
		�迭�� collection ��ü�� ����� �ִ� �޼ҵ尡 �ִ�.
		�Ű������� ���� ������ ������ �ִ� ���� ����
		
		List<Integer> list1 = Arrays.asList(2,3,4,5,6,8,9,10);
		List<Integer> list1 = Arrays.asList(10,9,8,7,6,5,4,3,2);
		
		set1.addAll(list1);
		
		set1.add(2);
*/		
		
//	set1.union.set2 � ������ �ص� ���� ���. �������� ������ ���� ����� �޶���
		Set<Integer> set2 = new HashSet<>();
		
		List<Integer> list2 = Arrays.asList(1,3,5,7,9);
		set2.addAll(list2);
		
		System.out.println("set1 : " + set1);
		System.out.println("set2 : " + set2);
		
		
		Set<Integer> unionset = new HashSet<>(set1);
		unionset.addAll(set2);//�� �� 
		System.out.println("set1 ������ set2 : " + unionset);
		
		Set<Integer> intersectinoset= new HashSet<>(set1);
		intersectinoset.retainAll(set2);
		System.out.println("set1 ������ set2: " + intersectinoset);
		
		Set<Integer> deffset = new HashSet<>(set1);
		deffset.removeAll(set2);
		System.out.println("set1 ������ set2: " + deffset );
		
		Set<Integer> deffset2 = new HashSet<>(set2);
		deffset2.removeAll(set1);
		System.out.println("set2 ������ set1: " + deffset2 );
		

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
	
//		������ 0
//		�տ� ���ڷ� ���޵� ���� ũ�� ���
//		���� ���ڷ� ���޵� ���� ũ�� ����
		
//		�������� ���� �Ǵ� ��
		return o2 - o1;
	}
	
}
