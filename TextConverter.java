package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class TextConverter extends JFrame implements ActionListener {

	private JButton converBtn;
	private JButton cancelBtn;
	private JTextArea textIn;
	private JTextArea textOut;
	
	public TextConverter() {
//		�ڵ� �ٹٲ�
		textIn = new JTextArea(10, 14);
		textOut = new JTextArea(10, 14);
		
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEditable(false);
		
		JPanel textAreaJPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaJPanel.add(textIn);
		textAreaJPanel.add(textOut);
		
		converBtn = new JButton("��ȯ");
		cancelBtn = new JButton("���");
		
		converBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		JPanel btnJPanel = new JPanel();
		btnJPanel.add(converBtn);
		btnJPanel.add(cancelBtn);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(textAreaJPanel,BorderLayout.CENTER);
		mainPanel.add(btnJPanel, BorderLayout.SOUTH);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.add(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setTitle("�ڹ� ������");
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		��ȯ��ư�� Ŭ���Ǿ��ٸ�
			���� textArea(textin)�� �ؽ��� �о
			����� ��ȯ�ϰ� �� ��ȯ�� ����� 
			���� textArea(textout)�� append
		
		��� ��ư�� Ŭ�� �Ǿ��ٸ�
			���� textarea(textout)�� �� ���ڿ��� ����
		*/
		
		if(e.getSource() == converBtn) {
			String str = textIn.getText();
			String result = toEnglish(str);
			textOut.setText(result);
			System.out.println("��ȯ");
			
		}else if(e.getSource() == cancelBtn){
			textOut.setText("");
			System.out.println("���");
			
		}else {
			System.out.println("�� �� ");
		}
	}
	
	private String toEnglish(String Korean) {
/*			korean ���ڿ��� ����� ��ȯ�ؼ� ��ȯ..
 * 			�ؽ�Ʈ -> text
 * 			���� => english
 * */
		
		String result = null;
		
//		������ �ٲٴ� ���� �ƴ϶� �ּҰ��� �ٲ���
		result = Korean.replace("�ؽ�Ʈ", "text");
		result = result.replace("����", "english");

		return result;
	}
	
	public static void main(String[] args) {
		new TextConverter();
	}
}
