package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Paper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//1번째 내부클래스
class WelcomPanel extends JPanel{
	private JLabel message;
	
	public WelcomPanel() {
		message = new JLabel("자바 피자에 오신것을 환영합니다.");
		this.add(message);
	}
}

public class PizzaOrer extends JFrame implements ActionListener{

	private int sum, Type, Topping, Size;
	
	private JButton orderBtn, cancelBtn;
	private JPanel orderPanel;
	private JTextField priceField; // 여기에 총 가격이 떠야함
	
	JPanel welcomPanel = new WelcomPanel();
	
	JPanel typePanel = new TypePanel();
	JPanel toppingPanel= new ToppingPanel();
	JPanel sizePanel = new SizePanel();
	
	
//	내부클래스1
	class TypePanel extends JPanel implements ActionListener{
		
		private JRadioButton combo, potato, bulgogi;
		private ButtonGroup btnGroup;
		
		public TypePanel() {
			
			this.setLayout(new GridLayout(3, 1));
			
			combo = new JRadioButton("콤보", true);
			potato = new JRadioButton("포테이토");
			bulgogi = new JRadioButton("불고기");
			
			combo.addActionListener(this);
			potato.addActionListener(this);
			bulgogi.addActionListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(combo);
			btnGroup.add(potato);
			btnGroup.add(bulgogi);
			
			this.setBorder(BorderFactory.createTitledBorder("종류"));
			
			this.add(combo);
			this.add(potato);
			this.add(bulgogi);

		}
		
//		라디오 버튼에 따라서
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == combo) {
				Type = 2500;
			}else if(e.getSource() == potato) {
				Type = 3500;
			}else if(e.getSource() == bulgogi){
				Type = 4000;
			}
		}
	}
	
//	내부클래스 2
	class ToppingPanel extends JPanel implements ActionListener{
		private JRadioButton pepper, cheese, peperoni, bacon;
		
		private ButtonGroup btnG;
		
		public ToppingPanel() {
			// TODO Auto-generated constructor stub
			this.setLayout(new GridLayout(4, 1));
			
			pepper = new JRadioButton("피망", true);
			cheese = new JRadioButton("치즈");
			peperoni = new JRadioButton("페퍼로니");
			bacon = new JRadioButton("베이컨");
			
			pepper.addActionListener(this);
			cheese.addActionListener(this);
			peperoni.addActionListener(this);
			bacon.addActionListener(this);
			
			btnG = new ButtonGroup();
			btnG.add(pepper);
			btnG.add(cheese);
			btnG.add(peperoni);
			btnG.add(bacon);
			
			this.setBorder(BorderFactory.createTitledBorder("추가토핑"));
			
			this.add(pepper);
			this.add(cheese);
			this.add(peperoni);
			this.add(bacon);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == pepper) {
				Topping = 2500;
			}else if(e.getSource() == cheese) {
				Topping = 3000;
			}else if(e.getSource() == peperoni) {
				Topping = 3500;
			}else if(e.getSource() == bacon) {
				Topping = 4000;
			}
		}
		
	}
	
//	내부클래스3
	class SizePanel extends JPanel implements ActionListener{
		private JRadioButton Small, Medium, Large;
		private ButtonGroup btnG;
		
		public SizePanel() {
			this.setLayout(new GridLayout(3, 1));
			
			Small = new JRadioButton("Small", true);
			
			Medium = new JRadioButton("Medium");
			
			Large = new JRadioButton("Large");
			
//			
			Small.addActionListener(this);
			Medium.addActionListener(this);
			Large.addActionListener(this);
			
			
		    btnG = new ButtonGroup();
		    btnG.add(Small);
		    btnG.add(Medium);
		    btnG.add(Large);
		    
		    this.setBorder(BorderFactory.createTitledBorder("크기"));
		    
		    this.add(Small);
		    this.add(Medium);
		    this.add(Large);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == Small) {
				Size = 4000;
			}else if(e.getSource() == Medium) {
				Size = 5000;
			}else if(e.getSource() == Large) {
				Size = 6000;
			}
		}
	}
	
	public PizzaOrer() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("피자 주문서");
	
		
		orderBtn = new JButton("주문");
		orderBtn.addActionListener(this);
		
		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setColumns(6);
		
		orderPanel = new JPanel();
		orderPanel.add(orderBtn);
		orderPanel.add(cancelBtn);
		orderPanel.add(priceField)
		;
		
		this.setLayout(new BorderLayout());
		
		this.add(welcomPanel, BorderLayout.NORTH);
		this.add(orderPanel, BorderLayout.SOUTH);
		this.add(sizePanel, BorderLayout.EAST);
		this.add(typePanel, BorderLayout.WEST);
		this.add(toppingPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == orderBtn) {
			
			int[] changedPrice = {Type, Topping, Size};
			int[] originalPrice = {2500, 2500, 4000};
			
			if(sum != 0) sum = 0;
			
			for(int i = 0; i < changedPrice.length; i++) {
				if(changedPrice[i] == 0) {
					changedPrice[i] = originalPrice[i];
				}
				
				sum += changedPrice[i];
			}
			
//			텍스트 필드창 가격표시
			priceField.setText(String.valueOf(sum));
//			console확인부분
			System.out.println("Type: "+ changedPrice[0] +",Topping: "+ changedPrice[1] +",Size: " + changedPrice[2]);
			System.out.println("총 가격 : " + sum);
			
		}else if(e.getSource() == cancelBtn) {
			
			Type = 0;
			Topping = 0;
			Size = 0;
			sum = 0;
			priceField.setText(String.valueOf(sum));
		}
	}
	
	public static void main(String[] args) {
		new PizzaOrer();
	}
	
}
