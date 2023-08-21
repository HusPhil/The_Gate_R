package object;

import java.awt.Color;

import entity.Projectile;
import main.GamePanel;

public class SKL_MudBall extends Projectile{
	GamePanel gp;
	
	public SKL_MudBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Mud ball";
		speed = 5;
		maxLife = 40;
		life = maxLife;
		atk = 10; 
		manaCost = 1;
		alive = false;
		getImage();
		}
	public void getImage() {
		down1 = createImage("projectiles", "mudball/mud_ball0");
		down2 = createImage("projectiles", "mudball/mud_ball1");
		down3 = createImage("projectiles", "mudball/mud_ball2");
		down4 = createImage("projectiles", "mudball/mud_ball3");
		
		up1 = createImage("projectiles", "mudball/mud_ball0");
		up2 = createImage("projectiles", "mudball/mud_ball1");
		up3 = createImage("projectiles", "mudball/mud_ball2");
		up4 = createImage("projectiles", "mudball/mud_ball3");
		
		left1 = createImage("projectiles", "mudball/mud_ball0");
		left2 = createImage("projectiles", "mudball/mud_ball1");
		left3 = createImage("projectiles", "mudball/mud_ball2");
		left4 = createImage("projectiles", "mudball/mud_ball3");
		
		right1 = createImage("projectiles", "mudball/mud_ball0");
		right2 = createImage("projectiles", "mudball/mud_ball1");
		right3 = createImage("projectiles", "mudball/mud_ball2");
		right4 = createImage("projectiles", "mudball/mud_ball3");
	}
	public Color getParticleColor() {
 		Color c = new Color(40, 50, 30);
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
