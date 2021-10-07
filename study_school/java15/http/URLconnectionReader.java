package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//5월 31일

public class URLconnectionReader {
	
	public static void main(String[] args) throws Exception{

			URL site = new URL("https://www.naver.com");
			
			URLConnection sitecon = site.openConnection();  //urlconnection 객체 리턴
			
			InputStream inputstream = sitecon.getInputStream();
			
//			inputstream.read();  //왜 read는 int를 return..? 바이트로 계산하면 255인지 -255인지 구분이 안 되니까 인티져로 리턴함.
			
//			문자로 읽을 때는 reader객체를 시용하면 더 편리
			InputStreamReader ireader = new InputStreamReader(inputstream);
			
//			한 라인씩 읽음. reader객체를 원함. 리더를 가지고 버퍼터 리더를 줌.
			BufferedReader reader = new BufferedReader(ireader);
			
			String line;//다 읽고 나서 null을 리턴함.
			
			while((line = reader.readLine()) != null ) {
				System.out.println(line);
			}
		
	}
	
}
