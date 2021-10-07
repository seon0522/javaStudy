package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

public class SlideDEmo extends JFrame implements ChangeListener{ 
	
	static final int INIT_VALUE = 15;
//	JSlider - 사용자가 특정한 범위 안에서 하나의 값을 선택할 수 있는 컴포넌트
	private JSlider slider;
	private JButton btn;
	
	public SlideDEmo() {
		
	JPanel panel;
	this.setTitle("슬링더테스트");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	
//	레이블, 버튼 같은
//	패널에 컴포넌트 추가 -> 패널을 프레임에 추가
	
	panel = new JPanel();
	
//	읽기 전용 텍스트를 표시하기 위한 컴포넌트 - JLabel()
	JLabel label = new JLabel("슬라이더를 움직여 보세요", SwingConstants.CENTER);
	panel.add(label);
	
	slider = new JSlider(0,30,INIT_VALUE);
	slider.setMajorTickSpacing(10);
	slider.setMinorTickSpacing(1);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	
// 슬라이더 값이 변경되었을 경우, 이벤트를 받고 싶으면  슬라이더에 changeListener를 추가
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
	
	
//	슬라이더 상태가 변경되면 호출됨, 버튼의 크기를 변경
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
