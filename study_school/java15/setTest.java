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
//			System.out.println(file.getAbsolutePath() + " 존재함");
//		}else {
//			System.out.println(file.getAbsolutePath() + " 존재하지 않음");
//		}
		
//		파일 내용을 읽자
//		파일에 읽고 쓰려면 stream 객체를 이용해야 한다.
		/*
		 * 읽을 때는 Input Stream
		 * 쓸 때는 Output Stream	
		Stream은 기본적으로 byte Stream.
		그런데 문자단위로 읽고 쓸 때는 문자 스트림을 이용하는 것이 편리
		문자단위로 입력 스트림은 reader객체로 표현
		문자단위의 출력 스트림은 writer객체로 표현
	*/
		int cnt = 0;
		BufferedReader bReader = null;
		try{
//		FileReader fileReader = new FileReader("wordbook.txt");
//		FileReader는 한문자씩 읽을 때 사용
		FileReader fileReader = new FileReader(file);
		
//		한 라인단위로 읽어주는 클래스가 존재
		bReader = new BufferedReader(fileReader);
		String line = null;
		while((line = bReader.readLine()) != null) {
			System.out.println(line);
			cnt++;
		}
		
//		FileNotFoundException 가 IOException을 상속받고 있음
		}catch(IOException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}finally {
//			finally안 쓰게 나온 거네 tryresoce
			try{
				bReader.close();
				}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("단어 수(중복포함): " + cnt);
		System.out.println("단어 수(중복 미포함): " + set.size());
		
	}
}
