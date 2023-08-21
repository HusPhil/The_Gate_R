package entity;

import java.util.Random;
import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Hermit extends Entity{


	public NPC_Hermit(GamePanel gp) {
		super(gp);
		int i = rN.nextInt(4)+1;
		
		if(i ==1) direction = "up";
		if(i ==2) direction = "down";
		if(i ==3) direction = "left";
		if(i ==4) direction = "right";
		
		projectile = new SKL_MudBall(gp);
		speed = 1;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "up0");
		up2 = createImage("npc", "up1");
		up3 = createImage("npc", "up2");
		up4 = createImage("npc", "up3");
		
		down1 = createImage("npc", "down0");
		down2 = createImage("npc", "down1");
		down3 = createImage("npc", "down2");
		down4 = createImage("npc", "down3");
		
		right1 = createImage("npc", "right0");
		right2 = createImage("npc", "right1");
		right3 = createImage("npc", "right2");
		right4 = createImage("npc", "right3");
		
		left1 = createImage("npc", "left0");
		left2 = createImage("npc", "left1");
		left3 = createImage("npc", "left2");
		left4 = createImage("npc", "left3");
	}
	
	public void setAction() {
		actionDelay++;
		if(actionDelay == 120 ) {
			int n = rN.nextInt(100)+1;
			
			if(n<=25) direction = "up";
			if(n>=25 && n<=50) direction = "down";
			if(n>=50 && n<=75) direction = "left";
			if(n>=75 && n<=100) direction = "right";
			actionDelay = 0;
		}
		if(!projectile.alive && shotCounter == 30) {
			projectile.set(worldX, worldY, true, direction, this);
			gp.projectileList.add(projectile);
			shotCounter = 0;
			projectile.setDamage();
		}
	}
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					gp.projectileList.add(projectile);
					projectile.useResource(this);
					shotCounter = 0;
				} 
	}
	public void setDialouge() {
		dialouges[0] = "Hello there, boya..";
		dialouges[1] = "Have you come from another world?";
		dialouges[2] = "I see, you are cursed as well.";
		dialouges[3] = "I remember when I used to be a young \nadventurer like you. I was a fine warrior, \nthen. But now, I..";
		dialouges[4] = "Oh forgive me, I have said too much. \nThen, take care boya..";
		dialouges[19] = "Goodluck on your adventure boya..";
	}
	public void speak() {
	super.speak();
	}	
}
