import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Mob extends Mover {
  
  //Primary attributes
  protected String name;
  protected int health;
  protected int damage;
  private URL jumpsound = this.getClass().getResource("sounds/Jump Sound 3 quieter.wav");
  public int state = 0;
  protected int jumpHeight = 7;
  public int distanceToGun;
  
  public Mob(String name, int damage, int health, int x, int y){
	  this.name = name;
	  this.damage = damage;
	  this.health = health;
	  this.x = x;
	  this.y = y;
  }
  
  //Primary attributes
  public String getName()	{return this.name;};
  public int getHealth()	{return this.health;};
  public void setName(String s)	{this.name = s;}
  public void setHealth(int h)	{this.health = h;}

  public BufferedImage getImage(String imageUrl){
		try {
			return ImageIO.read(DisplayPanel.class.getResource(imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
  
  public int getDistance(int x, int y, int otherX, int otherY){
	  return (int) -Math.sqrt(((otherX-x)*(otherX-x))+((otherY-y)*(otherY-y)));
  }
  public int getCircleProjection(int x, int y, int otherX, int otherY, int r){
		//returns the coordinate of the projection onto a circle of radius r, centered at x, y.
		//returns the coordinate for the first number entered, so to get the y coordinate, input y first
		return (int)(x+r*(
				(x-otherX)/
					Math.sqrt(
							((otherX-x)*(otherX-x))+((otherY-y)*(otherY-y))
					)
				)
			);
	}
  public void pattern(){ move(); }
  
}//end main