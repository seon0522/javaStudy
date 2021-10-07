package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooser extends JFrame implements ActionListener{


	private JButton openBtn, saveBtn;
	JFileChooser fileChooser;

	

	public FileChooser() {

//		창만들기
		this.setTitle("파일 선택기");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 200);
		fileChooser = new JFileChooser();
		
//		label을 지역변수로 만듦 - 다른 메소드에서 접근할 일이 없기에 그냥 만듦
		JLabel label = new JLabel("파일선택기 컴포넌트 테스트입니다.");
		
		openBtn = new JButton("파일 오픈");
		openBtn.addActionListener(this);
		
		saveBtn = new JButton("저장 버튼");
		saveBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(openBtn);
		panel.add(saveBtn);
		
		this.add(panel);
		this.setVisible(true);
		
//		패널을 만들어서
//		라베넣고
//		버튼 넣고
		
//		그리기
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openBtn) {
//			파일을 보여주는 선택기
			int returnVal = fileChooser.showOpenDialog(this);
			
//			파일 선택 후 확인 버튼을 눌렀을 경우
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				System.out.println("파일 경로 : " + file.getAbsolutePath() + "선택");
			}else {
				System.out.println("파일을 선택하지 않았습니다.");
			}
			
		}else if(e.getSource() == saveBtn) {
//			파일을 저장하는 선택기
			int returnVal = fileChooser.showSaveDialog(this);
			
//			파일 저장 후 저장 버튼을 눌렀을 경우
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				System.out.println("저장할 파일 : " + file.getAbsolutePath() + "저장");
			}else {
				System.out.println("저장 안함");
			}
			
			
		}
	}
	
	
	public static void main(String[] args) {
		new FileChooser();
	}
}
