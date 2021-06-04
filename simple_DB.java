package GUI;


 

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class simple_DB extends JPanel implements ActionListener {
	/*
	 * �ܾ� �Է� ���� �� �ִ� JTextField �˻���ư �߰���ư �ܾ��� ������ ���� �ڷᱸ���� Map��ü ���
	 */
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_SERVER_URL = "jdbc:mysql://localhost:3306/dictionary";
	private static final String DB_USER = "";
	private static final String DB_USER_PW= "";
	/*
	 * Map ��ü�� �ܾ��� �������� ��� <key, value> ������ ����. key �� �ѱ� ,value �� �����Ǵ� ����ܾ�.
	 */
	private Map<String, String> words = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";

	public simple_DB() {
		// Panel�� �⺻ ���̾ƿ� : FlowLayout
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
		// searchBtn, addBtn �� Ŭ���̺�Ʈ�� �߻������� ó���� �����ʸ� ����
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setSize(new Dimension(600, 50));
		
		// ���Ͽ� key=value ���·� ����� ��Ʈ������ �о, words �� ������

		buildDictionaryFromDB();
	}

	private void buildDictionaryFromDB() {
		/*JDBC ����̹��� �ٸ��� �ڹپȿ��� ������ � ����̹��� ����ϵ� ����(��, sql���� �ٸ� �� ����)
		 * 1. DB ����
		 * 		1). JDBC ����̹� �޸𸮿� �ε�(����). 
		 * 			- Class.forName(""org.mariadb.jdbc.Driver");
		 * 		2). DriverMangager (java.sql ��Ű���� ���ǵ� Ŭ����)
		 * 			- Connection con = DriverManager.getConnection();
		 * 			�޼��带 ȣ���� ������ establish
		 *  		�� �� ���� ������ getConnection() �޼ҵ忡 �����������
		 *  		���� ���� : DB Server�� URL (ip�ּ�, port ��ȣ, DB �̸�, db ������� ���̵�� ��ȣ)
		 * 
		 * 2. Connection ��ü�� ���� SQL�� ������ ������ ��û�ϰ� �� ����� �޾� ó��
		 * 		1-1���). con.createStatement() �޼ҵ� ȣ���� ���� ��ȯ�Ǵ� Statement ��ü�� �̿� ( ���� SQL ��)
		 * 			����SQL�� : ���α׷��� ������ ������ SQL���� �����ǰ� ������ ���.
		 * 			- select *  from dict;
		 * 
		 * 		1-2���). con.prepareStatement() �޼��� ȣ���� ���� ��ȯ�Ǵ� PreparedStatement ��ü�� �̿� ( ���� SQL ��)
		 * 			����SQL�� : ���α׷��� ������ ������ SQL���� ���������ʰ� ����Ǵ� SQL��
		 * 			- select * from dict where han = ?
		 * 
		 * 		* �ַ� 2��° ����� ���!
		 *      * String sql = "select * from dict";
		 *      * PreparedStatement pstmt = con.prepareStatement(sql)
		 *      
		 * 		* ���� �غ� �� Preparedstatement �� �����Ű�� ��� 
		 * 		2-1). insert, update, delete�� ��� - executeUpdate() 
		 * 			- pstmt.executeUpdate(); ȣ�� // ���ڰ� ����
		 * 
		 * 		2-2). select�� ��� - executeQuery()
		 * 			- pstmt.executeQuery(); ȣ�� // Resultset ���·� ��ȯ
		 * 
		 * 		---------------------------------------------------------
		 * 		3). ���� �̾Ƴ��� 
		 * 			rs.next(); // ���������� ������ true ������ false�� ��ȯ
		 * 			while(rs.next()){
		 * 				
		 * 			 }
		 * 
		 * 
		 * 3. DB server���� ������ ����(close) ��
		 * 		con.close();
		*/ 		
		
		// MySql JDBC ����̹��� �޸𸮿� ����
		try {
		Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		//DB ������ ����
		//DB ���� ���� URL ������ �ٸ�
//			DriverManager.getConnection(DB_server_url, DB_user,DB_user_ password)
//			DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);
		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW);){
					
			//SELECT �� ����
			String sql = "select * from dict";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// ���� �����Ͱ� ����Ű�� Į�� ���� ����
				// �� Į���� Ÿ�Կ� ���� ȣ���� �޼��尡 �ٸ�
				// EX) 
				// char, varchar Ÿ�� : getString("Į���̸�" �Ǵ� "Į�� ��ġ");
				// int Ÿ�� : getInt("Į���̸�" �Ǵ� "Į�� ��ġ");
				// DateTime, Date Ÿ�� : getDate("Į���̸�" �Ǵ� "Į�� ��ġ");
				
				String hword = rs.getString("hword"); // �ַ� ���� ���
				String eword = rs.getString(2);
				words.put(hword, eword);
				words.put(eword, hword);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} 
//		finally {
//			try {
//			con.close();
//			} catch ( Exception ignore) {}
//		}
		
	}

	private void buildDictionaryFromFile() {
		// properties��?
		// key, value �� Ÿ���� ���� String, String ���� ������ Map
		Properties props = new Properties();
		
		// ���Ͽ��� �о props ��ü�� <key, value> ���� ��������
		try (FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader); // ������Ƽ ���ϰ�ü�� �ε���
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object obj : set) {
			words.put((String) obj, (String) props.get(obj)); // props ��ü�� key �� , value ��
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		
		if (e.getSource() == addBtn) { 
//			 �Էµ� �ܾ ���� String value = JOptionPane.showInputDialog(); �޼��带 ȣ���ؼ� �߰��� ����ܾ �Է¹޴´�. 
//			 words.put(�Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�);
			 
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ����ܾ �Է�");
			if (value.trim().length() == 0) {
				return;
			}
			
			if (key.trim().length() == 0)
				return;
			
			words.put(key, value);
			words.put(value, key);
			// ���Ͽ� key = value �� ������ ����ص�.
			// DB�� key = value �� ���� �ϳ��� ���ڵ�� ����

			addWordToFile(key, value);
			addToDB(key, value);
			JOptionPane.showMessageDialog(this, "�ܾ� �߰� �Ϸ�.", key, JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == searchBtn) {
			/*
			 * �Էµ� �ܾ ���� �� �ܾ key ������ ������ �����Ǵ� �� ��Ʈ���� �ִ��� �˻� -> words.get(�ܾ�) �� �ܾ �����Ǵ� ����
			 * ������ JOptionPane Ŭ������ ShowMessageDialog() �޼��带 ȣ���Ͽ� �� �����Ǵ� ���� ������. ���ٸ� (null)
			 * JOptionPane.showMessageDialog() �޼ҵ带 ȣ���� '�ܾ ã�� �� �����ϴ�' ��� ������� inputField ��
			 * ���ڿ��� ������.
			 * 
			 */
			System.out.println("[" + key + "]");
			String value = words.get(key);
			if (value != null) {
				// �����Ǵ� �ܾ �ִ� ���
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			} else {
				// �����Ǵ� �ܾ ���� ���
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�", key, JOptionPane.ERROR_MESSAGE);
			}
		}
//		inputField.setText("");
	}

	private void addToDB(String key, String value) {
		/*
		 * 1. ����̹� Ŭ������ �� �ѹ��� �޸𸮿� �����ϸ� ��. (�츮�� �����ڿ��� �����)
		 * 2. �����ͺ��̽��� ����
		 * 3. SQL �� ����
		 * 4. �����ͺ��̽� ���� �� ����
		 */
		try (Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
			String sql = "INSERT INTO dict VALUES(? , ?)";
			PreparedStatement pstmt = con.prepareStatement(sql); // �������� �˻�
			
			// ?�ڸ��� ���� ä�� ��, �������� �����غ�� SQL ���� �����϶�� ��û�ؾ���.
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			
			pstmt.executeUpdate(); // �����û
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void addWordToFile(String key, String value) {
		try (FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true);) {
			fWriter.write("\n" + key + "=" + value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
//		File file = new File("DIC_FILE_NAME");
//		System.out.println(file.getAbsolutePath());
		simple_DB dictPanel = new simple_DB();
		frame.add(dictPanel);
		frame.setTitle("����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
