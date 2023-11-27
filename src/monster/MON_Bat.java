package monster;

import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;
import object.OBJ_Health_Potion;
import object.OBJ_Heart;

public class MON_Bat extends Entity{
	GamePanel gp;
	//DropChanceSystem dcs = new DropChanceSystem();
	public static String monName = "bat";
	public MON_Bat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		defaultSpeed = 5;
		speed = defaultSpeed;
		maxLife = 15;
		name = monName;
		life = maxLife;
		atk = 5;
		def = 1;
		exp = 7;
		
		//SolidArea
		solidArea.x = 3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
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
		down1 = createImage("monsters", "bat_down_1",38,38);
		down2 = createImage("monsters", "bat_down_2",38,38);
		down3 = createImage("monsters", "bat_down_1",38,38);
		down4 = createImage("monsters", "bat_down_2",38,38);
		
		up1 = down1;
		up2 = down2;
		up3 = down3;
		up4 = down4;
		
		left1 = down1;
		left2 = down2;
		left3 = down3;
		left4 = down4;
		
		right1 = down1;
		right2 = down2;
		right3 = down3;
		right4 = down4;
		
	}
	public void setAction() {
		int actionChangeInterval = 30;
		
		actionDelay++;
		if(actionDelay == actionChangeInterval) {
			int n = rN.nextInt(100)+1;
			
			if(n<=25) direction = "up";
			if(n>=25 && n<=50) direction = "down";
			if(n>=50 && n<=75) direction = "left";
			if(n>=75 && n<=100) direction = "right";
			actionDelay = 0;
//		if(pathAI) {
//			int pWorldX = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
//			int pWorldY = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
//			
//		}
//		else {
//			
//			}
			
		}
		
	}
	public void damageReaction() {
		actionDelay = 0;
		
//		pathAI = true;
	}
	public void checkDrop() {
		
		int i = 0;
		gp.dcs.possibleDrops[i] = new OBJ_Health_Potion(gp);
		i++;
		gp.dcs.possibleDrops[i] = new OBJ_Heart(gp);
		i++;
		
		gp.dcs.defaultItem = new ITM_Coin(gp);
		dropItem(new ITM_Coin(gp));
	}
		
}