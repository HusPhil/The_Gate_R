package entity;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityClass;
import object.ITM_Key;
import object.OBJ_Chest;
import object.OBJ_Key;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;
import object.SKL_Fireball;

public class Player extends Entity{

	KeyHandler keys;
	int keynum = 0;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 28;
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public boolean magicOn = true;
	
	//Abilities
	int timer;
	boolean DashAbility =true;
	
	public Player(GamePanel gp, KeyHandler keys) {
		super(gp);
		//PressTime for DASH
		timer = 0; 
		attacking = false;
		
		speed = 3;
		screenX = (gp.screenWidth/2) - (gp.tileSize/2);
		screenY = (gp.screenHeight/2) - (gp.tileSize/2);
		
		this.keys = keys; 
		
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(10, 18, 30, 30);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		addInventoryItems();
		direction = "down";
	}
 	public void setDefaultValues() {
		
		//PLAYER POSITION
// 		worldX = (65*48); 
//		worldY = (46*48);
		
		worldX = (13*48); 
		worldY = (31*48);
	
		//PLAYER's ITEMS
		
		 
		//PLAYER STATS
		maxLife = 10;
		life = maxLife;
		level = 1;
		str = 2;
		dex = 1;
		exp = 0;  
		nextLvlExp = 5;
		coin = 20;
		currentWeapon = new OBJ_Wooden_Sword(gp);
		currentShield = new OBJ_Wooden_Shield(gp);
		projectile = new SKL_Fireball(gp);
		maxMana = 200;
		mana = maxMana;
		atk = getAtk();  
		def = getDef();
	}
	public int getAtk() {
		attackAreaX = currentWeapon.attackAreaX;
		attackAreaY = currentWeapon.attackAreaY;
		return str * currentWeapon.atkVal;
	}
	public int getDef() {
		return dex * currentShield.defVal;
	}
	public void getPlayerImage() {
		up1 = createImage("player", "walking/up0");
		up2 = createImage("player", "walking/up1");
		up3 = createImage("player", "walking/up2");
		up4 = createImage("player", "walking/up3");
		
		down1 = createImage("player", "walking/down0");
		down2 = createImage("player", "walking/down1");
		down3 = createImage("player", "walking/down2");
		down4 = createImage("player", "walking/down3");
		
		right1 = createImage("player", "walking/right0");
		right2 = createImage("player", "walking/right1");
		right3 = createImage("player", "walking/right2");
		right4 = createImage("player", "walking/right3");
		
		left1 = createImage("player", "walking/left0");
		left2 = createImage("player", "walking/left1");
		left3 = createImage("player", "walking/left2");
		left4 = createImage("player", "walking/left3");
	}
	public void getPlayerAttackImage() {
		if(currentWeapon.type == type_sword) {
			attackUp1 = createImage("player", "attacking/PA_up0", gp.tileSize, gp.tileSize*2);
			attackUp2 = createImage("player", "attacking/PA_up1", gp.tileSize, gp.tileSize*2);
			
			attackDown1 = createImage("player", "attacking/PA_down0", gp.tileSize, gp.tileSize*2);
			attackDown2 = createImage("player", "attacking/PA_down1", gp.tileSize, gp.tileSize*2);
			
			attackLeft1 = createImage("player", "attacking/PA_left0", gp.tileSize*2, gp.tileSize);
			attackLeft2 = createImage("player", "attacking/PA_left1", gp.tileSize*2, gp.tileSize);
			
			attackRight1 = createImage("player", "attacking/PA_right0", gp.tileSize*2, gp.tileSize);
			attackRight2 = createImage("player", "attacking/PA_right1", gp.tileSize*2, gp.tileSize);
		}
		if(currentWeapon.type == type_axe) {
			attackUp1 = createImage("player", "attacking/axePA_up0", gp.tileSize, gp.tileSize*2);
			attackUp2 = createImage("player", "attacking/axePA_up1", gp.tileSize, gp.tileSize*2);
			
			attackDown1 = createImage("player", "attacking/axePA_down0", gp.tileSize, gp.tileSize*2);
			attackDown2 = createImage("player", "attacking/axePA_down1", gp.tileSize, gp.tileSize*2);
			
			attackLeft1 = createImage("player", "attacking/axePA_left0", gp.tileSize*2, gp.tileSize);
			attackLeft2 = createImage("player", "attacking/axePA_left1", gp.tileSize*2, gp.tileSize);
			
			attackRight1 = createImage("player", "attacking/axePA_right0", gp.tileSize*2, gp.tileSize);
			attackRight2 = createImage("player", "attacking/axePA_right1", gp.tileSize*2, gp.tileSize);
		}
     
	}
	public void update() {
		if(keys.enterPressed && !attackCanceled) {
			attacking = true;
			//spriteCounter = 0;
		}
		
		if(attacking) {attackState();}
		else if(keys.downPressed || 
		   keys.upPressed ||
		   keys.leftPressed ||
		   keys.rightPressed ||
		   keys.talkOn) 
		{	attackCanceled = false;
			if(keys.upPressed) {
				direction = "up";
			}
			else if(keys.downPressed) {
				direction = "down";
			}
			else if(keys.leftPressed) {
				direction = "left";
			}
			else if(keys.rightPressed) {
				direction = "right";
			} 
			collisionOn = false;
			//Tile Collision Checker, turns the collisionOn property true or false
			gp.collCheck.checkTile(this); 	
			
			//Game Object Collision Checker, turns the collisionOn property true or false
			int objIndex = gp.collCheck.checkObj(this, true); 
			pickupObj(objIndex);
			int itemIndex = gp.collCheck.checkItem(this, true); 
			pickupItem(itemIndex);
			int tileIndex = gp.collCheck.checkEntity(this, gp.IT_Manager); 
			
			
			//Entity Collision Checker
			int npcIndex = gp.collCheck.checkEntity(this, gp.npc);
			npcInteract(npcIndex);
			int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
			monContact(monIndex);
			
			if(!collisionOn && !keys.talkOn) {
				switch(direction) {
				case "up":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldY-=speed*5;} 
					else { timer = 0; speed = 5; worldY -= speed; } 
					break;
				case "down":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldY+=speed*5;} 
					else { timer = 0; speed = 5; worldY += speed; } 
					break;
				case "left":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldX-=speed*5;} 
					else { timer = 0; speed = 5; worldX -= speed; } 
					break;
				case "right":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldX+=speed*5;} 
					else { timer = 0; speed = 5; worldX += speed; } 
					break;
				}
			}
			spriteCounter++;
			if (spriteCounter > 12) {
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
			}
		}
		
		
		
		//Check event collision
		gp.eventHandler.checkEvent();
		
		projectileAction();
		if(mana <= 0) mana = maxMana;
		if(life <= 0) life = maxLife;
		
		//delay players damage receive
		if(invincible) {
			invincibleTime++;
			if(invincibleTime == 60) {
				invincible = false;
				invincibleTime = 0; 					
			}
		}
		
		if(shotCounter < 30) {shotCounter++;}
			
		}
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					gp.playSE(2);
					gp.projectileList.add(projectile);
					projectile.useResource(this);
					shotCounter = 0;
				} 
	}
	public void attackState() {
		spriteCounter++;
		if(spriteCounter <= 5) {spriteNum = 1;}
		if(spriteCounter > 5 && spriteCounter <= 25) {
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

				worldY-=attackAreaY.height+18;
				solidArea.height = attackAreaY.height;
				solidArea.width = attackAreaY.width;
				break;
			case "down": 

				worldY+=attackAreaY.height; 
				solidArea.height = attackAreaY.height;
				solidArea.width = attackAreaY.width;
				break;
			case "left": 
				worldX-=attackAreaX.width; 
				solidArea.height = attackAreaX.height;
				solidArea.width = attackAreaX.width;
				break;
			case "right": 
				worldX+=attackAreaX.width; 
				solidArea.height = attackAreaX.height;
				solidArea.width = attackAreaX.width;
				break;
			}
			
			//Change the solidArea to attackArea
			
			
			//Check Monster collision
			int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
			manageMonDmg(monIndex, atk);
			
			int tileIndex = gp.collCheck.checkEntity(this, gp.IT_Manager);
			manageTileDmg(tileIndex);
			
			//Reset player's solidArea and Position
			worldX = currentWorldX;
			worldY = currentWorldY;
			
			
			solidArea.width = currentsolidAreaW;
			solidArea.height = currentsolidAreaH;
			
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void addInventoryItems() {
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new ITM_Key(gp));
		inventory.add(new OBJ_Chest(gp));
	}
	public void manageTileDmg(int i) {

		if(i !=  777 && gp.IT_Manager[i].destroyOn && gp.IT_Manager[i].checkReqItem
			(currentWeapon) && !gp.IT_Manager[i].invincible) {
			
			
			gp.IT_Manager[i].life--;	
			gp.IT_Manager[i].invincible = true;
				
			gp.IT_Manager[i].playSE();;
			generateParticle(gp.IT_Manager[i], gp.IT_Manager[i]);
			
			if(gp.IT_Manager[i].life == 0) {
			gp.IT_Manager[i] = gp.IT_Manager[i].destroyedForm();
			}
				
		
		}
		
	}
	public void manageMonDmg(int i, int atk) {
		if (i != 777) {
			
			if(!gp.monsters[i].invincible) {
			
				gp.playSE(1);
				int dmg = atk - gp.monsters[i].def;
				if(dmg < 0) dmg = 0;
				gp.monsters[i].life-=dmg;
				if(gp.monsters[i].life < 0) gp.monsters[i].life = 0;
				
			
				
				gp.monsters[i].invincible = true;
				gp.monsters[i].damageReaction();
				if(gp.monsters[i].life <= 0) {
					gp.monsters[i].dying = true;
					gp.gui.addMessage("You gained " + gp.monsters[i].exp + " exp");
					exp += gp.monsters[i].exp; 
					checkLvlUp();
					
				}
			}
		}
	}
	public void checkLvlUp() {
		if(exp >= nextLvlExp) {
			level++;
			nextLvlExp+=20;
			maxMana+=30;
			str++;
			dex++;
			def++;
			atk = getAtk();
			def = getDef();
			projectile.atk +=10;
			maxLife+=2;
		//	if(maxLife <=10) ;
			gp.gameState = gp.dialougeState;
			gp.gui.cD ="YOU LEVELED UP! You feel stronger.";
			gp.gui.addMessage("You are now level " + level + "!");	
		}
		
	}
	public void pickupItem(int i) {

		if(i != 777) {
		Entity obj = gp.items[i];
		String text = "";
		
		if(obj.type == non_inventory) {
			gp.playSE(0);
			obj.use(this);
			gp.items[i] = null;
		}
		
		//ADD THE PICKED UP OBJECT TO INVENTORY
			//the inventory must be checked if full
		else if(obj.type == type_consumables || obj.type == type_sword || obj.type == type_shield || obj.type == type_axe) {
			if(inventory.size() < maxInventorySize) {
				gp.playSE(0);
				inventory.add(obj);
				text = "You picked up " + obj.name + ".";
				gp.gui.addMessage(text);
			} else {
				gp.gameState = gp.dialougeState;
				text = "You are heavily burdened, you cannot \ncarry anymore.";
				gp.gui.cD = text;
				gp.gui.addMessage("Inventory Full!");
			}
			
			gp.items[i] = null;
		}
		}
	
	}
	public void pickupObj(int i) {
		if(i != 777) {
		Entity obj = gp.gameObjs[i];
		String text = "";
		
		if(obj.type == non_inventory) {
			obj.use(this);
			gp.gameObjs[i] = null;
		}
		
		//ADD THE PICKED UP OBJECT TO INVENTORY
			//the inventory must be checked if full
		else if(obj.type == type_consumables || obj.type == type_sword || obj.type == type_shield || obj.type == type_axe) {
			if(inventory.size() < maxInventorySize) {
				inventory.add(obj);
				text = "You picked up " + obj.name + ".";
			} else {
				gp.gameState = gp.dialougeState;
				text = "You are heavily burdened, you cannot \ncarry anymore.";
				gp.gui.cD = text;
			}
			gp.gui.addMessage(text);
			gp.gameObjs[i] = null;
		}
		}
	}
	public void npcInteract(int i) {
		if (i != 777) {
			if(gp.keys.talkOn) {
				gp.gameState = gp.dialougeState;
				gp.npc[i].speak();		
			} 
			if(gp.gui.cD == gp.npc[i].dialouges[19]) {
				magicOn = true;
			}
			}
		}
	public void monContact(int i) {
		if (i != 777) {
			if(!invincible && !gp.monsters[i].dying) {
				int dmg = gp.monsters[i].atk - def;
				if(dmg < 0) dmg = 0;
				life-=dmg;
				invincible = true;
			}
		}
	}
	public void selectItem() {
		
		int itemIndex = gp.gui.getItemIndex();
		
		if(itemIndex < inventory.size()) {
		
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword ||
			   selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				atk = getAtk();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				currentShield = selectedItem;
				atk = getDef();
			}
			if(selectedItem.type == type_consumables) {
				inventory.get(itemIndex).use(this);
				inventory.remove(itemIndex);
			}
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		BufferedImage image = null;
		
			switch(direction) {
			case "up":
				if(!attacking) {
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
					if(spriteNum == 3) {image = up3;}
					if(spriteNum == 4) {image = up4;}
					if(!keys.upPressed) image = up1;
				} else if(attacking) {
					tempScreenY-=gp.tileSize;
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
				}
				
				break;
			case "down":
				if(!attacking) {
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
					if(spriteNum == 3) {image = down3;}
					if(spriteNum == 4) {image = down4;}
					if(!keys.downPressed) image = down1;
				} else if(attacking) {
					if(spriteNum == 1) {image = attackDown1;}
					if(spriteNum == 2) {image = attackDown2;}
				}
				break;
			case "left":
				if(!attacking) {
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
					if(spriteNum == 3) {image = left3;}
					if(spriteNum == 4) {image = left4;}
					if(!keys.leftPressed) image = left1;
				} else if(attacking) {
					tempScreenX-=gp.tileSize;
					if(spriteNum == 1) {image = attackLeft1;}
					if(spriteNum == 2) {image = attackLeft2;}
				}
				break;
			case "right":
				if(!attacking) {
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
					if(spriteNum == 3) {image = right3;}
					if(spriteNum == 4) {image = right4;}
					if(!keys.rightPressed) image = right1;
				} else if(attacking) {
					if(spriteNum == 1) {image = attackRight1;}
					if(spriteNum == 2) {image = attackRight2;}
				}
				break;
			}
			
			if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
			// DEBUG
			// AttackArea
//			tempScreenX = screenX + solidArea.x;
//			tempScreenY = screenY + solidArea.y;		
//			switch(direction) {
//			case "up": tempScreenY = screenY - attackAreaY.height; break;
//			case "down": tempScreenY = screenY + attackAreaY.height; break; 
//			case "left": tempScreenX = screenX - attackAreaX.width; break;
//			case "right": tempScreenX = screenX + attackAreaX.width; break;
//			}				
//			g2.setColor(Color.red);
//	                g2.setStroke(new BasicStroke(1));
//			g2.drawRect(tempScreenX, tempScreenY, attackAreaY.width, attackAreaY.height);
//			g2.drawRect(tempScreenX, tempScreenY, attackAreaX.width, attackAreaX.height);
	}
}
