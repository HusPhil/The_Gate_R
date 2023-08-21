package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class SKL_Fireball extends Projectile{
	GamePanel gp;
	public static final String objName = "Fireball";
	public SKL_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 5;
		maxLife = 80;
		life = maxLife;
		atk = 3; 
		manaCost = 10;
		alive = false;
		getImage();
		}
	public void getImage() {
		down1 = createImage("skills", "lvl1_fireball_DOWN0");
		down2 = createImage("skills", "lvl1_fireball_DOWN1");
		down3 = createImage("skills", "lvl1_fireball_DOWN2");
		down4 = createImage("skills", "lvl1_fireball_DOWN3");
		
		up1 = createImage("skills", "lvl1_fireball_UP0");
		up2 = createImage("skills", "lvl1_fireball_UP1");
		up3 = createImage("skills", "lvl1_fireball_UP2");
		up4 = createImage("skills", "lvl1_fireball_UP3");
		
		left1 = createImage("skills", "lvl1_fireball_LEFT0");
		left2 = createImage("skills", "lvl1_fireball_LEFT1");
		left3 = createImage("skills", "lvl1_fireball_LEFT2");
		left4 = createImage("skills", "lvl1_fireball_LEFT3");
		
		right1 = createImage("skills", "lvl1_fireball_RIGHT0");
		right2 = createImage("skills", "lvl1_fireball_RIGHT1");
		right3 = createImage("skills", "lvl1_fireball_RIGHT2");
		right4 = createImage("skills", "lvl1_fireball_RIGHT3");
	}
	public boolean sufficientResource(Entity user) {
		boolean sufficient = false;
	
		shotCounter++;
		if(user.mana >= manaCost) {
			return true;
		} else 
		if(shotCounter == 7) {
			gp.gui.addMessage("Not enough mana.");
			shotCounter = 0;
		}
		return sufficient;
	}
	public void useResource(Entity user) {
		user.mana -= manaCost;
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
