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

		//기본적 input  보낼띠는 output스트림  근데 output은 막아놈
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
		
		//id = hyoseon&pw=1111
		//내 데이터를 보낼 수 있음 - 한 라인씩 쓰고 싶어서 buffer
		OutputStream stream = con.getOutputStream();
		
		OutputStreamWriter owriter = new OutputStreamWriter(stream,"UTF-8");
		
		PrintWriter writer = new PrintWriter(owriter);
		
		writer.println("id=hyoseon&pw=1111");
	}
}
