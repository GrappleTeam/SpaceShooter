import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DisplayPanel extends JPanel implements KeyListener, Runnable, MouseListener, MouseMotionListener{
	
	static int currentLevel;
	final int maxLevel = 5;
	Mob character;
		
	boolean upButtonPressed = false;
	boolean leftButtonPressed = false;
	boolean rightButtonPressed = false; 
	boolean downButtonPressed = false;
	boolean buttonReleased= false;
		
	static int mouseX;
	static int mouseY;	
	static boolean mousePressed = false;
		
		BufferedImage[] BackgroundImages = {
				getImage("graphics/sword-and-sworcery.png"),
			    getImage("graphics/windows_xp_bliss-wide.jpg"),
				getImage("graphics/whiteBackground.png"),
		};
		
	DisplayPanel(){
		character = new Player(0,0,20,20);
	}
	public BufferedImage getImage(String imageUrl){
		try {
			return ImageIO.read(DisplayPanel.class.getResource(imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void run(){
		
		processInput();
	
		character.move();
		character.checkWindowBoundaries(getWidth(), getHeight());
		
		repaint();
		
		try{Thread.sleep(18);}
		catch(InterruptedException e){
			System.out.println("Thread was interrupted.");
		}

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		g.drawImage(BackgroundImages[2],0,0,null);
		g.drawImage(character.getSprite(), character.getX(), character.getY(), this);
			
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
												
			case KeyEvent.VK_L:		DisplayFrame.levelChanged = true;	break;		
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
}

