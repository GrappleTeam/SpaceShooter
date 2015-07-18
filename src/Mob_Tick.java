public class Mob_Tick extends Mob{

	private static final int damage = 3;
	private static final int health = 5;
	private int mod = 2;
	private int tic = 0;
	
	Mob_Tick(int x, int y){
		super("Venus", damage, health, x, y);
		this.sprite = getImage("graphics/ladybug.png");
	}
	
	Mob_Tick(int damage, int health, int x, int y){
		super("Venus", damage, health, x, y);
		this.sprite = getImage("graphics/ladybug.png");
	}
	
	@Override
	public void pattern()
	{
			if(tic>50){
				mod = -mod;
				tic = 0;
			}
			setXspeed(mod);
			setYspeed(mod+1);
			move();
			tic++;
	}
}
