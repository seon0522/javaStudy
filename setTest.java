package GUI;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.AbstractDocument.BranchElement;


public class setTest {
	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {
		File file = new File("wordbook.txt");
		Set<String> set = new HashSet<>();
//		if(file.exists() == true) {
//			System.out.println(file.getAbsolutePath() + " ������");
//		}else {
//			System.out.println(file.getAbsolutePath() + " �������� ����");
//		}
		
//		���� ������ ����
//		���Ͽ� �а� ������ stream ��ü�� �̿��ؾ� �Ѵ�.
		/*
		 * ���� ���� Input Stream
		 * �� ���� Output Stream	
		Stream�� �⺻������ byte Stream.
		�׷��� ���ڴ����� �а� �� ���� ���� ��Ʈ���� �̿��ϴ� ���� ��
		���ڴ����� �Է� ��Ʈ���� reader��ü�� ǥ��
		���ڴ����� ��� ��Ʈ���� writer��ü�� ǥ��
	*/
		int cnt = 0;
		BufferedReader bReader = null;
		try{
//		FileReader fileReader = new FileReader("wordbook.txt");
//		FileReader�� �ѹ��ھ� ���� �� ���
		FileReader fileReader = new FileReader(file);
		
//		�� ���δ����� �о��ִ� Ŭ������ ����
		bReader = new BufferedReader(fileReader);
		String line = null;
		while((line = bReader.readLine()) != null) {
			System.out.println(line);
			cnt++;
		}
		
//		FileNotFoundException �� IOException�� ��ӹް� ����
		}catch(IOException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}finally {
//			finally�� ���� ���� �ų� tryresoce
			try{
				bReader.close();
				}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("�ܾ� ��(�ߺ�����): " + cnt);
		System.out.println("�ܾ� ��(�ߺ� ������): " + set.size());
		
	}
}
