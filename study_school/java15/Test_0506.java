package GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

//5�� 6��
public class Test_0506 {
	 public static void main(String[] args) {
//		 key�� value�� �̷����
		Properties props = new Properties();
		
		try(FileReader reader= new FileReader("dict.props")){
			props.load(reader);	
		}catch(Exception err) {
			System.out.println(err.getMessage());
		}
		
		System.out.println(props.get("���"));
		props.put("���", "rose");
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("dict.props");
			props.store(out, "�����ѿ�����");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try{
				out.close();
				}catch(Exception ignore) {}
		}
	}
}
