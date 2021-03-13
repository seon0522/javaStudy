package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaDemo extends JFrame implements ActionListener{
	
	private JTextField textField;
	private JTextArea textArea;
	
	
	public TextAreaDemo() {
//		������ ��ü�� Ÿ��Ʋ text area Deom�� ����
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		���� ���� ��
		textField = new JTextField(30);
		textField.addActionListener(this);
		
//		���� ���� ǥ��
		textArea = new JTextArea(10, 30);
		textArea.setEditable(false);
		this.add(textField, BorderLayout.NORTH);
		
		
//		textArea���� �ް� ��ũ�� �����
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.pack(); //�����ӿ� �ڵ������� ũ�⸦ ����
		this.setVisible(true);
	}
	
	
	
	
	//��ư Ŭ�� �� �߻��Ǵ� �׼� -> ������� �ؽ�Ʈ �ʵ��� ���ڸ� text�� ����
	public void actionPerformed(ActionEvent e) {
//		�ؽ�Ʈ �ʵ��� ���ڸ� text�� ���� 
		String text = textField.getText();
		textArea.append(text + "\n");
		
//		�߰� �� ��� ����
		textField.selectAll();
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	
	public static void main(String[] args) {
		new TextAreaDemo();
	}
}
