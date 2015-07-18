import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Game_Logic implements KeyListener, Runnable, MouseListener, MouseMotionListener{
	
	Display_Panel j;
	public enum gamestate { main_screen, title_screen, gameplay_screen }
	public gamestate current_gamestate = gamestate.main_screen;
	
	Mob character;
	boolean musicOn = true;
	boolean upButtonPressed = false;
	boolean leftButtonPressed = false;
	boolean rightButtonPressed = false; 
	boolean downButtonPressed = false;
	boolean buttonReleased= false;
	static ArrayList<Level> levelArray;
	static boolean mousePressed = false;
	static int mouseX;
	static int mouseY;
	static int currentLevel;
	int displacement;
	int cycle = 0;
	final int maxLevel = 5;
		
		BufferedImage[] BackgroundImages = {
				getImage("graphics/sword-and-sworcery.png"),
			    getImage("graphics/windows_xp_bliss-wide.jpg"),
				getImage("graphics/whiteBackground.png"),
		};
		
	Game_Logic(){
		j = new Display_Panel();
		j.setPreferredSize(new Dimension(900, 600));
		character = new Player(0,0,20,20);

		currentLevel = 0;
		levelArray = new ArrayList<Level>();
		levelArray.add(new Level(0, new Tile(0,0,getImage("graphics/whiteBackground.png"))));
	}
	public BufferedImage getImage(String imageUrl){
		try {
			return ImageIO.read(Game_Logic.class.getResource(imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void run(){
		
		processInput();
	
		character.move();
		character.checkWindowBoundaries(j.getWidth(), j.getHeight());
		
		for(Mob c: levelArray.get(currentLevel).getLevelMobs()){
			c.pattern();
			c.checkWindowBoundaries(j.getWidth(), j.getHeight());
		}
		
		j.repaint();
		
		try{Thread.sleep(18);}
		catch(InterruptedException e){
			System.out.println("Thread was interrupted.");
		}

	}
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//KEYLISTENER
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

		public void keyPressed(KeyEvent k) {
			switch(k.getKeyCode()){
			
			case KeyEvent.VK_W:		//-------------
			case KeyEvent.VK_UP:	upButtonPressed = true;		break;
			
			case KeyEvent.VK_A:		//-------------
			case KeyEvent.VK_LEFT:	leftButtonPressed = true;	break;
			
			case KeyEvent.VK_D:		//-------------
			case KeyEvent.VK_RIGHT: rightButtonPressed = true;	break;
			
			case KeyEvent.VK_S:		//-------------
			case KeyEvent.VK_DOWN: 	downButtonPressed = true;	break;
			
//			case KeyEvent.VK_SPACE:	character.setX(character.weaponArray.get(0).getHitX());
//									character.setY(character.weaponArray.get(0).getHitY());
//									character.setYspeed(0);
//									break;
			
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_Q:		System.exit(0);
												
			case KeyEvent.VK_L:		Display_Frame.levelChanged = true;	break;		
			default: break;
			}	
		}
		public void processInput(){
			if(upButtonPressed)		character.setYacc(-2);
			if(leftButtonPressed)	character.setXacc(-2);
			if(rightButtonPressed)	character.setXacc(2);
			if(downButtonPressed) 	character.setYacc(2);
		}	
		public void keyReleased(KeyEvent k) {
			switch(k.getKeyCode()){
			
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:	upButtonPressed = false;	
									break;
			
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:	leftButtonPressed = false;	
									character.setXacc(0);
									break;
			
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT: rightButtonPressed = false;	
									character.setXacc(0);
									break;
			
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN: 	downButtonPressed = false;	
									break;
			default: break;
			}	
		}
		public void keyTyped(KeyEvent k) {}
		
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//MOUSELISTENER
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		public void mousePressed(MouseEvent e)
		{
//			mousePressed = true;
//			mouseX = e.getX();
//			mouseY = e.getY();
//			character.weaponArray.get(0).setHitX(mouseX);
//			character.weaponArray.get(0).setHitY(mouseY);
//			character.tethered = true;
//			
//			character.distanceToGun = character.getDistance(character.getX(), character.getY(), character.weaponArray.get(0).getHitX(), character.weaponArray.get(0).getHitY());
//			e.consume();
		}
		public void mouseReleased(MouseEvent e){
			mousePressed = false;
			character.setYspeed(0);
			character.setXacc(0);
			character.setXspeed(0);
			mouseX = e.getX();
			mouseY = e.getY();
			character.tethered = false;
			e.consume();
		}
		public void mouseClicked(MouseEvent e)
		{
			mouseX = e.getX();
			mouseY = e.getY();
			e.consume();
		}
		public void mouseEntered(MouseEvent e){

		}
		public void mouseExited(MouseEvent e){

		}
		public void mouseDragged(MouseEvent e) {

		}
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			e.consume();
		}
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
		public JPanel getJPanel() { return j; }

		public class Display_Panel extends JPanel{
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				
			//draing the background
				g.drawImage(BackgroundImages[2],0,0,null);
				
			//drawing the mobs
				for(Mob m: levelArray.get(currentLevel).getLevelMobs())
					g.drawImage(m.getSprite(), m.getX(), m.getY(), this);
			
			//drawing the character
			g.drawImage(character.getSprite(), character.getX(), character.getY(), this);
				
			}
		}

}

