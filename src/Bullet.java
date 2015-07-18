import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bullet extends Mover{
	
	  static Bullet bill;
	  static BufferedImage bulletImage;
	  
	  private Bullet(){}
	  
	  public static Bullet getBullet(int x, int y){ 
		  if(bill==null) bill = new Bullet();
		  bill.setX(x);
		  bill.setY(y);
		  bill.setYspeed(-7);
		  bill.setYfriction(0);
		  bulletImage = getImage();
		  return bill; 
	  }
	  
	  public static BufferedImage getImage(){
		try {
			return ImageIO.read(Bullet.class.getResource("graphics/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
