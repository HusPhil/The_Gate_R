package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.DropChanceSystem;
import object.ITM_Coin;
import object.OBJ_Health_Potion;
import object.OBJ_Heart;
import object.OBJ_Slime_Shield;
import object.OBJ_Wood_Axe;
import object.SKL_SmallFireBall;

public class MON_FireSlime extends Entity{
	GamePanel gp;
	//DropChanceSystem dcs = new DropChanceSystem();
	public MON_FireSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		name = "Fire Slime";
		speed = 2;
		maxLife = 10;
		life = maxLife;
		atk = 2;
		def = 1;
		exp = 2;
		
		//SolidArea
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		projectile = new SKL_SmallFireBall(gp);
		
		//Set the image
		getMonImage();
		
	}
	public void getMonImage() {
		down1 = createImage("monsters", "fire_slime0");
		down2 = createImage("monsters", "fire_slime1");
		down3 = createImage("monsters", "fire_slime0");
		down4 = createImage("monsters", "fire_slime1");
		
		up1 = createImage("monsters", "fire_slime0");
		up2 = createImage("monsters", "fire_slime1");
		up3 = createImage("monsters", "fire_slime0");
		up4 = createImage("monsters", "fire_slime1");
		
		left1 = createImage("monsters", "fire_slime0");
		left2 = createImage("monsters", "fire_slime1");
		left3 = createImage("monsters", "fire_slime0");
		left4 = createImage("monsters", "fire_slime1");
		
		right1 = createImage("monsters", "fire_slime0");
		right2 = createImage("monsters", "fire_slime1");
		right3 = createImage("monsters", "fire_slime0");
		right4 = createImage("monsters", "fire_slime1");
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
	public void damageReaction() {
		actionDelay = 0;
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "left": direction = "right"; break;
		case "right": direction = "left"; break;
		}
		//speed = 5;
	}
	public void checkDrop() {
		
		int i = 0;
		gp.dcs.possibleDrops[i] = new OBJ_Wood_Axe(gp);
		i++;
		gp.dcs.possibleDrops[i] = new OBJ_Health_Potion(gp);
		i++;
		gp.dcs.possibleDrops[i] = new ITM_Coin(gp);
		i++;
		dropItem( gp.dcs.pickDrop());
	
	}
}
