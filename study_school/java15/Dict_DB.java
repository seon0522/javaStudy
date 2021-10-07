package sd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;

public class Dict_DB extends JPanel implements ActionListener {
// 단어 입력 받을 수 있는 JTextField
// 검색 버튼, 추가 버튼
// 단어장 구현을 위한 자료 구조로 Map 객체 생성

private JTextField inputField = new JTextField(30);
private JButton searchBtn = new JButton("검색");
private JButton addBtn = new JButton("추가");
	
// Map객체를 단어장 구현으로 사용
// key는 한글단어, value는 대응되는 영어 단어를 저장한다
private static final String JDBC_DRIVER ="org.mariadb.jdbc.Driver";
private Map<String, String> words = new HashMap<>();
private static final String DIC_FILE_NAME = "dict.props";
// DB마다 서버 URL 포맷이 다르다.
// 찾아봐야된다.
private static final String DB_SERVER_URL =
"jdbc:mariadb://localhost:3306/oop2";
private static final String DB_USER = "root";
private static final String DB_USER_PW="";


public Dict_DB() {
// Panel의 기본 레이아웃은 FlowLayout
this.add(inputField);
this.add(searchBtn);
this.add(addBtn);

// 각 버튼에 글릭 이벤트가 발생 했을때 처리할 리스너를 지정
searchBtn.addActionListener(this);
addBtn.addActionListener(this);

this.setPreferredSize(new Dimension(600, 50));

// 파일에 key=value 형태로 저장된 엔트리들을 읽어서, words를 구성하기
// buildDictionaryFromFile();
buildDictionaryFromDB();
}

private void buildDictionaryFromDB() {
/*
* 1. Databse server에 연결한다.
*   a. JDBC 드라이버를 메모리에 로딩(적재) - Class.forName(JDBC_DRIVER);
*   b. DriverManager (java.sql 패키지에 정의된 클래스) - Connection con = DriverManager.getConnection();
*       메서들 호출해 연결을 establish
*       이 때 연결 정보를 getConnection() 메서드에 전달해줘야 함.
*       연결정보: DB Server의 URL
*          => (ip 주소, port 번호, 데이터베이스 이름)
*           db 사용자의 아이디와 암호
*
* 2. Connection 객체를 통해 SQL문 실행을 서버에 요청하고, 결과를 받아 처리한다.
*    a. con.createStatement() 메소드 호출을 통해서 반환되는 Statement 객체를 이용하는 방법 (정적 SQL 문)
*     정적 SQL문 : 프로그래밍 시점에 실행할 SQL문 결정되고 고정된 경우.
*       select * from dict;
*
*    b. con.prepareStatement() 메서드 호출을 통해서 반환되는 PreparedStatement 객체를 이용하는 방법 (동적 SQL 문)
*     동적 SQL문: 프로그래밍 시점에 실행할 SQL문 결정되지 않고 변경되는 SQL문
*               select * from dict where han = ?
*
*   이 EX에서는 PreparedStatement 객체를 이용
*
*     String sql = "select * from dict";
*     PreparedStatement pstmt = con.prepareStatement(sql);
*
*     // 실행 준비가 된 PreparedStatement를 실행시키는 방법은 크게 2가지
*     a. insert, delete, update - pstmt.executeUpdate();
*     "insert into ..."
*     "delete from dict..."
*     "update set eng = ... from ..."
*    
*     pstmt.executeUpdate();
*    
*     b. select - pstmt.executeQuery();
*     "select .... "
*
*     ResultSet rs =  pstmt.executeQuery();
*    
*  3. DB Server와의 연결을 해제(close)한다.
*     con.close();
*/
	
// MySQL JDBC 드라이버를 메모리에 적재
// 드라이버 클래스 이름은 DBMS마다 다르다.
try {
Class.forName(JDBC_DRIVER);
} catch (Exception e) {
System.out.println(e.getMessage());
return;
}

// DB 서버에 연결
try(Connection con =
DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW))
{

// SELECT 문 실행.
String sql = "select * from dict";
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();
while(rs.next()) {
// 현재 포인터가 가리키는 칼럼 값을 빼오면 됨.
// 각 칼럼의 타입에 따라서. 호출할 메서드가 달라진다.
// EX)
// char, varchar 타입의 칼럼 - getString("칼럼이름" 또는 "칼럼 위치");
// int 타입의 칼럼 - getInt(...);
// DateTime, Date 타입의 칼럼 값 - getDate();
	
// rs.getString("han");
String han = rs.getString(1);
// rs.getString("eng");
String eng = rs.getString(2);

words.put(han, eng);
}

} catch(Exception e) {
System.out.println(e.getMessage());
}
// finally {
// try {con.close();} catch(Exception ignore) {}
// }
}

private void buildDictionaryFromFile() {
// Properties는
// key, value의 타입이 각각 String, String으로
// 고정된 일종의 Map이다.
Properties props = new Properties();
// props.put("사과", "apple");
// System.out.println(props.get("사과"));

// 파일에서 읽어서 props 객체의 <key, value>
// 쌍을 구성할 수 있다.
// FileReader fReader = new FileReader(DIC_FILE_NAME);
// props.load(fReader);

try (FileReader fReader = new FileReader(DIC_FILE_NAME, Charset.defaultCharset())) {
props.load(fReader);
}catch(Exception e) {
System.out.println(e.getMessage());
}

Set<Object> set = props.keySet();
for (Object obj : set) {
words.put((String)obj, (String)props.get(obj));
}
}

@Override
public void actionPerformed(ActionEvent e) {
String key = inputField.getText();
if(key.trim().length() == 0) return;

if(e.getSource() == searchBtn) {
// 입력된 단어를 추출하여
// 그 단어를 key 값으로 가지는 대응된느 맵 엔트리가 있는지 검사 words.get(단어);
// 대응되는 값 
// O -> JOptionPane.showMessageDialog() 메서드를 호출  //대응되는 값 보여줌
// X(null) -> JOptionPane.showMessageDialog() 메서드를 호출	 //단어를 찾을 수 없습니다. 라고 출력

// inputField를 빈 문자열로 설정한다
System.out.println("[" + key + "]");
String value = words.get(key);

if(value != null) {
// 대응되는 단어가 있는 경우
JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
} else {
// 대응되는 단어가 없는 경우
JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다.", key, JOptionPane.ERROR_MESSAGE);
}
} else if(e.getSource() == addBtn) {
// 입력된 단어를 추출
// JOptionPane.showInputDialog() 메서드를 호출해서 추가할 영어 단어를 입력 받는다
// words.put(입력 필드에 입력된 단어, inputDialog에 입력된 단어)

String value = JOptionPane.showInputDialog(this, key+ "에 대응 되는 영어 단어를 입력하세요.");

if(value.trim().length() == 0) return;
words.put(key, value);
words.put(value, key);

//addWordToFile(key, value);
addWordToDB(key, value);
JOptionPane.showMessageDialog(this, "[" + value + "]" + "영어 단어가 추가되었습니다.", key, JOptionPane.INFORMATION_MESSAGE);
}

// inputField.setText("");

}

private void addWordToDB(String key, String value) {
	/*1. 드라이버 클래스는 딱 한번만 메로리애 적재하면 됨.
	 * 근데 이미 객체가 생성될 때, 생성자에서 적재되었슴.
	 * 여기서는 적잭할 필요가 없음
	 * 
	 * 2. 데이터베이스 연결
	 * 3. SQL 문 실행  - prepare, statment 둘 중 하나는 필요/ 쓰기 위해서는 필요한 준비를 해야함
	 * 4. 데이터베이스 연결 해제
	 * 
	 * */
	try(Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
		String sql = "insert into dict values(?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//? 자리에 값을 채운 후에, 서버에게 실행준비된 SQL문을 실행하라고 요청해야 한다.
		pstmt.setString(1, key);
		pstmt.setString(2, value);
		
		
		pstmt.executeUpdate();//실행요청
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}

private void addWordToFile(String key, String value) {
try(FileWriter fWriter =
new FileWriter(DIC_FILE_NAME, true)) {
fWriter.write(key+"="+value+"\n");
// 사과, apple
}catch(Exception e) {
System.out.println(e.getMessage());
}
}

public static void main(String[] args) {
JFrame frame = new JFrame();
frame.add(new Dict_DB());

frame.setTitle("한영사전");
frame.setResizable(false);
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
}
}
