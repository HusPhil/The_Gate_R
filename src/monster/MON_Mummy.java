package monster;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import object.ITM_Bandage;

public class MON_Mummy extends Entity{
	GamePanel gp;
	//DropChanceSystem dcs = new DropChanceSystem();
	public MON_Mummy(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//Stats
		type = type_monster;
		name = "Mummy";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 45;
		attacking = !true;
		
		life = maxLife;
		atk = 4;
		def = 2;
		exp = 5;
		
		//SolidArea
		solidArea = new Rectangle((gp.tileSize/4)-4, gp.tileSize/4, ( gp.tileSize/2)+8, gp.tileSize-(gp.tileSize/4));
		
		attackArea.width = gp.tileSize;
		attackArea.height = gp.tileSize;
		
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		knockBackPower = 4;
		
		motion_duration1 = 20;
		motion_duration2 = 30;
		//Set the image
		getWalkingImage();
		getAttackingImage();
		
	}

	public void getWalkingImage() {
		up1 = createImage("monsters", "corrupted_mummy/walking/up0");
		up2 = createImage("monsters", "corrupted_mummy/walking/up1");
		up3 = createImage("monsters", "corrupted_mummy/walking/up2");
		up4 = createImage("monsters", "corrupted_mummy/walking/up3");
		
		down1 = createImage("monsters", "corrupted_mummy/walking/down0");
		down2 = createImage("monsters", "corrupted_mummy/walking/down1");
		down3 = createImage("monsters", "corrupted_mummy/walking/down2");
		down4 = createImage("monsters", "corrupted_mummy/walking/down3");
		
		right1 = createImage("monsters", "corrupted_mummy/walking/right0");
		right2 = createImage("monsters", "corrupted_mummy/walking/right1");
		right3 = createImage("monsters", "corrupted_mummy/walking/right2");
		right4 = createImage("monsters", "corrupted_mummy/walking/right3");
		
		left1 = createImage("monsters", "corrupted_mummy/walking/left0");
		left2 = createImage("monsters", "corrupted_mummy/walking/left1");
		left3 = createImage("monsters", "corrupted_mummy/walking/left2");
		left4 = createImage("monsters", "corrupted_mummy/walking/left3");
		
	}
	public void getAttackingImage() {
		attackUp1 = createImage("monsters", "corrupted_mummy/attacking/up0", gp.tileSize, gp.tileSize*2);
		attackUp2 = createImage("monsters", "corrupted_mummy/attacking/up1", gp.tileSize, gp.tileSize*2);
		attackUp3 = createImage("monsters", "corrupted_mummy/attacking/up2", gp.tileSize, gp.tileSize*2);
		attackUp4 = createImage("monsters", "corrupted_mummy/attacking/up3", gp.tileSize, gp.tileSize*2);
	
		attackDown1 = createImage("monsters", "corrupted_mummy/attacking/down0");
		attackDown2 = createImage("monsters", "corrupted_mummy/attacking/down1");
		attackDown3 = createImage("monsters", "corrupted_mummy/attacking/down2");
		attackDown4 = createImage("monsters", "corrupted_mummy/attacking/down3");
		
		attackLeft1 = createImage("monsters", "corrupted_mummy/attacking/left0", gp.tileSize*2, gp.tileSize);
		attackLeft2 = createImage("monsters", "corrupted_mummy/attacking/left1", gp.tileSize*2, gp.tileSize);
		attackLeft3 = createImage("monsters", "corrupted_mummy/attacking/left2", gp.tileSize*2, gp.tileSize);
		attackLeft4 = createImage("monsters", "corrupted_mummy/attacking/left3", gp.tileSize*2, gp.tileSize);
		
		attackRight1 = createImage("monsters", "corrupted_mummy/attacking/right0");
		attackRight2 = createImage("monsters", "corrupted_mummy/attacking/right1");
		attackRight3 = createImage("monsters", "corrupted_mummy/attacking/right2");
		attackRight4 = createImage("monsters", "corrupted_mummy/attacking/right3");
		
		
	}
	public void setAction() {
		
		if (!attacking) {
			setAttackState(gp.tileSize, gp.tileSize , gp.tileSize*40);
			if(pathAI) {
				int pWorldX = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
				int pWorldY = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
				
				searchPath(pWorldX, pWorldY);
			
			}
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
		
		
		
			
		}
		
	}
	public void damageReaction() {
		actionDelay = 0;
		
		pathAI = true;
	}
	public void checkDrop() {
		
		int i = 0;
		gp.dcs.possibleDrops[i] = new ITM_Bandage(gp);
		i++;
		
		gp.dcs.defaultItem = new ITM_Bandage(gp);
		dropItem( gp.dcs.pickDrop());
	}
	
	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		//check if the path to the goal was found
		if(gp.pathFinder.autoSearch()){
			//we can now track back the path and guide the entity to it
			int nextWorldX = gp.pathFinder.pathList.get(0).col*gp.tileSize;
			int nextWorldY = gp.pathFinder.pathList.get(0).row*gp.tileSize;			 
			
			//get Entity's solidArea position
			int entTopY = worldY + solidArea.y;
			int entBottomY = worldY + solidArea.y + solidArea.height;
			int entLeftX = worldX + solidArea.x;
			int entRightX = worldX + solidArea.x + solidArea.width;
			
			//compare the soild area and nextworld x to decide the direction of the entity
			if(entTopY > nextWorldY && entLeftX >= nextWorldX && entRightX < nextWorldX + gp.tileSize) {
				direction = "up";
			}
			else if(entTopY < nextWorldY && entLeftX >= nextWorldX && entRightX < nextWorldX + gp.tileSize) {
				direction = "down";
			}
			else if(entTopY >= nextWorldY && entBottomY < nextWorldY + gp.tileSize) {
				//enitity can go either left or right
				if(entLeftX > nextWorldX) direction = "left";
				if(entLeftX < nextWorldX) direction = "right";
			}
			else if(entTopY > nextWorldY && entLeftX > nextWorldX) {
				//entity can either go left or up
				direction = "up";
				checkCollision();
				if(collisionOn) {
					direction = "left";
				}
			}
			else if(entTopY > nextWorldY && entLeftX < nextWorldX) {
				//entity can either go right or up
				direction = "up";
				checkCollision();
				if(collisionOn) {
					direction = "right";
				}
			}
			else if(entTopY < nextWorldY && entLeftX > nextWorldX) {
				//entity can either go down or left
				direction = "down";
				checkCollision();
				if(collisionOn) {
					direction = "left";
				}
			}
			else if(entTopY < nextWorldY && entLeftX > nextWorldX) {
				//entity can either go down or left
				direction = "down";
				checkCollision();
				if(collisionOn) {
					direction = "left";
				}
			}
			else if(entTopY < nextWorldY && entLeftX < nextWorldX) {
				//entity can either go down or right
				direction = "down";
				checkCollision();
				if(collisionOn) {
					direction = "right";
				}
			}
			int nextCol =  gp.pathFinder.pathList.get(0).col;
			int nextRow = gp.pathFinder.pathList.get(0).row;
			
			if(nextCol+1 == goalCol+1 && nextRow+1 == goalRow+1) {
				
			}
			
		}
		
	}
	
	public void update() {
		super.update();
		
		int distanceX = Math.abs(worldX - gp.player.worldX);
		int distanceY = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (distanceX + distanceY) / gp.tileSize;
		
		if(!pathAI && tileDistance < 5) pathAI = true; 
		if(pathAI && tileDistance > 15) pathAI = false; 
		
	}
}
