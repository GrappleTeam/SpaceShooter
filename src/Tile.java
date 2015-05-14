import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private int x,y;
	
	Tile(int x, int y, BufferedImage s){
		this.x = x;
		this.y = y;
		this.image = s;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public void setX(int x){this.x=x;}
	public void setY(int y){this.y=y;}
	
	public BufferedImage getImage(){return image;}
	public int getWidth() {return image.getHeight();}
	public int getHeight() {return image.getHeight();}
	public void setImage(BufferedImage s){this.image=s;}
	
}
