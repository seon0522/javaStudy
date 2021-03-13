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
//		생성된 객체의 타이틀 text area Deom로 설정
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		글자 적는 곳
		textField = new JTextField(30);
		textField.addActionListener(this);
		
//		적은 글자 표시
		textArea = new JTextArea(10, 30);
		textArea.setEditable(false);
		this.add(textField, BorderLayout.NORTH);
		
		
//		textArea값을 받고 스크롤 만들기
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.pack(); //프레임에 자동적으로 크기를 맞춤
		this.setVisible(true);
	}
	
	
	
	
	//버튼 클릭 시 발생되는 액션 -> 만들어진 텍스트 필드의 글자를 text에 저장
	public void actionPerformed(ActionEvent e) {
//		텍스트 필드의 글자를 text에 저장 
		String text = textField.getText();
		textArea.append(text + "\n");
		
//		추가 후 모두 선택
		textField.selectAll();
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	
	public static void main(String[] args) {
		new TextAreaDemo();
	}
}
