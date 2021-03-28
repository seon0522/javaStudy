package GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError2 {

	private static void writeList1() {
		PrintWriter out = null;
//		AutoClosable �������̽��� �����ؾ��Ѵ�.
//		�� ��ü�� try with resources ���� ���� �� �ִ�.
	
//		FileWriter fw = null;
//		7���� try���� ()�� ���� : ���������� ������ �ҷ�����ϴ� �� ()�ȿ��� �� �ָ� ��
//		()�ȿ� �b���� �ڵ����� close�� �ҷ���. ������ �߻��ϵ� ���� �� �ֱ� ������ finally�� �ᵵ ��
		try (FileWriter fw = new FileWriter("out3.txt")){
			
			out = new PrintWriter(fw);
			out.println("hello? i love you");
			System.out.println("�۾�����.../");
//			out.close();
		}catch(IOException e) {
			System.out.println("catch : " + e.getMessage());
		}finally {
			System.out.println("finally code..");
			if(out != null) {
				out.close();
			}
			System.out.println("finally and...");
		}
	}
	
	public static void main(String[] args){
		
		writeList1();
	}
}
