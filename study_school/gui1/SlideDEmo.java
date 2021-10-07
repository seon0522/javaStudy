package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

public class SlideDEmo extends JFrame implements ChangeListener{ 
	
	static final int INIT_VALUE = 15;
//	JSlider - ����ڰ� Ư���� ���� �ȿ��� �ϳ��� ���� ������ �� �ִ� ������Ʈ
	private JSlider slider;
	private JButton btn;
	
	public SlideDEmo() {
		
	JPanel panel;
	this.setTitle("�������׽�Ʈ");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	
//	���̺�, ��ư ����
//	�гο� ������Ʈ �߰� -> �г��� �����ӿ� �߰�
	
	panel = new JPanel();
	
//	�б� ���� �ؽ�Ʈ�� ǥ���ϱ� ���� ������Ʈ - JLabel()
	JLabel label = new JLabel("�����̴��� ������ ������", SwingConstants.CENTER);
	panel.add(label);
	
	slider = new JSlider(0,30,INIT_VALUE);
	slider.setMajorTickSpacing(10);
	slider.setMinorTickSpacing(1);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	
// �����̴� ���� ����Ǿ��� ���, �̺�Ʈ�� �ް� ������  �����̴��� changeListener�� �߰�
	slider.addChangeListener(this);
	panel.add(slider);
	
	btn = new JButton(" ");
	ImageIcon icon = new ImageIcon("dog.gif");
	btn.setIcon(icon);
	btn.setSize(INIT_VALUE * 10 ,INIT_VALUE * 10);
	panel.add(btn);
	
	this.add(panel);
	this.setSize(300, 500);
	this.setVisible(true);
		
	}
	
	
//	�����̴� ���°� ����Ǹ� ȣ���, ��ư�� ũ�⸦ ����
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if(!source.getValueIsAdjusting()) {
			int value = source.getValue();
			btn.setSize(value * 10, value * 10);
		}
		
	}
	
	public static void main(String[] args) {
		new SlideDEmo();
	}

}
