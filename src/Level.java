import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Level {
	
	private Tile background;
	private ArrayList<Mob> mobArray;
	
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int xSize = ((int) tk.getScreenSize().getWidth());  
	int ySize = ((int) tk.getScreenSize().getHeight());
	int thisLevelNumber;
	
	//10 represents the smallest possible block size.
	String levelString = null;
	int levelWidth;
	int levelHeight;
	
	//Constructor
	Level(int i, Tile background0){
		mobArray = new ArrayList<Mob>();
		this.background = background0;
		thisLevelNumber=i;
		mobArray.add(new Mob_Tick(20, 20));
		mobArray.add(new Mob_Tick(20, 50));
		mobArray.add(new Mob_Tick(220, 20));
		mobArray.add(new Mob_Tick(330, 360));
		mobArray.add(new Mob_Tick(550, 250));
		mobArray.add(new Mob_Tick(60, 620));
	}
	
	public BufferedImage getImage(String imageUrl){
		try {
			return ImageIO.read(Level.class.getResource(imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getThisLevelNumber(){
		return thisLevelNumber;
	}
	public Tile getBackground(){
		return background;
	}
	public ArrayList<Mob> getLevelMobs(){
		return mobArray;
	}
}
