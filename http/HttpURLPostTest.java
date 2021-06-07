package http;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLPostTest {
	public static void main(String[] args) throws Exception {
		String site = "http://localhost:8080/test";
		
		URL url = new URL(site);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();

		//�⺻�� input  ������� output��Ʈ��  �ٵ� output�� ���Ƴ�
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
		
		//id = hyoseon&pw=1111
		//�� �����͸� ���� �� ���� - �� ���ξ� ���� �; buffer
		OutputStream stream = con.getOutputStream();
		
		OutputStreamWriter owriter = new OutputStreamWriter(stream,"UTF-8");
		
		PrintWriter writer = new PrintWriter(owriter);
		
		writer.println("id=hyoseon&pw=1111");
	}
}
