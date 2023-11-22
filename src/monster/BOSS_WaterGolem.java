package monster;

import DataHandling.GameProgress;
import entity.Entity;
import main.GamePanel;
import object.ITM_Coin;
import object.ITM_WaterEssence;
import object.OBJ_IronDoor;

public class BOSS_WaterGolem extends Entity{
	GamePanel gp;
	
	public static String monName = "Water Golem";
	public BOSS_WaterGolem(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		name = monName;
		defaultSpeed = 3;
		speed = defaultSpeed;
		maxLife = 80;
		attacking = !true;
//		debugOn = true;
//		type_boss = true;
		life = maxLife;
		atk = 10;
		def = 10;
		exp = 50;
		
		asleep = true;
		
		//SolidArea
		int size = 3 * gp.tileSize;
		
		solidArea.x = gp.tileSize/2;
		solidArea.y = gp.tileSize/2;
		solidArea.width = size - (gp.tileSize);
		solidArea.height = size - (gp.tileSize/2);
		
		attackArea.width = 170/2;
		attackArea.height = 170/2;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		knockBackPower = 6;
		
		motion_duration1 = 20;
		motion_duration2 = 35;
		
		//Set the image
		getWalkingImage();
		getAttackingImage();
		setDialogue();
	}

	public void getWalkingImage() {
		int scale = 3;
		 
		 
		left1 = createImage("monsters", "boss_watergolem/walking/left0", gp.tileSize*scale, gp.tileSize*scale);
		left2 = createImage("monsters", "boss_watergolem/walking/left1", gp.tileSize*scale, gp.tileSize*scale);
		left3 = createImage("monsters", "boss_watergolem/walking/left2", gp.tileSize*scale, gp.tileSize*scale);
		left4 = createImage("monsters", "boss_watergolem/walking/left3", gp.tileSize*scale, gp.tileSize*scale);
		
		up1 = left1;
		up2 = left2;
		up3 = left3;
		up4 = left4;
		
		right1 = createImage("monsters", "boss_watergolem/walking/right0", gp.tileSize*scale, gp.tileSize*scale);
		right2 = createImage("monsters", "boss_watergolem/walking/right1", gp.tileSize*scale, gp.tileSize*scale);
		right3 = createImage("monsters", "boss_watergolem/walking/right2", gp.tileSize*scale, gp.tileSize*scale);
		right4 = createImage("monsters", "boss_watergolem/walking/right3", gp.tileSize*scale, gp.tileSize*scale);

		down1 = right1;
		down2 = right2;
		down3 = right3;
		down4 = right4;
	}
	public void getAttackingImage() {
		int scale = 3;

		attackUp1 = createImage("monsters", "boss_watergolem/attacking/up0", gp.tileSize*scale, gp.tileSize*scale*2);
		attackUp2 = createImage("monsters", "boss_watergolem/attacking/up1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackUp3 = createImage("monsters", "boss_watergolem/attacking/up0", gp.tileSize*scale, gp.tileSize*scale*2);
		attackUp4 = createImage("monsters", "boss_watergolem/attacking/up1", gp.tileSize*scale, gp.tileSize*scale*2);
		
		attackDown1 = createImage("monsters", "boss_watergolem/attacking/down0", gp.tileSize*scale, gp.tileSize*scale*2);
		attackDown2 = createImage("monsters", "boss_watergolem/attacking/down1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackDown3 = createImage("monsters", "boss_watergolem/attacking/down0", gp.tileSize*scale, gp.tileSize*scale*2);
		attackDown4 = createImage("monsters", "boss_watergolem/attacking/down1", gp.tileSize*scale, gp.tileSize*scale*2);
		
		attackLeft1 = createImage("monsters", "boss_watergolem/attacking/left0", gp.tileSize*scale*2, gp.tileSize*scale);
		attackLeft2 = createImage("monsters", "boss_watergolem/attacking/left1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackLeft3 = createImage("monsters", "boss_watergolem/attacking/left0", gp.tileSize*scale*2, gp.tileSize*scale);
		attackLeft4 = createImage("monsters", "boss_watergolem/attacking/left1", gp.tileSize*scale*2, gp.tileSize*scale);
		
		attackRight1 = createImage("monsters", "boss_watergolem/attacking/right0", gp.tileSize*scale*2, gp.tileSize*scale);
		attackRight2 = createImage("monsters", "boss_watergolem/attacking/right1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackRight3 = createImage("monsters", "boss_watergolem/attacking/right0", gp.tileSize*scale*2, gp.tileSize*scale);
		attackRight4 = createImage("monsters", "boss_watergolem/attacking/right1", gp.tileSize*scale*2, gp.tileSize*scale);
		
	}
	public void setAction() {
		
		if(!enraged && life < maxLife/2) {
			enraged = true;
			
			motion_duration1 = 20;
			motion_duration2 = 35;
			
			defaultSpeed = 6;
			speed = defaultSpeed;
			atk *= 1.5;
			getAttackingImage();
			getWalkingImage();
		}
		
		if(getTileDistance(gp.player) < 10) huntPlayer(30);
		else {
			actionDelay++;
			if(actionDelay == 120 ) {
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
			
		}
		
		if (!attacking) {
			int rate = 45;
			setAttackState(gp.tileSize*2, gp.tileSize*2, rate);
			
		}
		
		
		
	}
	public void damageReaction() {
		actionDelay = 0;
		
		pathAI = true;
	}
	public void checkDrop() {
		gp.bossBattleOn = false;
		GameProgress.waterGolemDefeated = true;
		
		//stopTheBossMusic
		//playDungeonMusic
		
		//open iron doors
		
		dropItem(new ITM_WaterEssence(gp));
	}
	public void setDialogue() {
		int dialogueSet, dialogueNum;
		
		dialogueSet = dialogueNum = 0;
		
		dialogues[dialogueSet][dialogueNum] = "No one shall steal my treasure!"; dialogueNum++;
		dialogues[dialogueSet][dialogueNum] = "You mere humans' ignorance know no bound!"; dialogueNum++;
		dialogues[dialogueSet][dialogueNum] = "I shall end you here!"; dialogueNum++;
		
		dialogueSet++; dialogueNum = 0;
		
	}
	
	
	public void attackState() {
		spriteCounter++;
		if(spriteCounter > motion_duration1 && spriteCounter <= motion_duration2 && (spriteNum == 2 || spriteNum == 4)) {
			
			if (spriteNum == 4) spriteNum = 1;
			if (spriteNum == 2) spriteNum = 3;
			
			
			//SAVE THE CURRENT DATA OF PLAYER BEFORE IT IS CHANGED
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int currentsolidAreaW = solidArea.width;
			int currentsolidAreaH = solidArea.height;
			//int tempHolder;
			
			// Check the players direction to adjust the solid area
			switch(direction) {
			case "up": 
				worldY-= attackArea.height;
				break;
			case "down": 
				worldY+= attackArea.height/2; 
				break;
			case "left": 
				worldX-= attackArea.width; 
				break;
			case "right": 
				worldX+= attackArea.width/2; 
				break;
			}
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//Change the solidArea to attackArea
			if(type == type_monster && gp.collCheck.playerIntersect(this)) {
				damagePlayer(atk);
				spriteNum = 1;
			}
			
			//Reset player's solidArea and Position
			worldX = currentWorldX;
			worldY = currentWorldY;
			
			
			solidArea.width = currentsolidAreaW;
			solidArea.height = currentsolidAreaH;
			
			

		}
		if(spriteCounter > motion_duration2) {
			if(spriteNum == 1) {
				spriteNum = 2; 
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
			attacking = false;
		} 
	}
	
	public void update() {
		super.update();
		
		
		
	}
}
