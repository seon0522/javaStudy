package http;

import java.net.InetAddress;
import java.net.UnknownHostException;

//5岿 31老

public class Host2InetAddr {
	public static void main(String[] args) {
		String hostName = "www.naver.com";
		
		try {
			InetAddress address = InetAddress.getByName(hostName);
			System.out.println("ip林家 : " + address.getHostAddress());
			System.out.println("ip林家 : " + address.getHostName());
			
		}catch(UnknownHostException e) {
			System.err.print(e.getMessage());
		}
	}



}
