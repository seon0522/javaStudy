package GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError {

	private static void writeList() {
		PrintWriter out = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("��:/sout.txt");
			out = new PrintWriter(fw);
			out.println("�ȳ��ϼ���");
			System.out.println("�۾�����.../");
			out.close();
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
		
		writeList();
	}
}
