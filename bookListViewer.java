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

// JFrame�� �־�� ȭ���� �ϳ� ��
public class bookListViewer extends JFrame implements ActionListener{
//	�� - �ؽ�Ʈ �ʵ�, ��ư 4��, �׸��巹�̾ƿ��� ���
	//input field�� ��ư�� ������ �� ����
	
	private JTextField idField, titleField, publisherField, yearField, priceField;
	private JButton previousBtn, nextBtn, insertBtn, finishBtn;
	private ResultSet result;
	private Connection con;
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mariadb://localhost:3306/oop2";
	private static final String DB_USER = "root";
	private static final String DB_USER_PW="1111";
	

	public bookListViewer() throws Exception {
		
		//����1 db���� å ���ڵ���� ��������
//		1). JDBC����̹�����
			Class.forName(JDBC_DRIVER);
//		2). DB����
			con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);
//		3). PreparedStatement ��ü ����
			String sql = "select * from books order by book_id";
//			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			
//		4). SQL�� ����
		// executequery() - select / ��� ������ ��ȯ
		// executeupdate() - insert, delete, update / ������ ��ȯ
			result = pstmt.executeQuery();
		
		
//		1. JFrame�� ���̾ƿ� ���� - �⺻�� border 
		// gridlayout���� ����(0,2) = �� �÷��� 2���� ��ġ
		this.setLayout(new GridLayout(0,2));
		
//		2. ������Ʈ�� ����		
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
		
//3. ���� �� �����ֱ� ���� ������ ������Ʈ���� jFrame��ü�� ���̱�
//		- JLable - �����ִ°� �ٶ� �� ����
//		- field ���̱�
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
		
//		-��ư�� �׼� �߰� �� button���̱�
		previousBtn.addActionListener(this);  // �׼� ���̱�
		this.add(previousBtn);
		nextBtn.addActionListener(this);
		this.add(nextBtn);
		insertBtn.addActionListener(this);
		this.add(insertBtn);
		finishBtn.addActionListener(this);
		this.add(finishBtn);		
		
//		4. ȭ���� ���� �� �� ����
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//x�� ������ �� ��
		this.setResizable(false);
		this.setSize(350, 200);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {

		try{
			new bookListViewer();
		//ui�� ���α׷��� ������ �� ����, ������ �ٸ� ������� ���ư��� ���� //������� ������ �帧. excuteflow
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
			//��� ���տ� Ŀ���� �������� �̵�
			result.previous();
			// Ŀ���� ����Ű�� ��� ���ڵ��� �÷����� �̾ƿͼ� JtextField ������ ����
			setCurent_Book_info();
			
		}else if(e.getSource() == nextBtn) {
			//��� ���տ� Ŀ���� �������� �̵�
			result.next();
			// Ŀ���� ����Ű�� ��� ���ڵ��� �÷����� �̾ƿͼ� JtextField ������ ����
			setCurent_Book_info();
			
		}else if(e.getSource() == insertBtn) {
			//
			System.out.println("insert");
		}else if(e.getSource() == finishBtn) {
			//
			System.out.println("finish");
			System.out.println("���α׷��� �����մϴ�.");
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
		Date year = result.getDate("year");  //sql�� ������ //�ڹٴ� sql���� �ְ�, util���� ����
		int price = result.getInt("price");
		
		idField.setText(String.valueOf(bookId));
		titleField.setText(title);
		publisherField.setText(publisher);
		yearField.setText(year.toString());
		priceField.setText(String.valueOf(price));
		

	}

}
