public class Mob_BigBug extends Mob{

	private static final int damage = 3;
	private static final int health = 5;
	private int mod = 2;
	private int tic = 0;
	private int tic2 = 0;
	private int dir = 3;
	
	Mob_BigBug(int x, int y){
		super("Venus", damage, health, x, y);
		this.sprite = getImage("graphics/BigBug.png");
	}
	
	Mob_BigBug(int damage, int health, int x, int y){
		super("Venus", damage, health, x, y);
		this.sprite = getImage("graphics/BigBug.png");
	}
	
	@Override
	public void pattern()
	{
		
		this.setYacc(0);
		if(tic>70){
			mod = -mod;
			tic = 0;
			setYspeed(-10);
			tic2++;
			if(tic2>5){ dir = -dir; tic2 = 0;}
		}
		if(tic==20){
			setYspeed(dir);
		}
		setXspeed(mod);
		move();
		tic++;
	}
}
