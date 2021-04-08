package GUI;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Lotto_Test {
	
	public static void main(String[] args) {
		lotto();
	}
	
	class MyComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public static void lotto() {
//		MyComparator comp = new MyComparator();
//		Set<Integer> lottoNums = new TreeSet<>(comp);
		
//		Set<Integer> lottoNums = new HashSet<>(); // ����X
//		Set<Integer> lottoNums = new TreeSet<>();  //���ĵǸ鼭
		Set<Integer> lottoNums = new LinkedHashSet<>();  //�� ������� 
		
		
		
//		lottoNum�� ���� ���� 6�� �ɶ�����
//		1~45������ ������ ���� ������  lottoNum�� ����		
		
		while(lottoNums.size() < 6) {
//			lottoNums.add((int)(Math.random() * 45) + 1);
//			System.out.println(lottoNums);
			int num = (int)(Math.random() * 45) + 1;
			if(lottoNums.add(num)) {
				System.out.println(num + ",");
			}

		}
		
		System.out.println(lottoNums);
		
		Iterator<Integer> iter = lottoNums.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
	}
	
	
	

}
