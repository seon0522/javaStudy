package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//5�� 31��

public class URLconnectionReader {
	
	public static void main(String[] args) throws Exception{

			URL site = new URL("https://www.naver.com");
			
			URLConnection sitecon = site.openConnection();  //urlconnection ��ü ����
			
			InputStream inputstream = sitecon.getInputStream();
			
//			inputstream.read();  //�� read�� int�� return..? ����Ʈ�� ����ϸ� 255���� -255���� ������ �� �Ǵϱ� ��Ƽ���� ������.
			
//			���ڷ� ���� ���� reader��ü�� �ÿ��ϸ� �� ��
			InputStreamReader ireader = new InputStreamReader(inputstream);
			
//			�� ���ξ� ����. reader��ü�� ����. ������ ������ ������ ������ ��.
			BufferedReader reader = new BufferedReader(ireader);
			
			String line;//�� �а� ���� null�� ������.
			
			while((line = reader.readLine()) != null ) {
				System.out.println(line);
			}
		
	}
	
}
