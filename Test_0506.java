package GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

//5월 6일
public class Test_0506 {
	 public static void main(String[] args) {
//		 key와 value로 이루어짐
		Properties props = new Properties();
		
		try(FileReader reader= new FileReader("dict.props")){
			props.load(reader);	
		}catch(Exception err) {
			System.out.println(err.getMessage());
		}
		
		System.out.println(props.get("사과"));
		props.put("장미", "rose");
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("dict.props");
			props.store(out, "나의한영사전");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{
				out.close();
				}catch(Exception ignore) {}
		}
	}
}
