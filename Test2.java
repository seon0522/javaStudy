package GUI;

import java.util.ArrayList;

public class Test2 {
	public static void main(String[] args) {
//		String[] arr1 = {"����", "�ٴ�", "��"};
//		arr1[3] = "�ź���";//runtimeException�߻�
		
//		���Ұ� 100 ~ 10���� / �迭�� ũ�Ⱑ �����Ǿ� �ִٴ� ����
//		�̸� �غ��ϱ� ���ؼ� arraylist�� ����
		
//		100���µ� �� ������ �ڵ����� �� �÷� ��; �پ��� ������ �� �ݳ���
		ArrayList<String> strList = new ArrayList<String>();
		
		for(int i = 0; i < 200; i++) {
//			���ڿ��� �ٲ㼭 �־���
//			strList.add(String.valueOf(i));
			
			strList.add(""+i);
		}
		
		for(int j = 0; j < strList.size(); j++) {
			System.out.println(strList.get(j));
		}
		
		ArrayList<Student> stdList = new ArrayList<Student>();
		for(int i = 0; i < 100; i++) {
			stdList.add(new Student());
		}
	}
}
