package GUI;

import java.util.*;
import java.util.stream.IntStream;

//list����

public class CollectionTest {
	
	public static void main(String[] args) {
//		test2();
//		test3();
		setTest1();
		
	}

	
	public static void setTest1() {
//		�ߺ��ż� �� ��
		Set<String> set = new HashSet<>();
		String[] strArr = {"�ܾ�", "�ߺ�","����","�ߺ�"};
		
		for(String s : strArr) {
			if(set.add(s) == false) {
				System.out.println(s + "���� �̹� ����");
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
		System.out.println("APPLE�� 1�� �ε����� �߰��� �� : " + list);
		list.add(2, "GRAPE");
		System.out.println("2�� �ε����� ���Ҹ� GRAPE�� ������ �� : " + list);
		
		list.remove(3);
		System.out.println("3�� �ε����� ���Ҹ� ������ ��: " + list);
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}

//		�� ���Ҵµ� �ұ��ϵ� �� ������ �ϸ� exception �߻�
//		iter.next();
		System.out.println("��...");

	}
	
	public static void test2() {
		List<Integer> list = new ArrayList<>();
//		List<Integer> list = new LinkedList<>();
//		�̷��� �� ���� �ְ�
//		for(int i = 0; i < 10000000; i++) {
//			list.add(i + 1);
//		}
		
//		���۰��� ���� ������ �� ����
//		IntStream.range(startInclusive, endExclusive)
//		���۰��� ���� ������ ����. ���ٽ�
		IntStream.rangeClosed(1, 10000000).forEach(i -> list.add(i));
		
//		�ð� ���̰� �󸶳� ������ 
//		1970�� 1�� 1�Ϻ��� ��ms�� �ɷȴ����� ��������
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
		 * list : ������ �ְ� �ߺ� ���Ǵ� �ڷᱸ��
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
//		���µ� ������ �ϸ� exception�� �߻�
		

	//  � ������ �����Ŀ� ���� �ӵ����̰� �߻�
		
	}
}
