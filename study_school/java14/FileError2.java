package GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError2 {

	private static void writeList1() {
		PrintWriter out = null;
//		AutoClosable 인터페이스를 구현해야한다.
//		그 객체는 try with resources 절에 사용될 수 있다.
	
//		FileWriter fw = null;
//		7부터 try옆에 ()가 생김 : 마지막으로 무조건 불려줘야하는 걸 ()안에서 해 주면 됨
//		()안에 얺으면 자동으로 close를 불러줌. 오류가 발생하든 말든 해 주기 때문에 finally안 써도 됨
		try (FileWriter fw = new FileWriter("out3.txt")){
			
			out = new PrintWriter(fw);
			out.println("hello? i love you");
			System.out.println("작업종료.../");
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
