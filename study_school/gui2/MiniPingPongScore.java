package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 돌리는 건 main에서 하는데 main은 핑퐁이 가지겠다.

public class MiniPingPongScore extends JPanel implements KeyListener {
	
	private BallScore ball;
	protected RacquetScore racquet1;
	protected RacquetScore racquet2;
	protected Score score;
	
//	라켓에 번호를 부여해서 1번이면 w 2번이면 s에 맞추게 하기
	public MiniPingPongScore() {
		ball = new BallScore(this, Color.red);
		racquet1 = new RacquetScore(this, 10, 0, Color.blue,1);
		racquet2 = new RacquetScore(this, 10, 575, Color.yellow, 2);
		
		score = new Score(600, 400);
		
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
//		각 클래스의 로직돼로
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
		score.draw(g2d);
	}
	
//	프레임은 따로 안 만들었으니까
	public static void main(String[] args) {
		JFrame frame = new JFrame("MiniPingPongScore");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(frame);
		frame.setResizable(false);
		
//		핑퐁생성
		MiniPingPongScore game = new MiniPingPongScore();
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
	
	public class Score{
		private int GAME_WIDTH;
		private int GAME_HEIGHT;
		protected int player1 = 0;
		protected int player2 = 0;
		
		public Score(int gameWidth, int gameHeight) {
			GAME_WIDTH = gameWidth;
			GAME_HEIGHT = gameHeight;
		}
		public void getScore(int player1, int player2) {
			this.player1 = player1;
			this.player2 = player2;
		}
		public void draw(Graphics g) {
			
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
			g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
			
			g.setColor(Color.white);
			g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), GAME_WIDTH/2 - 85, 50);
			g.setColor(Color.white);
			g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), GAME_WIDTH/2 + 20, 50);
		}
	}
	
	
//공
	class BallScore extends Rectangle {
		private static final int RADIUS = 20;
		private int x = 0, y = 0, xSpeed = 1, ySpeed = 1;
		private MiniPingPongScore game;
		private Color color;
		
		public BallScore( MiniPingPongScore game, Color color ) {
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
	

	class RacquetScore{
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int id;
		
		private int x = 0, y =0;
		
//		private int xSpeed = 0;
		private int ySpeed = 0;
		private MiniPingPongScore game;
		private Color color;
		
		public RacquetScore(MiniPingPongScore game, int x, int y, Color color, int id) {
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
