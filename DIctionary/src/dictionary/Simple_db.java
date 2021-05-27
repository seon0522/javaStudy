package dbss;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Simple_db extends JPanel implements ActionListener {

// �ܾ� �Է� ���� �� �ִ� JTextField
// �˻� ��ư, �߰� ��ư
// �ܾ��� ������ ���� �ڷ� ������ Map ��ü ����

private JTextField inputField = new JTextField(30);
private JButton searchBtn = new JButton("�˻�");
private JButton addBtn = new JButton("�߰�");
private static final String JDBC_DRIVER =
"org.mariadb.jdbc.Driver";

// Map��ü�� �ܾ��� �������� ���
// key�� �ѱ۴ܾ�, value�� �����Ǵ� ���� �ܾ �����Ѵ�
private Map<String, String> words = new HashMap<>();
private static final String DIC_FILE_NAME = "dict.props";
// DB���� ���� URL ������ �ٸ���.
// ã�ƺ��ߵȴ�.
private static final String DB_SERVER_URL =
"jdbc:mariadb://localhost:3306/oop2";
private static final String DB_USER = "root";
private static final String DB_USER_PW="1111";


public Simple_db() {
// Panel�� �⺻ ���̾ƿ��� FlowLayout
this.add(inputField);
this.add(searchBtn);
this.add(addBtn);

// �� ��ư�� �۸� �̺�Ʈ�� �߻� ������ ó���� �����ʸ� ����
searchBtn.addActionListener(this);
addBtn.addActionListener(this);

this.setPreferredSize(new Dimension(600, 50));

// ���Ͽ� key=value ���·� ����� ��Ʈ������ �о, words�� �����ϱ�
// buildDictionaryFromFile();
buildDictionaryFromDB();
}

private void buildDictionaryFromDB() {
/*
* 1. Databse server�� �����Ѵ�.
*   a. JDBC ����̹��� �޸𸮿� �ε�(����)
*   b. DriverManager (java.sql ��Ű���� ���ǵ� Ŭ����)
*       Connection con = DriverManager.getConnection();
*       �޼��� ȣ���� ������ establish
*       �� �� ���� ������ getConnection() �޼��忡 ��������� ��.
*       ��������: DB Server�� URL
*          => (ip �ּ�, port ��ȣ, �����ͺ��̽� �̸�)
*           db ������� ���̵�� ��ȣ
* 2. Connection ��ü�� ���� SQL�� ������ ������ ��û�ϰ�
*    �� ����� �޾� ó���Ѵ�.
*    ũ�� �� ���� ����� �ִ�.
*    ù °�� con.createStatement() �޼ҵ� ȣ���� ���ؼ�
*    ��ȯ�Ǵ� Statement ��ü�� �̿��ϴ� ��� (���� SQL ��)
*     ���� SQL�� : ���α׷��� ������ ������ SQL�� �����ǰ� ������ ���.
*       select * from dict;
*    �� ��² con.prepareStatement() �޼��� ȣ���� ���ؼ�
*    ��ȯ�Ǵ� PreparedStatement ��ü�� �̿��ϴ� ��� (���� SQL ��)
*     ���� SQL��: ���α׷��� ������ ������ SQL�� �������� �ʰ�
*               ����Ǵ� SQL��
*               select * from dict where han = ?
*   �� �������� PreparedStatement ��ü�� �̿��Ѵ�.
*     String sql = "select * from dict";
*     PreparedStatement pstmt = con.prepareStatement(sql);
*     // ���� �غ� �� PreparedStatement�� �����Ű�� ����� ũ�� 2����
*     ù ��°: ������ SQL ���� insert, delete, �Ǵ� update ���� ���
*     "insert into ..."
*     "delete from dict..."
*     "update set eng = ... from ..."
*    
*     pstmt.executeUpdate();
*    
*     "select .... "
*     �� ��°: ������ SQL ���� select ���� ���.
*     ResultSet rs =  pstmt.executeQuery();
*    
*  3. DB Server���� ������ ����(close)�Ѵ�.
*     con.close();
*/
// MySQL JDBC ����̹��� �޸𸮿� ����
// ����̹� Ŭ���� �̸��� DBMS���� �ٸ���.
try {
Class.forName(JDBC_DRIVER);
} catch (Exception e) {
System.out.println(e.getMessage());
return;
}

// DB ������ ����
try(Connection con =
DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW))
{

// SELECT �� ����.
String sql = "select * from dict";
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();
while(rs.next()) {
// ���� �����Ͱ� ����Ű�� Į�� ���� ������ ��.
// �� Į���� Ÿ�Կ� ����. ȣ���� �޼��尡 �޶�����.
// ���� �� char, varchar Ÿ���� Į����
// getString("Į���̸�" �Ǵ� "Į�� ��ġ");
// int Ÿ���� Į����  getInt(...);
// DateTime, Date Ÿ���� Į�� ����
// getDate();
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
// Properties��
// key, value�� Ÿ���� ���� String, String����
// ������ ������ Map�̴�.
Properties props = new Properties();
// props.put("���", "apple");
// System.out.println(props.get("���"));

// ���Ͽ��� �о props ��ü�� <key, value>
// ���� ������ �� �ִ�.
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
// �Էµ� �ܾ �����Ͽ�
// �� �ܾ key ������ ������ �����ȴ� �� ��Ʈ���� �ִ��� �˻� words.get(�ܾ�);
// �� �ܾ �����Ǵ� ���� ������ JOptionPane.showMessageDialog() �޼��带 ȣ���ؼ� �����Ǵ� ���� �����ش�
// ���� ������ (null) JOptionPane.showMessageDialog() �޼��带 ȣ���ؼ�
// �ܾ ã�� �� �����ϴ� ��� ������ش�
// inputField�� �� ���ڿ��� �����Ѵ�

System.out.println("[" + key + "]");
String value = words.get(key);

if(value != null) {
// �����Ǵ� �ܾ �ִ� ���
JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
} else {
// �����Ǵ� �ܾ ���� ���
JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�.", key, JOptionPane.ERROR_MESSAGE);
}
} else if(e.getSource() == addBtn) {
// �Էµ� �ܾ ����
// JOptionPane.showInputDialog() �޼��带 ȣ���ؼ� �߰��� ���� �ܾ �Է� �޴´�
// words.put(�Է� �ʵ忡 �Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�)

String value = JOptionPane.showInputDialog(this, key+ "�� ���� �Ǵ� ���� �ܾ �Է��ϼ���.");

if(value.trim().length() == 0) return;
words.put(key, value);
words.put(value, key);

//addWordToFile(key, value);
addWordToDB(key, value);
JOptionPane.showMessageDialog(this, "[" + value + "]" + "���� �ܾ �߰��Ǿ����ϴ�.", key, JOptionPane.INFORMATION_MESSAGE);
}

// inputField.setText("");

}

private void addWordToDB(String key, String value) {
	/*1. ����̹� Ŭ������ �� �ѹ��� �޷θ��� �����ϸ� ��.
	 * �ٵ� �̹� ��ü�� ������ ��, �����ڿ��� ����Ǿ���.
	 * ���⼭�� ������ �ʿ䰡 ����
	 * 
	 * 2. �����ͺ��̽� ����
	 * 3. SQL �� ����  - prepare, statment �� �� �ϳ��� �ʿ�/ ���� ���ؼ��� �ʿ��� �غ� �ؾ���
	 * 4. �����ͺ��̽� ���� ����
	 * 
	 * */
	try(Connection con = DriverManager.getConnection(DB_SERVER_URL, DB_USER, DB_USER_PW)){
		String sql = "insert into dict values(?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//? �ڸ��� ���� ä�� �Ŀ�, �������� �����غ�� SQL���� �����϶�� ��û�ؾ� �Ѵ�.
		pstmt.setString(1, key);
		pstmt.setString(2, value);
		
		
		pstmt.executeUpdate();//�����û
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}

private void addWordToFile(String key, String value) {
try(FileWriter fWriter =
new FileWriter(DIC_FILE_NAME, true)) {
fWriter.write(key+"="+value+"\n");
// ���, apple
}catch(Exception e) {
System.out.println(e.getMessage());
}
}

public static void main(String[] args) {
JFrame frame = new JFrame();
frame.add(new Simple_db());

frame.setTitle("�ѿ�����");
frame.setResizable(false);
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
}
}