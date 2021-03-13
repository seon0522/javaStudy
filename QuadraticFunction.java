package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuadraticFunction extends JPanel implements ActionListener{

//	ax^2 + bx + c  = y  //이차함수방정식
//	JTextField - 한줄의 텍스트를 만드는데 사용
	private JTextField aField, bField, cField;
	private double aCE = 1.0, bCE = -5.0, cCE=6.0; 
	
	public QuadraticFunction() {
//		2차 함수의 계소를 받아옴
//		문자열 초기화, 칸수 지정
		aField = new JTextField("1.0", 10);
		bField = new JTextField("-5.0", 10);
		cField = new JTextField("6.0", 10);
		
//		패널에 붙임
		this.add(aField);
		this.add(bField);
		this.add(cField);
		
		JButton drawButton = new JButton("Draw");
		this.add(drawButton);
		drawButton.addActionListener(this);
	}
	
//	그리는 컴포넌트
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawLine(100, 200, 500, 200);
		g2.drawLine(300, 0, 300, 400);
		
		g2.setPaint(Color.red);
		System.out.println("aCE : "+ aCE +"/ bCE : " +bCE + "/ cCE : " +cCE );
		
//		계산식
		for(int i = -20; i < 20; i++) {
			int x = i;
			int y = (int)(aCE * x * x - bCE * x + cCE);
			Shape s = new Ellipse2D.Float(300 + x - 2, 200 - y + 2, 4, 4);
			
			g2.fill(s);
			
		}
	}
	
//	버튼 클릭 시 발생 메소드 
	@Override
	public void actionPerformed(ActionEvent e) {
		//새로운 값 적용
		aCE = Double.parseDouble(aField.getText());
		bCE = Double.parseDouble(bField.getText());
		cCE = Double.parseDouble(cField.getText());
		
		System.out.println("aCE : "+ aCE +"/ bCE : " +bCE + "/ cCE : " +cCE );
		repaint();
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new QuadraticFunction());
		frame.setSize(600, 400);
		frame.setVisible(true);
		
	}
}


