package GUI;

import java.io.IOException;

public class Exc {
	
	private static String readString() throws IOException{
		byte[] buf = new byte[100];
		System.out.println("문자열을 입력하세요");
		System.in.read(buf);
		return new String(buf);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(readString());
		}catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}

