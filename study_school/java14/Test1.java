package GUI;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��¥�� �Է��Ͻÿ�");
		int date = input.nextInt();
		
//		assert�� �Ÿ��� �� �ε� default�� ���� �� ��, �ɼ��� �༭ ���� ���Ѿ���
		
		assert (date >= 1 && date <= 31) : "�߸��� ��¥" + date;
		
//		assert�� ����ߴٸ� ����
		System.out.printf("�Էµ� ���ڴ� %d �Դϴ�.\n", date);
		
	}
}
