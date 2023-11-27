package monster;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;
import object.ITM_FireGel;
import object.OBJ_Health_Potion;
import object.OBJ_Iron_Shield;
import object.SKL_MudBall;

public class MON_FloatingSkull extends Entity{
	GamePanel gp;
	//DropChanceSystem dcs = new DropChanceSystem();
	public static String monName = "Floating Skull";
	public MON_FloatingSkull(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 160;
		name = monName;
		life = maxLife;
		atk = 20;
		def = 10;
		exp = 20;
		debugOn = true;
		
		//SolidArea
//		solidArea.x = 0;
//		solidArea.y = 0;
//		solidArea.width = 38;
//		solidArea.height = 38;
		
		solidArea = new Rectangle((gp.tileSize/2)-4, gp.tileSize/2, ( gp.tileSize/2)+18, (gp.tileSize-(gp.tileSize/4))+8);
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		projectile = new SKL_MudBall(gp);
		knockBackPower = 2;
		
		//Set the image
		getMonImage();
		
	}
	public void update() {
		super.update();
		
		int distanceX = Math.abs(worldX - gp.player.worldX);
		int distanceY = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (distanceX + distanceY) / gp.tileSize;
		
		if(!pathAI && tileDistance < 5) pathAI = true; 
		if(pathAI && tileDistance > 15) pathAI = false; 
		
	}
	public void getMonImage() {
		down1 = createImage("monsters", "floating_skull/left0", 2*gp.tileSize, 2*gp.tileSize);
		down2 = createImage("monsters", "floating_skull/left1", 2*gp.tileSize, 2*gp.tileSize);
		down3 = createImage("monsters", "floating_skull/left2", 2*gp.tileSize, 2*gp.tileSize);
		down4 = createImage("monsters", "floating_skull/left3", 2*gp.tileSize, 2*gp.tileSize);

		left1 = createImage("monsters", "floating_skull/left0", 2*gp.tileSize, 2*gp.tileSize);
		left2 = createImage("monsters", "floating_skull/left1", 2*gp.tileSize, 2*gp.tileSize);
		left3 = createImage("monsters", "floating_skull/left2", 2*gp.tileSize, 2*gp.tileSize);
		left4 = createImage("monsters", "floating_skull/left3", 2*gp.tileSize, 2*gp.tileSize);
		
		right1 = createImage("monsters", "floating_skull/right0", 2*gp.tileSize, 2*gp.tileSize);
		right2 = createImage("monsters", "floating_skull/right1", 2*gp.tileSize, 2*gp.tileSize);
		right3 = createImage("monsters", "floating_skull/right2", 2*gp.tileSize, 2*gp.tileSize);
		right4 = createImage("monsters", "floating_skull/right3", 2*gp.tileSize, 2*gp.tileSize);

		up1 = createImage("monsters", "floating_skull/right0", 2*gp.tileSize, 2*gp.tileSize);;
		up2 = createImage("monsters", "floating_skull/right1", 2*gp.tileSize, 2*gp.tileSize);;
		up3 = createImage("monsters", "floating_skull/right2", 2*gp.tileSize, 2*gp.tileSize);;
		up4 = createImage("monsters", "floating_skull/right3", 2*gp.tileSize, 2*gp.tileSize);;
		
		
		
	}
	public void setAction() {
		int actionChangeInterval = 120;
		if(pathAI) {
			int pWorldX = gp.player.getPlayerWordlX();
			int pWorldY = gp.player.getPlayerWordlY();
			
			searchPath(pWorldX, pWorldY, true);
		}
		else {
			actionDelay++;
			if(actionDelay == actionChangeInterval) {
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
			
		}
		
	}
	public void damageReaction() {
		actionDelay = 0;
		
		pathAI = true;
	}
	public void checkDrop() {
		
		int i = 0;
		gp.dcs.possibleDrops[i] = new OBJ_Health_Potion(gp);
		i++;
		gp.dcs.possibleDrops[i] = new OBJ_Iron_Shield(gp);
		i++;
		
		gp.dcs.defaultItem = new ITM_Coin(gp);
//		dropItem(new ITM_FireGel(gp));
	}
}
