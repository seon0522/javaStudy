package GUI;

//5�� 6�� ~ 5�� 10��
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//�ܾ �߰��ص� ���α׷��� �����ϸ� �޸��� �����Ͱ� �����
//�׷��� Ȯ�� - �����ͺ��̽��� ����. ���α׷� �� �� �����ͺ��̽����� �о�ͼ� ���� ����
//DB�� docker�� �̿��ؼ� ��.���α׷��� ���̺귯�� ���� ��Ű¡�ؼ� �̹���ȭ�� �� docker 


//key-value �� �ʿ��ٰ� ����. 
//simple�� ������ �� �ڱⰡ ����̴ϱ� ���� ��ư�� �ʵ带 �ٷ� ���̱�
public class SimpleDictionary extends JPanel implements ActionListener{
/*	�ʿ��� �� : �ܾ� �Է� ���� �� �ִ� JTextField, �˻���ư, �߰���ư, �ܾ��� ������ ���� �ڷᱸ�� Map��ü   */
	
	private JTextField inputField = new JTextField(30); //30�÷�¥�� �ؽ�Ʈ�ʵ�
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	
	//Map ��ü�� �ܾ��� ���� ����ϱ�
	//Key, Value ������ ����, key�� �ѱ۴ܾ�, value�� �����Ǵ� ����ܾ�
	private Map<String, String> words = new HashMap<>();//hashMap, TreeMap, linkedhashMap
	private static final String DIC_FILE_NAME = "doct.props";

	public SimpleDictionary() {
//		panel�� �⺻ ���̾ƿ��� : FlowLayout - ���帣�� ���̰� �ڸ������ϸ� �ؿ� ���̰�. 
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
/*		setSize - ������Ʈ�� ũ�⸦ �����ϴ� �޼ҵ� 
 * 		/ flowLayout���� ũ�� ���� �Ұ�
		vs
		setPreferredSize -  �޼ҵ�� Dimension��ü�� ���ڷ� �����鼭 
		�ش� ������Ʈ�� �⺻ũ�⸦ ���� / flowLayout���� ũ�� ���� ����*/
		this.setPreferredSize(new Dimension(600, 50));
		
//		1. searchBtn, addBtn�� Ŭ�� �̺�Ʈ�� �߻����� �� ó���� ������ �����ϱ�
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
		
//		���Ͽ� key=value ���·� ����� ��Ʈ������ �о, words�� �����ϱ�
		buildDictionaryFromFile();
	}
	
	private void buildDictionaryFromFile() {
//		Properties�� key, value�� Ÿ���� ���� String, String���� ������ ������ map�̴�.
		Properties props = new Properties();
		
//		props.put("���", "apple");
//		System.out.println(props.get("���"));
		
//		���Ͽ��� �о props ��ü�� <key, value> ���� ������ �� �ִ�.
		File file = new File(DIC_FILE_NAME);
		System.out.println(file.getAbsolutePath());
		FileReader fReader = null;
		try {
		fReader = new FileReader(DIC_FILE_NAME);
		props.load(fReader); // �ε� �غθ�
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
//			�ݱ� �ʼ� - �ٵ� close�� �ϸ� �� ���� �߻� �׷��� try with �� �̿��� �̿��ϸ� ���� - try() catch{}  
			try {
				fReader.close();
			}catch(Exception ignore) {}
		}
		
		Set<Object> set = props.keySet();
		for(Object obj : set) {
			words.put((String)obj,(String)props.get(obj)); // words�� String���� �޴µ� obj���� ��ȯ�ϸ� ���� �� �ִ�.
		}
		
	}
	
	public static void main(String[] args) {
		
		File file = new File("doct.props");
		System.out.println(file.getAbsolutePath());
		
//		���� ���̰� �ϱ� ���ؼ��� �������� �־�� ��.
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel); //�̷��Ը� �Ѵٰ� ������ ����
		frame.setTitle("���� �ѿ�����");
		
		frame.setVisible(true); //���̰� �ϱ� ����
		frame.pack(); //��ȣ�Ǵ� ����� �� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ŭ�� �� �۵��ǰ�
		frame.setResizable(false); //flow�� ��������ϱ� ������ ������ �ʵ��� �������� �� �ϰ� ����
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello");
		
		
//		1.�Էµ� �ܾ� �߭� - inputField���� 
		String key = inputField.getText();
		
//		1-1. ������ ��� - trim() - ��������
		if(key.trim().length() == 0) return;
		
		if(e.getSource() == searchBtn) {	
			System.out.println("searchBtn");
	
				System.out.println(key);
				System.out.println(e.getSource());
			 /*2. �ܾ�� �����Ǵ� key�� �� ��Ʈ���� �ִ��� �˻� -> dict.get(�ܾ�);
			 * 3. JOptionPane.showMessageDialog() ȣ���ؼ�
			 * �ܾ�� �����Ǵ� ���� ������ �����Ǵ� ���� �����ְ�
			 * �ܾ�� �����Ǵ� ���� ������(null��ȯ) '�ܾ ã�� �� �����ϴ�.'���
			 */
			String value = words.get(key);
			if(value != null) {
				JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�."
											, key, JOptionPane.ERROR_MESSAGE);
			}

		}else if(e.getSource() == addBtn) {
			System.out.println("addBtn");
			 /* 2. JOptionPane.showinputDialog() ȣ���ؼ�
			 * 3. �߰��� ���� �ܾ� �Է� ����
			 * 4. dict.put(�Է��ʵ忡 �Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�);
			 */
			
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ���� �ܷ��� �Է��ϼ���");
			if(value.trim().length() == 0) return;
			words.put(key, value);
			addWordToFile(key, value);
			JOptionPane.showMessageDialog(this, value + "����ܾ �߰��Ǿ����ϴ�." 
											,key, JOptionPane.INFORMATION_MESSAGE);
		}
//		4. inputField�� �� ���ڿ��� ����
		inputField.setText("");
	}
	
	private void addWordToFile(String key, String value) {
		
//		�ݱ� �ʼ�
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true) ){
			// Exception�� �߻��� �� �ְ�, �� ���� close �� ��� ��
			fWriter.write(key + "=" + value + "\n"); //�߰��ؾ� �ϴµ� ���� ��. �� ��..
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
