package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Paper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//1��° ����Ŭ����
class WelcomPanel extends JPanel{
	private JLabel message;
	
	public WelcomPanel() {
		message = new JLabel("�ڹ� ���ڿ� ���Ű��� ȯ���մϴ�.");
		this.add(message);
	}
}

public class PizzaOrer extends JFrame implements ActionListener{

	private int sum, Type, Topping, Size;
	
	private JButton orderBtn, cancelBtn;
	private JPanel orderPanel;
	private JTextField priceField; // ���⿡ �� ������ ������
	
	JPanel welcomPanel = new WelcomPanel();
	
	JPanel typePanel = new TypePanel();
	JPanel toppingPanel= new ToppingPanel();
	JPanel sizePanel = new SizePanel();
	
	
//	����Ŭ����1
	class TypePanel extends JPanel implements ActionListener{
		
		private JRadioButton combo, potato, bulgogi;
		private ButtonGroup btnGroup;
		
		public TypePanel() {
			
			this.setLayout(new GridLayout(3, 1));
			
			combo = new JRadioButton("�޺�", true);
			potato = new JRadioButton("��������");
			bulgogi = new JRadioButton("�Ұ��");
			
			combo.addActionListener(this);
			potato.addActionListener(this);
			bulgogi.addActionListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(combo);
			btnGroup.add(potato);
			btnGroup.add(bulgogi);
			
			this.setBorder(BorderFactory.createTitledBorder("����"));
			
			this.add(combo);
			this.add(potato);
			this.add(bulgogi);

		}
		
//		���� ��ư�� ����
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
	
//	����Ŭ���� 2
	class ToppingPanel extends JPanel implements ActionListener{
		private JRadioButton pepper, cheese, peperoni, bacon;
		
		private ButtonGroup btnG;
		
		public ToppingPanel() {
			// TODO Auto-generated constructor stub
			this.setLayout(new GridLayout(4, 1));
			
			pepper = new JRadioButton("�Ǹ�", true);
			cheese = new JRadioButton("ġ��");
			peperoni = new JRadioButton("���۷δ�");
			bacon = new JRadioButton("������");
			
			pepper.addActionListener(this);
			cheese.addActionListener(this);
			peperoni.addActionListener(this);
			bacon.addActionListener(this);
			
			btnG = new ButtonGroup();
			btnG.add(pepper);
			btnG.add(cheese);
			btnG.add(peperoni);
			btnG.add(bacon);
			
			this.setBorder(BorderFactory.createTitledBorder("�߰�����"));
			
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
	
//	����Ŭ����3
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
		    
		    this.setBorder(BorderFactory.createTitledBorder("ũ��"));
		    
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
		this.setTitle("���� �ֹ���");
	
		
		orderBtn = new JButton("�ֹ�");
		orderBtn.addActionListener(this);
		
		cancelBtn = new JButton("���");
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
			
//			�ؽ�Ʈ �ʵ�â ����ǥ��
			priceField.setText(String.valueOf(sum));
//			consoleȮ�κκ�
			System.out.println("Type: "+ changedPrice[0] +",Topping: "+ changedPrice[1] +",Size: " + changedPrice[2]);
			System.out.println("�� ���� : " + sum);
			
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
