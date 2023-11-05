package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityClass;

public class Entity {
	
	GamePanel gp;
	Graphics2D g2;
	
	//Entity Images 
	public BufferedImage 
	up1, up2, up3, up4, 
	down1, down2, down3, down4,
	left1, left2, left3, left4, 
	right1, right2, right3, right4,
	upDash1, upDash2;
	public BufferedImage image, image1, image2;
	public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4; 
	public BufferedImage attackDown1,attackDown2, attackDown3, attackDown4;
	public BufferedImage attackLeft1, attackLeft2, attackLeft3, attackLeft4;
	public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4;
	
	//Entity's Solid Area
	public Rectangle solidArea = new Rectangle();
//	0, 16,  32, 32
	
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Rectangle attackAreaX = new Rectangle(0, 0, 0, 0);
	public Rectangle attackAreaY = new Rectangle(0, 0, 0, 0);
	public int defaultSolidAreaX, defaultSolidAreaY;
	
	//Entity's dialogue
	public String dialogues[][] = new String[200][200];
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	
	//Entity's counters
	public int timer = 0;
	public int spriteNum = 1;
	public int spriteCounter = 0;
	public int actionDelay = 0;
	public int invincibleTime = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public int shotCounter = 0;
	public int knockBackCounter = 0;
	public Random rN = new Random();
	
	//Entity's paths
	public int currentSearchPath;
	public static final int pathOFF = 0;
	
		
	//Entity State
	public boolean contactOn = false;
	public boolean temp = false;
	public boolean drawing = true;
	public boolean collisionOn = false;
	public boolean collision = false;
	public boolean attacking = false;
	public boolean invincible;
	public boolean alive = true;
	public boolean dying;
	public boolean hpBarOn;
	public boolean pathAI = false;
	public boolean knockBackState = false;
	public boolean debugOn = false;
	public boolean enraged = false;
	public boolean asleep = false;
		
	//Entity's Attributes
	public Entity loot;
	public boolean opened = false;
	public int knockBackPower = 0;
	public int defaultSpeed;
	public int maxLife, life;
	public int worldX;
	public int worldY; 
	public int speed;
	public int level;
	public int str;
	public int dex;
	public int atk;
	public int def;
	public int exp;
	public int nextLvlExp;
	public int coin;
	public int dropChance;
	public int reqItem;
	public int lightRadius;
	public Entity currentWeapon;
	public Entity currentShield;
	public Entity currentLightItem;
	public Entity linkedEntity;
	public String description = "";
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 28;
	public String cs_id = "000";
	
	//Item Stats
	public int atkVal;
	public int defVal;
	public int plus;
	public int ammount = 1;
	
	//Entity Status
	public boolean atk_state = false;
	public String name;
	public String direction = "down"; 
	public String knockDirection = direction;
	public int type;
	public int mana, maxMana, manaCost;
	public int motion_duration1 = 0;
	public int motion_duration2 = 0;
	
	//ENtity types
	public boolean type_boss = false;
	public boolean stackable = false;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_shield = 4;
	public final int type_axe = 5;
	public final int type_consumables = 6;
	public final int type_interactiveObjects = 7;
	public final int type_lightSource = 8;
	public final int type_merchant = 9;
	public final int type_pickaxe = 10;
	
	public final int non_inventory = 888;
	public int slowcounter = 0;
	public Projectile projectile;	
	
	
 	public Entity(GamePanel gp) {
		this.gp = gp;
		this.name = "";
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
	}

 	
 	//GETTERS
 	public int getCenterX() {
 		return worldX + (left1.getWidth()/2);
 	}
 	public int getCenterY() {
 		return worldY + (up1.getHeight()/2);
 	}
 	public int getDistanceX(Entity target) {
 		int distanceX = Math.abs(getCenterX()-target.getCenterX());
 		return distanceX;
 	}
 	public int getDistanceY(Entity target) {
 		int distanceY = Math.abs(getCenterY()-target.getCenterY());
 		return distanceY;
 	}
 	public int getGoalCol(Entity target) {
 		int goalCol = (target.worldX+target.solidArea.x)/gp.tileSize;
 		return goalCol;
 	}
 	public int getGoalRow(Entity target) {
 		int goalRow = (target.worldY+target.solidArea.y)/gp.tileSize;
 		return goalRow;
 	}
 	public int getTileDistance(Entity target) {
 		int tileDistance = 0;
 		tileDistance =(getDistanceX(target)+getDistanceY(target))/gp.tileSize;
 		return tileDistance;
 	}
 	////////////////////////////////////////
 	
 	public int getTopY() {
		return worldY + solidArea.y;
	}
 	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
 	public int getLeftX() {
		return worldX + solidArea.x;
	}
 	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
 	public int getCol() {
 		return getLeftX()/gp.tileSize;
 	}
 	public int getRow() {
 		return getTopY()/gp.tileSize;
 	}
 	public BufferedImage createImage(String type, String fileName) {
		UtilityClass Utils = new UtilityClass();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read((getClass().getResourceAsStream("/" + type + "/" + fileName + ".png")));
			image = Utils.scaleImage(gp.tileSize, gp.tileSize, image); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public BufferedImage createImage(String type, String fileName, int width, int height) {
		UtilityClass Utils = new UtilityClass();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read((getClass().getResourceAsStream("/" + type + "/" + fileName + ".png")));
			image = Utils.scaleImage(width, height, image);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public void playerIntersect(Entity ent) {
		
			if(ent != null) {
				
				gp.player.solidArea.x += gp.player.worldX;
				gp.player.solidArea.y += gp.player.worldY;
				
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;
				
				switch(gp.player.direction) {
				case "up":
					gp.player.solidArea.y -= gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "down":
					gp.player.solidArea.y += gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "left":
					gp.player.solidArea.x -= gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				case "right":
					gp.player.solidArea.x += gp.player.speed;
					if(gp.player.solidArea.intersects(ent.solidArea)) {
						gp.player.collisionOn = true;
						
					}
					break;
				
				}
				gp.player.solidArea.x = gp.player.defaultSolidAreaX;
				gp.player.solidArea.y = gp.player.defaultSolidAreaY;
				
				ent.solidArea.x = ent.defaultSolidAreaX;
				ent.solidArea.y = ent.defaultSolidAreaY;
			
		}
	}
	public void damagePlayer(int atk) {
		if(!gp.player.invincible) {
			gp.playSE(6);
			knockBackAction(gp.player, knockBackPower, direction); 
			int dmg = atk - gp.player.def;
			if(dmg < 0) dmg = 0;
			gp.player.life-=dmg;
			gp.player.invincible = true;
	}
	}
	public void damageReaction() {}
	public void setAction() {}
	public void speak(int dialogueSetNum) {
	}
	public void facePlayer() {
		//Face the player
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "left": direction = "right"; break;
		case "right": direction = "left"; break;
		}
	}
	public void startDialogue(Entity ent, int setNum) {
		System.out.println("ediwow");

		gp.keys.enterPressed = false;
		gp.gameState = gp.dialogueState;
		gp.gui.npc = ent;
		dialogueSet = setNum;
	}
	public void use(Entity ent) {}
	public int getScreenX() {
		int x = 0;
		x = worldX - gp.player.worldX + gp.player.screenX; 
		return x;
	}
	public int getScreenY() {
		int y = 0;
		y = worldY - gp.player.worldY + gp.player.screenY;
		return y;
	}
	public void attackState() {
		spriteCounter++;
		if(spriteCounter > motion_duration1 && spriteCounter <= motion_duration2) {
			spriteNum = 2;
			
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
				worldY+= attackArea.height; 
				break;
			case "left": 
				worldX-= attackArea.width; 
				break;
			case "right": 
				worldX+= attackArea.width; 
				break;
			}
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//Change the solidArea to attackArea
			if(type == type_monster && gp.collCheck.playerIntersect(this)) {
				damagePlayer(atk);
			}
			
			//Reset player's solidArea and Position
			worldX = currentWorldX;
			worldY = currentWorldY;
			
			
			solidArea.width = currentsolidAreaW;
			solidArea.height = currentsolidAreaH;
			
		}
		if(spriteCounter > motion_duration2) {
			spriteNum = 1;
			attacking = false;
			spriteCounter = 0;
		}
		
	}
	public void setAttackState(int vertical, int horizontal, int rate) {
		boolean targetInRange = false;
		Entity target = gp.player;
		int xDistance = getDistanceX(target);
		int yDistance = getDistanceY(target);
		
		switch(direction) {
		case "up":
			if(
			target.getCenterY() < getCenterY() && 
			yDistance < vertical &&
			xDistance < horizontal
			) targetInRange = true;
			break;
		case "down":
			if(
			target.getCenterY() > getCenterY() && 
			yDistance < vertical &&
			xDistance < horizontal
			) targetInRange = true;
			break;
		case "left":
			if(
			target.getCenterX() < getCenterX() && 
			xDistance < vertical &&
			yDistance < horizontal
			) targetInRange = true;
			break;
		case "right":
			if(
			target.getCenterX() > getCenterX() && 
			xDistance < vertical &&
			yDistance < horizontal
			) targetInRange = true;
			break;
		}
		
		if(targetInRange) {
			attacking = true;
			
			
		}
		
	}
	public void dyingAnim() {
		dyingCounter++;
		int i = 5;
		
		if(dyingCounter < i) changeOpacity(0f);
		if(dyingCounter > i && dyingCounter <= i*2) changeOpacity(1f); 
		if(dyingCounter > i*2 && dyingCounter <= i*3) changeOpacity(0f);
		if(dyingCounter > i*3 && dyingCounter <= i*4) changeOpacity(1f);
		if(dyingCounter > i*4 && dyingCounter <= i*5) changeOpacity(0f);
		if(dyingCounter > i*5 && dyingCounter <= i*6) changeOpacity(1f);
		if(dyingCounter > i*6 && dyingCounter <= i*7) changeOpacity(0f);
		if(dyingCounter > i*7 && dyingCounter <= i*8) changeOpacity(1f);
		if(dyingCounter > i*8) {
			alive = false;
		}
		
		
	}
	public void changeOpacity(float value) {
		Graphics2D g2 = this.g2;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
	}
	public void checkDrop() {
		
	}
	public void dropItem(Entity item) {
		int j = new Random().nextInt(2);
		int k = new Random().nextInt(2);
		int m = new Random().nextInt(2); 
		
		for(int i = 0; i < gp.gameObjs[1].length; i++) {
			
			if(gp.gameObjs[gp.currentMap][i] == null) {
				gp.gameObjs[gp.currentMap][i] = item;
				if (k == 0) gp.gameObjs[gp.currentMap][i].worldX = worldX + j*(gp.tileSize/2);
				if (k == 1) gp.gameObjs[gp.currentMap][i].worldX = worldX - j*(gp.tileSize/2);
				
				if (m == 0) gp.gameObjs[gp.currentMap][i].worldY = worldY + k*(gp.tileSize/2);
				if (m == 1) gp.gameObjs[gp.currentMap][i].worldY = worldY - k*(gp.tileSize/2);
				break;
			}
		}
	}
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					for(int i = 0; i < gp.projectiles[1].length; i++) {
						if(gp.projectiles[gp.currentMap][i] == null) {
							gp.projectiles[gp.currentMap][i] = projectile;
							break;
						}
					}
					projectile.useResource(this);
					shotCounter = 0;
				} 
	}
	public Color getParticleColor() {
		Color c = null;
		return c;
	}
	public int getParticleSize() {
		int size = 0;
		return size;
	}
	public int getParticleSpeed(){
		int pSpeed = 0;
		return pSpeed;
	}
	public int getParticleLife() {
		int maxLife = 0;
		return maxLife;
	}
	public void generateParticle(Entity user, Entity target) {
			
			Color c = user.getParticleColor();
			int size = user.getParticleSize();
			int speed = user.getParticleSpeed();
			int maxLife = user.getParticleLife();

			Particle particleA = new Particle(gp, c, user, size, speed, maxLife, -1,  -1);
			Particle particleB = new Particle(gp, c, user, size, speed, maxLife, -1,  1);
			Particle particleC = new Particle(gp, c, user, size, speed, maxLife, 1,  -1);
			Particle particleD = new Particle(gp, c, user, size, speed, maxLife, 1,  1);
			
			gp.particleList.add(particleA);		
			gp.particleList.add(particleB);		
			gp.particleList.add(particleC);		
			gp.particleList.add(particleD);		
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
			
			if(nextCol+gp.tileSize == goalCol+gp.tileSize && nextRow+gp.tileSize == goalRow+gp.tileSize) {
				currentSearchPath = pathOFF;
				System.out.println(worldX/gp.tileSize+ "::"+ worldY/gp.tileSize);
			}
			
		}
		
	}
	public void checkCollision() {	
		collisionOn = false;
		
		gp.collCheck.checkTile(this);
		gp.collCheck.checkObj(this, true);
		gp.collCheck.checkEntity(this, gp.npc);
		gp.collCheck.checkEntity(this, gp.monsters);
		gp.collCheck.checkEntity(this, gp.IT_Manager);
		boolean contactPlayer = gp.collCheck.playerIntersect(this);
		 
		if (contactPlayer && this.type == type_monster) {
			damagePlayer(atk);
		}
	}
	public void selectItem() {}
	public void sellItem() {}
	public void buyItem() {}
	public void knockBackAction(Entity ent, int knockBackPower, String direction) {
		ent.knockDirection = direction;
		ent.speed += knockBackPower;
		ent.knockBackState = true;
	}
	public void reaction() {}
	public void changePic() {}
	public void setLoot(Entity loot) {}
	public void move(String direction) {}
	public void huntPlayer(int interval) {
		actionDelay++;
		if(actionDelay > interval) {
			if(getDistanceX(gp.player) > getDistanceY(gp.player)) {
				if(gp.player.getCenterX() < getCenterX()) direction = "left";
				else direction = "right";
			}
			else if(getDistanceX(gp.player) < getDistanceY(gp.player)) {
				if(gp.player.getCenterY() < getCenterY()) direction = "up";
				else direction = "down";
			}
			actionDelay = 0;
		}
		
	}
	public boolean inCamera() {
		boolean inCamera = false;
			if(
		   worldX + gp.tileSize*4 > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize*4 > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY 
		   ) inCamera = true;
		return inCamera;
	}
	
 	public void update() {
 		checkCollision();
 		
 		if(!asleep) {
 			if(knockBackState) {
 				checkCollision();
// 				gp.collCheck.checkEntityCol(this, gp.monsters);
 				if(collisionOn == true) {
 					knockBackCounter = 0;
 					knockBackState = false;
 					speed = defaultSpeed;
 				} else if(!collisionOn){
 					
 					switch(knockDirection) {
 					case "up": {worldY-=speed; break;}
 					case "down":{ worldY+=speed; break;}
 					case "left": {worldX-=speed; break;}
 					case "right": {worldX+=speed; break;}
 					}
 				}
 				
 				knockBackCounter++;
 				
 				if(knockBackCounter == 10) {
 					knockBackCounter = 0;
 					knockBackState = false;
 					speed = defaultSpeed;
 				}
 				
 			}  else if(attacking) attackState();
 			else {
 				setAction();
 				checkCollision();
 				
 				
 				if(!collisionOn) {
 					switch(direction) {
 					case "up": {worldY-=speed; break;}
 					case "down":{ worldY+=speed; break;}
 					case "left": {worldX-=speed; break;}
 					case "right": {worldX+=speed; break;}
 					}
 				}
 				
 				spriteCounter++;
 				
 				if (spriteCounter > 12) {
 					if(spriteNum == 1) {spriteNum = 2;}
 					else if(spriteNum == 2) {spriteNum = 3;}
 					else if(spriteNum == 3) {spriteNum = 4;}
 					else if(spriteNum == 4) {spriteNum = 1;} 
 					spriteCounter = 0;
 				}
 			}
 			
 			
 			if(invincible) {
 				invincibleTime++;
 				if(invincibleTime == 20) {
 					invincible = false;
 					invincibleTime = 0; 					
 				}
 			}
 			if(shotCounter < 30) {shotCounter++;}
 		}
 		
 		
 		//DEBUG
		if(gp.keys.debugPressed) {
 			System.out.println(debugOn);
 			if(debugOn) debugOn = false;
 			else if(!debugOn) debugOn = true;
 		}
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		// TODO Auto-generated method stub
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(inCamera()) {
			BufferedImage image = null;
			
			 // STOP MOVING CAMERA
//			  if(gp.player.worldX < gp.player.screenX) {
//			   screenX = worldX;
//			  }
//			  if(gp.player.worldY < gp.player.screenY) {
//			   screenY = worldY;
//			  }   
//			  int rightOffset = gp.screenWidth - gp.player.screenX;      
//			  if(rightOffset > gp.worldWidth - gp.player.worldX) {
//			   screenX = gp.screenWidth - (gp.worldWidth - worldX);
//			  } 
//			  int bottomOffset = gp.screenHeight - gp.player.screenY;
//			  if(bottomOffset > gp.worldHeight - gp.player.worldY) {
//			   screenY = gp.screenHeight - (gp.worldHeight - worldY);
//			  }
			  ///////////////////
			
			  int tempScreenX = screenX;
			  int tempScreenY = screenY;
				
				
				
		switch(direction) {
		case "up":
			if(!attacking) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
			} else if(attacking) {
				tempScreenY-=up1.getHeight();
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
							if(spriteNum == 3) {image = attackUp3;}
							if(spriteNum == 4) {image = attackUp4;}
			}
			
			break;
		case "down":
			if(!attacking) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
			} else if(attacking) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
							if(spriteNum == 3) {image = attackDown3;}
							if(spriteNum == 4) {image = attackDown4;}
			}
			break;
		case "left":
			if(!attacking) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
			} else if(attacking) {
				tempScreenX-=left1.getWidth();
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
							if(spriteNum == 3) {image = attackLeft3;}
							if(spriteNum == 4) {image = attackLeft4;}
			}
			break;
		case "right":
			if(!attacking) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
			} else if(attacking) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
							if(spriteNum == 3) {image = attackRight3;}
							if(spriteNum == 4) {image = attackRight4;}
			}
			break;
		}
			
			if(type == type_monster) {
				if(invincible) {
					hpBarOn = true;
					hpBarCounter = 0;
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
				}
				
				if(dying) dyingAnim();
			}
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	
			
					
			//MONSTERS HEALTH BAR
			if(type == type_monster && hpBarOn && !type_boss) {
				
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarVal = oneScale*life;
				
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(124, 16, 14));
				g2.fillRect(screenX, screenY-15,(int)hpBarVal, 10);
				
				hpBarCounter++;
				if(hpBarCounter == 1) {
				}
				if(hpBarCounter == 60*5) {
					hpBarOn = false;
					hpBarCounter = 0;
				}
				
			}
			
			
		}
		


		if(debugOn) {
			g2.setColor(Color.red);
			g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
		
		}
		
	}
	public int getDetectedItem(Entity user, Entity target[][], String targetName) {
		int index = 777;
		
		//temp values which would change with the players direction
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(gp.player.direction) {
		case "up": nextWorldY = user.getTopY() - user.speed; break;
		case "left": nextWorldX = user.getLeftX() - user.speed; break;
		case "down": nextWorldY = user.getBottomY() + user.speed; break;
		case "right": nextWorldX = user.getRightX() + user.speed; break;
		}
		
		//get the col and roww
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		for(int i = 0; i < target[1].length; i++) {
			if(target[gp.currentMap][i] != null && target[gp.currentMap][i].getCol() == col &&
			   target[gp.currentMap][i].getRow() == row && target[gp.currentMap][i].name.equals(targetName)) {
				index = i;
			}
		}
		
		return index;
	}
}