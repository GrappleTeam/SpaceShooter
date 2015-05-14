
public class Player extends Mob{
	Player(int damage, int health, int x, int y){
		super("Player", damage, health, x, y);
		this.sprite = getImage("graphics/spaceship1_final.png");
	}
}
