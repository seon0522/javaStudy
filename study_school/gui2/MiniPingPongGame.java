package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//나 교수님 따라했는데.. 왜 오류나냐?...
// 돌리는 건 main에서 하는데 main은 핑퐁이 가지겠다.

public class MiniPingPongGame extends JPanel implements KeyListener {
	
	private Ball ball;
	protected Racquet racquet1;
	protected Racquet racquet2;
	
//	라켓에 번호를 부여해서 1번이면 w 2번이면 s에 맞추게 하기
	public MiniPingPongGame() {
		
		ball = new Ball(this, Color.red);
		racquet1 = new Racquet(this, 10, 0, Color.blue,1);
		racquet2 = new Racquet(this, 10, 575, Color.yellow, 2);
		this.setBackground(Color.black);
		
//		코멘트 처리하면 이벤트 처리 안 되는가 확인
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
//	누르면 라켓한테 알려주기
	public void keyPressed(KeyEvent e) {
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.KeyReleased(e);
		racquet2.KeyReleased(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
//	공.움직임
	private void move() {
//		각 클래스의 로직되로
	ball.move();
	racquet1.move();
	racquet2.move();
}
	
//	패널이 생성되면 공과 라켓을 내것으로 만들기
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
	}
	
//	프레임은 따로 안 만들었으니까
	public static void main(String[] args) {
		JFrame frame = new JFrame("PingPong Game");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
//		핑퐁생성
		MiniPingPongGame game = new MiniPingPongGame();
		frame.add(game);
		frame.setVisible(true);
		while(true) {
			game.move();
			game.repaint();
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
//공
	class Ball extends Rectangle {
		private static final int RADIUS = 20;
		private int x = 0, y = 0, xSpeed = 1, ySpeed = 1;
		private MiniPingPongGame game;
		private Color color;
		
		public Ball(MiniPingPongGame game, Color color ) {
			this.game = game;
			this.color = color;
		}
		
		void move() {
			//위
			if( x + xSpeed < 0)
				xSpeed = 1;
			//왼쪽
			if( x + xSpeed > game.getWidth() - 2 * RADIUS ) 
				xSpeed = -1;
			//아래
			if( y + ySpeed < 0) 
				ySpeed = 1;
//			오른쪽
			if( y + ySpeed > game.getHeight() - 2 * RADIUS ) 
				ySpeed = -1;
			
			if(collision()) 
				xSpeed = -xSpeed;
			
			x += xSpeed;
			y += ySpeed;
		}
		
		private boolean collision() {
			Rectangle r1 = game.racquet1.getBounds();
			Rectangle r2 = game.racquet2.getBounds();
			Rectangle myr = getBounds();
			
			boolean r1c = r1.intersects(myr); 
			boolean r2c = r2.intersects(myr);
			
			return r1c || r2c;
		}
		
//		그 위치에 그려주면 됨
		private void draw(Graphics2D g) {
			g.setColor(color);
			g.fillOval(x, y, 2 * RADIUS , 2 * RADIUS);
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, 2*RADIUS, 2*RADIUS);
		}
		

	}
	

	class Racquet{
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int id;
		
		private int x = 0, y =0;
		
//		private int xSpeed = 0;
		private int ySpeed = 0;
		private MiniPingPongGame game;
		private Color color;
		
		public Racquet(MiniPingPongGame game, int x, int y, Color color, int id) {
			this.game = game;
			this.x = x;
			this.x = y;
			this.color = color;
			this.id = id;
		}
		
		public void move() {
			if(y + ySpeed > 0 && y + ySpeed < game.getHeight() - HEIGHT)
				y += ySpeed;
		}
		
		private void draw(Graphics2D g) {
			g.setColor(color);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
		
		public Rectangle getBounds() {
			return new Rectangle (x,y,WIDTH, HEIGHT);
			
		}
		
		public void keyPressed(KeyEvent e){
			if(id == 2) {
				if(e.getKeyCode() == KeyEvent.VK_UP)
					ySpeed = -3;
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					ySpeed = 3;
			}else {
				if(e.getKeyCode() == KeyEvent.VK_W)
					ySpeed = -3;
				else if(e.getKeyCode() == KeyEvent.VK_S)
					ySpeed = 3;
			}
//			위쪽 화살표인지, 아래쪽인지 확인
			
		}
		
		private void KeyReleased(KeyEvent e) {
			ySpeed = 0;

		}
		
	}
	
	
}
