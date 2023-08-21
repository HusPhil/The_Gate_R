package object;

import java.awt.Color;

import entity.Projectile;
import main.GamePanel;

public class SKL_SmallFireBall extends Projectile{
	GamePanel gp;
	
	public SKL_SmallFireBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Small Fireball";
		speed = 3;
		maxLife = 60;
		life = maxLife;
		atk = 10; 
		manaCost = 1;
		alive = false;
		getImage();
		}
	public void getImage() {
		down1 = createImage("skills", "lvl0fireball/lvl0_fireball0");
		down2 = createImage("skills", "lvl0fireball/lvl0_fireball1");
		down3 = createImage("skills", "lvl0fireball/lvl0_fireball2");
		down4 = createImage("skills", "lvl0fireball/lvl0_fireball3");
		
		up1 = createImage("skills", "lvl0fireball/lvl0_fireball0");
		up2 = createImage("skills", "lvl0fireball/lvl0_fireball1");
		up3 = createImage("skills", "lvl0fireball/lvl0_fireball2");
		up4 = createImage("skills", "lvl0fireball/lvl0_fireball3");
		
		left1 = createImage("skills", "lvl0fireball/lvl0_fireball0");
		left2 = createImage("skills", "lvl0fireball/lvl0_fireball1");
		left3 = createImage("skills", "lvl0fireball/lvl0_fireball2");
		left4 = createImage("skills", "lvl0fireball/lvl0_fireball3");
		
		right1 = createImage("skills", "lvl0fireball/lvl0_fireball0");
		right2 = createImage("skills", "lvl0fireball/lvl0_fireball1");
		right3 = createImage("skills", "lvl0fireball/lvl0_fireball2");
		right4 = createImage("skills", "lvl0fireball/lvl0_fireball3");
	}
	public Color getParticleColor() {
 		Color c = new Color(250, 50, 0);
		return c;
	}
	public int getParticleSize() {
		int size = 10;
		return size;
	}
	public int getParticleSpeed(){
		int pSpeed = 1;
		return pSpeed;
	}
	public int getParticleLife() { 
		int maxLife = 20;
		return maxLife;
	}
}
