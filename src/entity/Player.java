package entity;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.ITM_Key;
import object.OBJ_Lantern;
import object.OBJ_Wooden_Axe;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;
import object.SKL_Fireball;

public class Player extends Entity{

	KeyHandler keys;
	String temp;
	int keynum = 0;
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	
	public boolean magicOn = true;
	public boolean lightUpdated = false;
	
	public int offsetRand = 0;
	
	//Abilities
	int timer;
	boolean DashAbility =true;
	
	public Player(GamePanel gp, KeyHandler keys) {
		super(gp);
		setDialogue();
		//PressTime for DASH
		name = "Player";
		timer = 0; 
		attacking = false;
		dialogueIndex = 0;
		
		screenX = (gp.screenWidth/2) - (gp.tileSize/2);
		screenY = (gp.screenHeight/2) - (gp.tileSize/2);
		
		this.keys = keys; 
		this.gp = gp;
		
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(12, 18, 24, 30);
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		addInventoryItems();
		
		direction = "down";
	}
 	public void setDefaultValues() {
		
	//	PLAYER POSITION
 		gp.currentArea = 0;
 		worldX = (14*48); 
		worldY = (10*48);
		
	
		//PLAYER's ITEMS
		
		 
		//PLAYER STATS
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 6;
		life = maxLife;
		level = 1;
		str = 6;
		dex = 1;
		exp = 0;  	
		nextLvlExp = 5;
		coin = 1000000900;
		currentLightItem = null;
		currentWeapon = new OBJ_Wooden_Sword(gp);
		currentShield = new OBJ_Wooden_Shield(gp);
		projectile = new SKL_Fireball(gp);
		maxMana = 200;
		mana = maxMana;
		
		atk = getAtk();  
		def = getDef();
	}
 	public void setDialogue() {
		int i = 0;
		int j = 0;
		dialogues[i][j] = "You leveled up!"; j++;
		dialogues[i][j] = "You leveled up! You feel stronger."; j++;
		
		i++; j = 0;
		dialogues[i][j] = "You are heavily burdened, you cannot \ncarry anymore."; j++;
		
		i++; j = 0;
		dialogues[i][j] = "Press 'Y' to save the game. Esc to cancel"; j++;
		
	}
 	
 	public int getCurrenntWeaponSlot() {
 		int weaponSlot = 0;
 		
 		for(int i = 0; i < inventory.size(); i++) {
 			if(inventory.get(i) == currentWeapon)
 				weaponSlot = i;
 		}
 		
 		return weaponSlot;
 	}
 	
 	public int getCurrenntShieldSlot() {
 		int shieldSlot = 0;
 		
 		for(int i = 0; i < inventory.size(); i++) {
 			if(inventory.get(i) == currentShield)
 				shieldSlot = i;
 		}
 		return shieldSlot;
 	}
 	
	public int getAtk() {
		motion_duration1 = currentWeapon.motion_duration1;
		motion_duration2 = currentWeapon.motion_duration2;
		
		attackArea.x = solidArea.x;
		attackArea.y = solidArea.y;
		
		
		return str/2 * currentWeapon.atkVal;
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
	public BufferedImage getCurrentImage() {
		BufferedImage image = null;
		switch(direction) {
		case "up": image = up1; break;
		case "down": image = down1; break;
		case "left": image = left1; break;
		case "right": image = right1; break;
		}
		return image;
	}
	public void getPlayerAttackImage() {
		if(currentWeapon.type == type_sword) {
			attackUp1 = createImage("player", "attacking/PA_up0", gp.tileSize, gp.tileSize*2);
			attackUp2 = createImage("player", "attacking/PA_up1", gp.tileSize, gp.tileSize*2);
			attackUp3 = createImage("player", "attacking/PA_up2", gp.tileSize, gp.tileSize*2);
			attackUp4 = createImage("player", "attacking/PA_up3", gp.tileSize, gp.tileSize*2);
			
			attackDown1 = createImage("player", "attacking/PA_down0", gp.tileSize, gp.tileSize*2);
			attackDown2 = createImage("player", "attacking/PA_down1", gp.tileSize, gp.tileSize*2);
			attackDown3 = createImage("player", "attacking/PA_down2", gp.tileSize, gp.tileSize*2);
			attackDown4 = createImage("player", "attacking/PA_down3", gp.tileSize, gp.tileSize*2);
			
			attackLeft1 = createImage("player", "attacking/PA_left0", gp.tileSize*2, gp.tileSize);
			attackLeft2 = createImage("player", "attacking/PA_left1", gp.tileSize*2, gp.tileSize);
			attackLeft3 = createImage("player", "attacking/PA_left2", gp.tileSize*2, gp.tileSize);
			attackLeft4 = createImage("player", "attacking/PA_left3", gp.tileSize*2, gp.tileSize);
			
			attackRight1 = createImage("player", "attacking/PA_right0", gp.tileSize*2, gp.tileSize);
			attackRight2 = createImage("player", "attacking/PA_right1", gp.tileSize*2, gp.tileSize);
			attackRight3 = createImage("player", "attacking/PA_right2", gp.tileSize*2, gp.tileSize);
			attackRight4 = createImage("player", "attacking/PA_right3", gp.tileSize*2, gp.tileSize);
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
		if(currentWeapon.type == type_pickaxe) {
			attackUp1 = createImage("player", "attacking/boy_pick_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = createImage("player", "attacking/boy_pick_up_2", gp.tileSize, gp.tileSize*2);
			attackUp3 = attackUp1;
			attackUp4 = attackUp2;
			
			attackDown1 = createImage("player", "attacking/boy_pick_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = createImage("player", "attacking/boy_pick_down_2", gp.tileSize, gp.tileSize*2);
			attackDown3 = attackDown1;
			attackDown4 = attackDown2;
			
			attackLeft1 = createImage("player", "attacking/boy_pick_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = createImage("player", "attacking/boy_pick_left_2", gp.tileSize*2, gp.tileSize);
			attackLeft3 = attackLeft1;
			attackLeft4 = attackLeft2;
			
			attackRight1 = createImage("player", "attacking/boy_pick_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = createImage("player", "attacking/boy_pick_right_2", gp.tileSize*2, gp.tileSize);
			attackRight3 = attackRight1;
			attackRight4 = attackRight2;
		}
     
	}
	
	
	
	public void projectileAction() {
		if(gp.keys.fireAway && !projectile.alive && 
				   shotCounter == 30 && projectile.sufficientResource(this)) {
					projectile.set(worldX, worldY, true, direction, this);
					gp.playSE(2);
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
	public void addInventoryItems() {
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new ITM_Key(gp));
		inventory.add(new OBJ_Lantern(gp));
		inventory.add(new OBJ_Wooden_Axe(gp));
	}
	
	public void attackState() {
		
		
		spriteCounter++;
		
		if(spriteCounter > motion_duration1 && spriteCounter <= motion_duration2 && (spriteNum == 3)) {
			gp.collCheck.checkTile(this);
			gp.collCheck.checkObj(this, true);
			gp.collCheck.checkEntity(this, gp.npc);
			gp.collCheck.checkEntity(this, gp.monsters);
			gp.collCheck.checkEntity(this, gp.IT_Manager);
			if(!collisionOn) {
				switch(direction) {
				case "up": worldY--; break;
				case "down": worldY++; break;
				case "right": worldX++; break;
				case "left": worldX--; break;
				}
			}
			
			spriteNum = 4;
			//SAVE THE CURRENT DATA OF PLAYER BEFORE IT IS CHANGED
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int currentsolidAreaW = solidArea.width;
			int currentsolidAreaH = solidArea.height;
			
//			Rectangle currentSolidArea = solidArea;
			//int tempHolder;
			
			// Check the players direction to adjust the solid area
			switch(direction) {
			case "up": 
				worldY-= gp.tileSize;
				break;
			case "down": 
				worldY+=attackArea.height; 
				break;
			case "left": 
				worldX-=attackArea.width; 
				break;
			case "right": 
				worldX+=attackArea.width; 
				break;
			}
			
			
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
//			System.out.println("X:" +  attackAreaX); System.out.println("Y: " + attackAreaY);
			//Change the solidArea to attackArea
//			solidArea = attackArea;
			
			
			//Check Monster collision
			int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
			manageMonDmg(monIndex, atk, currentWeapon.knockBackPower, direction);
			
			//check interactive il collision
			int tileIndex = gp.collCheck.checkEntity(this, gp.IT_Manager);
			manageTileDmg(tileIndex);
			
			//check projectile collision
			int projectileIndex = gp.collCheck.checkEntity(this, gp.projectiles);
			manageProjectileDmg(projectileIndex);
			
			//Reset player's solidArea and Position
			worldX = currentWorldX;
			worldY = currentWorldY;
			
			
			solidArea.width = currentsolidAreaW;
			solidArea.height = currentsolidAreaH;
			
//			solidArea = currentSolidArea;
			
			
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

	public void knockBackAction(Entity ent, int knockBackPower, String direction) {
		ent.knockDirection = direction;
		ent.speed += knockBackPower;
		ent.knockBackState = true;
	}
	public void manageTileDmg(int i) {

		if(i !=  777 && gp.IT_Manager[gp.currentMap][i].destroyOn && gp.IT_Manager[gp.currentMap][i].checkReqItem
			(currentWeapon) && !gp.IT_Manager[gp.currentMap][i].invincible) {
			
			
			gp.IT_Manager[gp.currentMap][i].life--;	
			gp.IT_Manager[gp.currentMap][i].invincible = true;
				
			gp.IT_Manager[gp.currentMap][i].playSE();;
			generateParticle(gp.IT_Manager[gp.currentMap][i], gp.IT_Manager[gp.currentMap][i]);
			
			if(gp.IT_Manager[gp.currentMap][i].life == 0) {
			gp.IT_Manager[gp.currentMap][i] = gp.IT_Manager[gp.currentMap][i].destroyedForm();
			}
				
		
		}
		
	}
	public void manageMonDmg(int i, int atk, int knockBackPower, String direction) {
		if (i != 777) {
			
			if(!gp.monsters[gp.currentMap][i].invincible) {
				knockBackAction(gp.monsters[gp.currentMap][i], currentWeapon.knockBackPower, direction);
				gp.playSE(1);
				int dmg = atk - gp.monsters[gp.currentMap][i].def;
				if(dmg < 0) dmg = 0;
				gp.monsters[gp.currentMap][i].life-=dmg;
				if(gp.monsters[gp.currentMap][i].life < 0) gp.monsters[gp.currentMap][i].life = 0;
				
				gp.monsters[gp.currentMap][i].invincible = true;
				gp.monsters[gp.currentMap][i].damageReaction();
				
				if(gp.monsters[gp.currentMap][i].life <= 0) {
					gp.monsters[gp.currentMap][i].dying = true;
					gp.gui.addMessage("You gained " + gp.monsters[gp.currentMap][i].exp + " exp");
					exp += gp.monsters[gp.currentMap][i].exp; 
					checkLvlUp();
					
				}
			}
		}
	}
	public void manageProjectileDmg(int i) {
		if(i != 777) {
			Entity projectile = gp.projectiles[gp.currentMap][i];
			
			projectile.alive = false;
			generateParticle(projectile, projectile);
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
			startDialogue(this, 0);
			gp.gui.addMessage("You are now level " + level + "!");	
			checkLvlUp();
		}
		
	}
	public int searchItemInInventory(String itemName) {
		int index = 777;
		//scan inventory
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean itemObtainable(Entity item) {
		Entity newItem = gp.objGen.getObjectFromName(item.name);
		
		boolean result = false;
			if(newItem.stackable) {
				int index = searchItemInInventory(newItem.name);
				if(index != 777) {
					inventory.get(index).ammount++;
					result = true;
				} else {
					if(inventory.size() != maxInventorySize) {
						inventory.add(newItem);
						result = true;
					}
				}
			} else {
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					result = true;
				}
			}
		return result;
	}
	public void pickupItem(int i) {

//		if(i != 777) {
//		Entity obj = gp.items[gp.currentMap][i];
//		String text = "";
//		
//		if(obj.type == non_inventory) {
//			gp.playSE(0);
//			obj.use(this);
//			gp.items[gp.currentMap][i] = null;
//		}
//		
//		//ADD THE PICKED UP OBJECT TO INVENTORY
//			//the inventory must be checked if full
//		else if(itemObtainable(gp.items[gp.currentMap][i])) {
//				gp.playSE(0);
//				text = "You picked up " + obj.name + ".";
//				gp.gui.addMessage(text);
//				gp.items[gp.currentMap][i] = null;
//			} else {
//				gp.gameState = gp.dialogueState;
//				text = "You are heavily burdened, you cannot \ncarry anymore.";
//				gp.gui.currentDialogue = text;
//				gp.gui.addMessage("Inventory Full!");
//			}
//		}
	}
	public void interactObj(int i) {
		//ADD THE PICKED UP OBJECT TO INVENTORY
		//the inventory must be checked if full
		//Non-inventory type items
		if(i != 777) {
		String text = "";
		Entity obj = gp.gameObjs[gp.currentMap][i];
		
		if(obj.type == non_inventory) {
			gp.playSE(0);
			obj.use(this);
			gp.gameObjs[gp.currentMap][i] = null;
		}
		//Interactive objects type
		else if(gp.gameObjs[gp.currentMap][i].type == type_interactiveObjects ) {
			attackCanceled = true;
			if(gp.keys.enterPressed) {
				gp.gameObjs[gp.currentMap][i].reaction();
				attackCanceled = false;
			}
		}
		else if(itemObtainable(obj)) {
			
			gp.playSE(0);
			text = "You picked up " + obj.name + ".";
			gp.gui.addMessage(text);
			gp.gameObjs[gp.currentMap][i] = null;
		} else {
			
			gp.gameObjs[gp.currentMap][i] = null;
			gp.gui.addMessage("Inventory Full!");
			startDialogue(this, 1);
		}
		
		}
	}
	public void interactNPC(int i) {
		if (i != 777) {
			if(gp.keys.talkOn) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak();
			} 
//			if(gp.gui.currentDialogue == gp.npc[gp.currentMap][i].dialouges[19]) {
//				magicOn = true;
//			}
			gp.npc[gp.currentMap][i].move(direction);
		}
		}
	public void monContact(int i) {
		if (i != 777) {
			if(!invincible && !gp.monsters[gp.currentMap][i].dying) {
				int dmg = gp.monsters[gp.currentMap][i].atk - def;
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
			   selectedItem.type == type_axe ||
			   selectedItem.type == type_pickaxe) {
				currentWeapon = selectedItem;
				atk = getAtk();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				currentShield = selectedItem;
				atk = getDef();
			}
			if(selectedItem.type == type_lightSource) {
				if(currentLightItem == selectedItem)
					currentLightItem = null;
				else currentLightItem = selectedItem; 
				
				lightUpdated = true;
			}
			if(selectedItem.type == type_consumables) {
				inventory.get(itemIndex).use(this);
				
			}
		}
		
	}
	
	
	

	public void update() {
		setDialogue();
		if(gp.keys.debugPressed) {
 			System.out.println(debugOn);
 			if(debugOn) debugOn = false;
 			else debugOn = true;
 			
 		} //else debugOn = true;
		
		collisionOn = false;
		//Tile Collision Checker, turns the collisionOn property true or false
		gp.collCheck.checkTile(this); 	
		
		//Game Object Collision Checker, turns the collisionOn property true or false
		gp.collCheck.checkItem(this, true); 
		gp.collCheck.checkObj(this, true);
		gp.collCheck.checkEntity(this, gp.IT_Manager); 
		gp.collCheck.checkEntity(this, gp.buildings); 
		
		
		if(attacking) {
			if(direction == "up" || direction == "down") {
				attackArea.width = currentWeapon.attackAreaY.width;
				attackArea.height = currentWeapon.attackAreaY.height;
				if(direction == "up") attackArea.y = -10;
			}
				
			if(direction == "left" || direction == "right") {
				attackArea.height = currentWeapon.attackAreaX.height;
				attackArea.width = currentWeapon.attackAreaX.width;
			}
			attackState();
		}
		if(knockBackState) {
			collisionOn = false;
			
			gp.collCheck.checkTile(this);
			gp.collCheck.checkObj(this, true);
			gp.collCheck.checkEntity(this, gp.npc);
			gp.collCheck.checkEntity(this, gp.monsters);
			gp.collCheck.checkEntity(this, gp.IT_Manager);
			
			attacking = false;
			
			if(collisionOn == true) {
				knockBackCounter = 0;
				knockBackState = false;
				speed = defaultSpeed;
			} else 
			if(!collisionOn){
				
				switch(knockDirection) {
				case "up": {worldY-=speed; break;}
				case "down":{ worldY+=speed; break;}
				case "left": {worldX-=speed; break;}
				case "right": {worldX+=speed;break;}
				}
				
			}
			knockBackCounter++;
			if(knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBackState = false;
				speed = defaultSpeed;
			}
		}
		
		if(keys.enterPressed && !attackCanceled) {
			attacking = true;
			//spriteCounter = 0;
		}
		else
		if(keys.downPressed || 
		   keys.upPressed ||
		   keys.leftPressed ||
		   keys.rightPressed ||
		   keys.talkOn) 
		{	attackCanceled = false;
			
			attacking = false;
		
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
			
			
			//Entity Collision Checker
			int npcIndex = gp.collCheck.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			int monIndex = gp.collCheck.checkEntity(this, gp.monsters);
			monContact(monIndex);
			
			if(!collisionOn && !keys.talkOn) {
				switch(direction) {
				case "up":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldY-=(speed*5);} 
					else { timer = 0; speed = 5; worldY -= speed; } 
					break;
				case "down":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldY+=(speed*5);} 
					else { timer = 0; speed = 5; worldY += speed; } 
					break;
				case "left":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldX-=(speed*5);} 
					else { timer = 0; speed = 5; worldX -= speed; } 
					break;
				case "right":
					if(keys.dashPressed && DashAbility) { timer++; if(timer == 5) {speed=0; timer = 0;} worldX+=(speed*5);} 
					else { timer = 0; speed = 5; worldX += speed; } 
					break;
				}
			}
			spriteCounter++;
			if (spriteCounter > 15) {
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
		
		int objIndex = gp.collCheck.checkObj(this, true);
//		pickupItem(itemIndex);
		interactObj(objIndex);
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
		
		if(life <= 0) gp.gameState = gp.gameOverState;
			
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
					if(!keys.downPressed) image = down1;
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
					if(!keys.leftPressed) image = left1;
				} else if(attacking) {
					tempScreenX-=gp.tileSize;
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
					if(!keys.rightPressed) image = right1;
				} else if(attacking) {
					if(spriteNum == 1) {image = attackRight1;}
					if(spriteNum == 2) {image = attackRight2;}
					if(spriteNum == 3) {image = attackRight3;}
					if(spriteNum == 4) {image = attackRight4;}
				}
				break;
			}
			
			if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			
			if(drawing) g2.drawImage(image, tempScreenX, tempScreenY, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
			// DEBUG
			//AttackArea
			if(debugOn) {
				
				tempScreenX = screenX + solidArea.x;
				tempScreenY = screenY + solidArea.y;
				
				int offsetY = solidArea.y + (attackArea.height-solidArea.height);
				int offsetX = solidArea.x + (attackArea.width-solidArea.width);
				
				switch(direction) {
				case "up": tempScreenY = (screenY + offsetY - attackArea.height) - solidArea.y; break;
				case "down": tempScreenY = screenY + offsetY + attackArea.height; break; 
				case "left": tempScreenX = (screenX - offsetX)  - solidArea.x; break;
				case "right": tempScreenX =  screenX + offsetX + attackArea.width; break;
				}	
				
				g2.setColor(Color.yellow);
				g2.setStroke(new BasicStroke(1));
				
				//if(direction == "up" || direction == "down")
//				System.out.println(solidArea.width + "," + solidArea.height);
				g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
				
				//(direction == "left" || direction == "right")
				//g2.drawRect(tempScreenX, tempScreenY, currentWeapon.attackAreaX.width, currentWeapon.attackAreaX.height);
				
				g2.setColor(Color.red);
				g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
			}

		
	}
}
