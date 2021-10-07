package http;

import java.awt.Insets;
import java.io.*;
import java.net.*;
import java.sql.*;

import javax.swing.plaf.InsetsUIResource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


// 6월 7일
public class JSONReader {
	public static void main(String[] args) throws Exception {
		String site = "https://jsonplaceholder.typicode.com/posts";
		
//		- exception이 발생가능하기 때문에 1. trycatch를 이요해서 예외처리를 하던가 2. 혹은 나를 호출한 운영체제에 throws던지던가
		
//		사이트를 연결하기 위해서 
//		URL객체를 만듦 - urlException발생
		URL url = new URL(site);	//1. 
		
//		URL 연결 열기
		URLConnection con = url.openConnection();	//2.
		
//		이거를 이용해 읽기
		InputStream stream = con.getInputStream();	//3.
		
//		한 라인 씩 편리하게 읽기 위해서 bufferReader읽기
//		inputStream -> bufferReader 
//		그래서 inputbu
		
//		stream.read(); //읽을 수 있음 그러나 편리하게 하기 위해서	 
//		inputStreamReader 를 만듦
//		다국어가 포함 된 경우 깨질 수 있어서 UTF-8이라고 명시
//		exception발생가능
		InputStreamReader reader = new InputStreamReader(stream, "UTF-8");	//4.
		
		BufferedReader bufReader = new BufferedReader(reader);	//5.
		
		String line = null;
		String JsonString = "";
		
	//		한 라인씩 읽겠다. 이런식으로 사용 안하면 맨 첫번째 것만 들고오기 때문에 무한반복!!!! 절대 null이 안되기 때문	
		while((line = bufReader.readLine()) != null) {
			System.out.println(line);
			JsonString += line;
		}
//		System.out.println(JsonString);
		
//	******************************************************	
//		구글에서 만든 GSON 라이브러리를 사용해서 쉽게 객체로 변환
//		set이름 - 값을 셋팅할 때는 set객체이름		
		
		Gson gson = new Gson();

//		String json = "[{'userId':1, 'id':1, 'title':'test', 'body':'test body'},"
//				+ "{'userId':2, 'id':1, 'title':'sd', 'body':'test body'}]";
		Post[] posts = gson.fromJson(JsonString, Post[].class); 
		//	json이 배열로 반환될 대는 저런시긍로 배열이라서 객체의 배열로 뱐환시켜 달라 해야함
		
		/*
		 * Post post = new Post();
		 * post.setUserId(1);
		 * post.setId(1);
		 * post.setTitle("title");
		 * post.setBody("test Body");
		 * return post; 를 해줌
		 * */ //단순 문자열이 핸들링 가능한 데이터가 됨
//		
////		배열이 리턴될 때 [] 사용 해줘야 됨
//		for(Post post : posts) {
//			System.out.println(post.getUserId());
//			System.out.println(post.getId());
//			System.out.println(post.getTitle());
//			System.out.println(post.getBody());
//			System.out.println("jsonString - 1 ###########################");
//			System.out.println();
//		}
//		
//		System.out.println("------------------------");
//		
//		for(int i = 0; i < posts.length; i++) {
//			System.out.println(posts[i].getUserId());
//			System.out.println(posts[i].getId());
//			System.out.println(posts[i].getTitle());
//			System.out.println(posts[i].getBody());
//			System.out.println("jsonString - 2 ###########################");
//			System.out.println();
//		}
		
		try {
			insertIntoDB(posts);
		}catch(Exception e) {
			e.getMessage();
		}
		
		
	}
	
	private static void insertIntoDB(Post[] posts) throws Exception {
		/*
		1. class.forName();  //JDBC메모리에 적재
		2. Connection con = DriverManager.getConnection(); //DB서버 연결 
		3. String sql = "insert into posts(UserId, id, title, body)
					values(?,?,?,?);
					preparedStatement pstmt = con.prepareStatement(sql);
					
		---------------------------------------- 한번만 하면 됨
   		4. ? 자리에 바인딩
   			pstmt.setInt(1, post.getUserId());
   			pstmt.setInt(1, post.getUserId());
   			pstmt.setInt(1, post.getUserId());
   			pstmt.setInt(1, post.getUserId());
   			
   		5. 서버에 실행 요청
   			pstmt.executeUpdate();
   			
   		6. con.close();
		*/
		
		
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://localhost:3306/oop";
			String id = "root";
			String pw = "1111";
			
			Connection con = DriverManager.getConnection(url, id, pw);
			String sql = "insert into posts(userId, id, title, body) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			for(Post post: posts) {
				pstmt.setInt(1, post.getUserId());
				pstmt.setInt(2, post.getId());
				pstmt.setString(3, post.getTitle());
				pstmt.setString(4, post.getBody());
				
				pstmt.executeUpdate();
			}
			
			con.close();
	}	
}
