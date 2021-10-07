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

//		â�����
		this.setTitle("���� ���ñ�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 200);
		fileChooser = new JFileChooser();
		
//		label�� ���������� ���� - �ٸ� �޼ҵ忡�� ������ ���� ���⿡ �׳� ����
		JLabel label = new JLabel("���ϼ��ñ� ������Ʈ �׽�Ʈ�Դϴ�.");
		
		openBtn = new JButton("���� ����");
		openBtn.addActionListener(this);
		
		saveBtn = new JButton("���� ��ư");
		saveBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(openBtn);
		panel.add(saveBtn);
		
		this.add(panel);
		this.setVisible(true);
		
//		�г��� ����
//		�󺣳ְ�
//		��ư �ְ�
		
//		�׸���
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openBtn) {
//			������ �����ִ� ���ñ�
			int returnVal = fileChooser.showOpenDialog(this);
			
//			���� ���� �� Ȯ�� ��ư�� ������ ���
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				System.out.println("���� ��� : " + file.getAbsolutePath() + "����");
			}else {
				System.out.println("������ �������� �ʾҽ��ϴ�.");
			}
			
		}else if(e.getSource() == saveBtn) {
//			������ �����ϴ� ���ñ�
			int returnVal = fileChooser.showSaveDialog(this);
			
//			���� ���� �� ���� ��ư�� ������ ���
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				System.out.println("������ ���� : " + file.getAbsolutePath() + "����");
			}else {
				System.out.println("���� ����");
			}
			
			
		}
	}
	
	
	public static void main(String[] args) {
		new FileChooser();
	}
}
