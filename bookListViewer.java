package books;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// JFrame이 있어야 화면이 하나 뜸
public class bookListViewer extends JFrame implements ActionListener{
//	라벨 - 텍스트 필드, 버튼 4개, 그리드레이아웃을 사용
	//input field는 버튼을 눌러을 때 설정
	
	private JTextField idField, titleField, publisherField, yearField, priceField;
	private JButton previousBtn, nextBtn, insertBtn, finishBtn;
	private ResultSet result;
	private Connection con;
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mariadb://localhost:3306/oop2";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW="1111";
	

	public bookListViewer() throws Exception {
		
		//로직1 db에서 책 레코드들을 가져오기
//		1). JDBC드라이버적재
			Class.forName(JDBC_DRIVER);
//		2). DB연결
			con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);
//		3). PreparedStatement 객체 생성
			String sql = "select * from books order by book_id";
//			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			
//		4). SQL문 실행
		// executequery() - select / 결과 집합을 반환
		// executeupdate() - insert, delete, update / 정수값 반환
			result = pstmt.executeQuery();
		
		
//		1. JFrame에 레이아웃 지정 - 기본은 border 
		// gridlayout으로 설정(0,2) = 한 컬럼에 2개씩 배치
		this.setLayout(new GridLayout(0,2));
		
//		2. 컴포넌트를 생성		
//		- field
		idField = new JTextField();
		titleField = new JTextField();
		publisherField = new JTextField();
		yearField = new JTextField();
		priceField = new JTextField();
		
//		-button
		previousBtn = new JButton("previous");
		nextBtn = new JButton("next");
		insertBtn = new JButton("insert");
		finishBtn = new JButton("finish");
		
//3. 생성 후 보여주기 위해 생성된 컴포넌트들을 jFrame객체에 붙이기
//		- JLable - 보여주는게 다라서 다 박음
//		- field 붙이기
		this.add(new JLabel("id", JLabel.CENTER));
		this.add(idField);
		this.add(new JLabel("title", JLabel.CENTER));
		this.add(titleField);
		this.add(new JLabel("publisher", JLabel.CENTER));
		this.add(publisherField);
		this.add(new JLabel("year", JLabel.CENTER));
		this.add(yearField);
		this.add(new JLabel("price", JLabel.CENTER));		
		this.add(priceField);
		
//		-버튼에 액션 추가 후 button붙이기
		previousBtn.addActionListener(this);  // 액션 붙이기
		this.add(previousBtn);
		nextBtn.addActionListener(this);
		this.add(nextBtn);
		insertBtn.addActionListener(this);
		this.add(insertBtn);
		finishBtn.addActionListener(this);
		this.add(finishBtn);		
		
//		4. 화면이 변경 될 때 설정
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//x를 눌러도 못 끔
		this.setResizable(false);
		this.setSize(350, 200);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {

		try{
			new bookListViewer();
		//ui라서 프로그램이 끝나도 안 끊남, 독립된 다른 쓰레드로 돌아가기 때문 //쓰레드는 실행의 흐름. excuteflow
		}catch (SQLException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getSource() == previousBtn) {
			//결과 집합에 커서를 이전으로 이동
			result.previous();
			// 커서가 가리키는 결과 레코드의 컬럼값을 뽑아와서 JtextField 값으로 설정
			setCurent_Book_info();
			
		}else if(e.getSource() == nextBtn) {
			//결과 집합에 커서를 다음으로 이동
			result.next();
			// 커서가 가리키는 결과 레코드의 컬럼값을 뽑아와서 JtextField 값으로 설정
			setCurent_Book_info();
			
		}else if(e.getSource() == insertBtn) {
			//
			System.out.println("insert");
		}else if(e.getSource() == finishBtn) {
			//
			System.out.println("finish");
			System.out.println("프로그램을 종료합니다.");
			con.close();
			System.exit(0);
		}
		}catch(Exception err) {
			System.out.println(err.getMessage());
		}
		
		
	}
	
	private void setCurent_Book_info() throws Exception {
		int bookId = result.getInt("book_id");
		String title = result.getString("title");
		String publisher = result.getString("publisher");
		Date year = result.getDate("year");  //sql의 데이터 //자바는 sql에도 있고, util에도 있음
		int price = result.getInt("price");
		
		idField.setText(String.valueOf(bookId));
		titleField.setText(title);
		publisherField.setText(publisher);
		yearField.setText(year.toString());
		priceField.setText(String.valueOf(price));
		

	}

}
