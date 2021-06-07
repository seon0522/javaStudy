package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLTest {
	public static void main(String[] args) throws Exception{
		URL url = new URL("https://www.google.com/search?q=java");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		int responeCode = con.getResponseCode();
		System.out.println("Response code: " + responeCode);
		
		InputStream stream = con.getInputStream();
		
		InputStreamReader isreader = new InputStreamReader(stream, "UTF-8");
		
		BufferedReader reader = new BufferedReader(isreader);
		
		String line;
		while((line = reader.readLine()) != null ) {
			System.out.println(line);
		}
	}
}
