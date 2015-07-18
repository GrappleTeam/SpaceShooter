import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Mover {
	  protected int jumpable=0;
	  public int getJumpable(){return jumpable;}
	  public void incrementJumpable(){
		  jumpable+=1;
		  if(jumpable == 1){
			  //playJumpSound();
		  }
	  }
//Images======================================================
	  protected BufferedImage sprite;
	  public BufferedImage getSprite(){return sprite;}
	  public int getWidth(){return sprite.getWidth();}
	  public int getHeight(){return sprite.getHeight();}
//Positioning=================================================
	  protected int oldX, oldY;
	  protected int x,y;
	  protected boolean tethered = false;
	  protected boolean xlocked = false;
	  protected boolean ylocked= false;
	  
	  //Speed and acceleration
	  protected int xspeed = 0;
	  protected int yspeed = 0;
	  protected int yacc = 0;
	  protected int xacc = 0;
	  protected int topXSpeed = 15;
	  protected int topYSpeed = 15;
	  protected int gravity = 0;
	  protected int xfriction = 1;
	  protected int yfriction = 1;
	  
	  //Speed and acceleration
	  public int getX()			{return x;}
	  public int getY()			{return y;}
	  public int getXspeed()	{return xspeed;}
	  public int getYspeed()	{return yspeed;}
	  public int getXacc()		{return xacc;}
	  public int getYacc()		{return yacc;}
	  public void setX(int x)		{this.x = x;}
	  public void setY(int y)		{this.y = y;}
	  public void setXspeed(int n)	{this.xspeed = n;}
	  public void setYspeed(int n)	{this.yspeed = n;}
	  public void setXacc(int n)	{this.xacc = n;}
	  public void setYacc(int n)	{this.yacc = n;}
	  public void setXfriction(int n) {this.xfriction = n;}
	  public void setYfriction(int n) {this.yfriction = n;}

//Boundary Checkers=============================================
	  public void move(){
		  
	      xspeed+=xacc;
	      if(xspeed>topXSpeed) xspeed = topXSpeed;
	      if(xspeed<-topXSpeed) xspeed = -topXSpeed;
	      
	      yspeed+=yacc;
	      if(yspeed>topYSpeed) yspeed = topYSpeed;
	      
	      if(!xlocked){
		      oldX=x;
		      x+=xspeed;
	      }
		  if(!ylocked){
		      oldY=y;
		      y+=yspeed;
	      }
	      
	      //friction
		  for(int i=xfriction;i>0;i--){
			  if(xspeed>0)	xspeed-=1;
			  if(xspeed<0)	xspeed+=1;
		  }
		  for(int i=yfriction;i>0;i--){
			  if(yspeed>0)	yspeed-=1;
			  if(yspeed<0)	yspeed+=1;
		  }
	     yacc = gravity;
		 
	     	  
	  }
	  public void checkWindowBoundaries(int windowWidth, int windowHeight){
		  if(x<0){
				x=0; xspeed = 0;
			}
			if(x>windowWidth-getWidth()){
				x=windowWidth-getWidth(); xspeed = 0;
			}
				
			if(y<0){
				y=0; yspeed = 0;
			}
			if(y>windowHeight-getHeight()){
				y=windowHeight-getHeight(); yspeed = 0;
				jumpable = 0;
			}
	  }
	  public void bounceWindowBoundaries(int windowWidth, int windowHeight){
		  if(x<getWidth()/2){
				x=getWidth()/2; xspeed = Math.abs(xspeed);
			}
			if(x>windowWidth-getWidth()/2){
				x=windowWidth-getWidth()/2; xspeed = -Math.abs(xspeed);
			}
				
			if(y<getHeight()){
				y=getHeight(); yspeed = Math.abs(yspeed);
			}
			if(y>windowHeight-getHeight()){
				y=windowHeight-getHeight(); yspeed = -Math.abs(yspeed);
			}
	  }

//Util==========================================================
	  public boolean withinXparam(Tile b){
		  return (b.getX()<oldX+this.getWidth() && oldX<b.getX()+b.getWidth());
	  }
	  public boolean withinYparam(Tile b){
		  return (b.getY()<oldY+this.getHeight() && oldY<b.getY()+b.getHeight());
	  }
	  public int yspeedSign(){
		  if(yspeed>0) return 1;
		  if(yspeed<0) return -1;
		  return 0;
	  }
}
