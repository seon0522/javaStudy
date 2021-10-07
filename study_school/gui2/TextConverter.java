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
//		자동 줄바꿈
		textIn = new JTextArea(10, 14);
		textOut = new JTextArea(10, 14);
		
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEditable(false);
		
		JPanel textAreaJPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaJPanel.add(textIn);
		textAreaJPanel.add(textOut);
		
		converBtn = new JButton("변환");
		cancelBtn = new JButton("취소");
		
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
		
		this.setTitle("자바 번역기");
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		변환버튼이 클릭되었다면
			좌측 textArea(textin)의 텍스를 읽어서
			영어로 변환하고 그 변환된 결과를 
			우측 textArea(textout)에 append
		
		취소 버튼이 클릭 되었다면
			우측 textarea(textout)을 빈 문자열로 설정
		*/
		
		if(e.getSource() == converBtn) {
			String str = textIn.getText();
			String result = toEnglish(str);
			textOut.setText(result);
			System.out.println("변환");
			
		}else if(e.getSource() == cancelBtn){
			textOut.setText("");
			System.out.println("취소");
			
		}else {
			System.out.println("그 외 ");
		}
	}
	
	private String toEnglish(String Korean) {
/*			korean 문자열을 영어로 변환해서 반환..
 * 			텍스트 -> text
 * 			영어 => english
 * */
		
		String result = null;
		
//		원본을 바꾸는 것이 아니라 주소값을 바꿔줌
		result = Korean.replace("텍스트", "text");
		result = result.replace("영어", "english");

		return result;
	}
	
	public static void main(String[] args) {
		new TextConverter();
	}
}
