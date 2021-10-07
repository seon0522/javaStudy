package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ComboBox extends JFrame implements ActionListener{

	private JLabel label;
	private JComboBox animalList;
	
	public ComboBox() {
//		창에 대한 설정
		this.setTitle("동물 콤보박스");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		
		
//		콤보박스 만들기
		String[] animals = {"dog","cat","rabit"};
		animalList = new JComboBox(animals);
		animalList.setSelectedIndex(0);
		animalList.addActionListener(this);
		
		
//		선택된 동물 변경
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER); //수평정렬
		changePicture(animals[animalList.getSelectedIndex()]); //배열을 선택해서 매소드에 인자값 넘김
		
	
		this.add(animalList, BorderLayout.NORTH);
		this.add((label), BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
//  새롭게 설정될 시 액션
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = (String)animalList.getSelectedItem();
		changePicture(name);
		
	}
	
//	이름에 따른 아이콘 가져오기
	protected void changePicture(String name) {
		ImageIcon icon = new ImageIcon(name + ".gif");
		label.setIcon(icon);
		
		label.setText(null);
	}
	
	public static void main(String[] args) {
		new ComboBox();
	}
	
}
