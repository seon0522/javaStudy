package GUI;
// 5�� 3��
//2��° �ð�

import java.util.Comparator;

public class MyStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// o1�� ũ�� ��� ��ȯ, o1�� o2�� ������ 0��ȯ, o2�� ũ�� ������ȯ
		
		//���ڿ� ���� �񱳴� Comparable �������̽��� �����ϰ� �ִ� StringŬ������ 
		//compare �޼ҵ带 ȣ���ϸ� ��
		// o2�� ũ�� ����, 
		return o1.compareTo(o2) * (-1); //�̸� �Ʊ� �ݷ��ǿ� �ָ� �������� ���� ��
	}
	
	public static void main(String[] args) {
		
	}
	
}
