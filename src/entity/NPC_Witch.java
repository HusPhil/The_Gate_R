package entity;


import main.GamePanel;
import object.ITM_Key;
import object.OBJ_Health_Potion;
import object.OBJ_Iron_Shield;
import object.OBJ_Iron_Sword;
import object.OBJ_Lantern;
import object.OBJ_Iron_Axe;
import object.OBJ_Wooden_Shield;
import object.OBJ_Wooden_Sword;

public class NPC_Witch extends Entity{
	public final static int quest1a = 0;
	public final static int quest1b = 1;
	public final static int quest1c = 2;
	public final static int quest1d = 3;
	public final static int quest1e = 4;

	public static final String NPC_Name = "Witch";
	public NPC_Witch(GamePanel gp) {
		super(gp);
		type = type_npc;
		name = NPC_Name;
		int i = rN.nextInt(4)+1;
		if(i ==1) direction = "up";
		if(i ==2) direction = "down";
		if(i ==3) direction = "left";
		if(i ==4) direction = "right";
		solidArea.x = 8;
		solidArea.y = 8;
		solidArea.height = 40;
		solidArea.width = 40;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		
		 
		speed = 1;
		getNpcImage();
		setDialouge();
		addInventoryItems();
	}

	private void getNpcImage() {
		down1 = createImage("npc", "/witch/up0");
		down2 = createImage("npc", "/witch/up1");
		down3 = createImage("npc", "/witch/up2");
		down4 = createImage("npc", "/witch/up0");

		up1 = createImage("npc", "/witch/up0");
		up2 = createImage("npc", "/witch/up1");
		up3 = createImage("npc", "/witch/up2");
		up4 = createImage("npc", "/witch/up0");
		
		left1 = createImage("npc", "/witch/up0");
		left2 = createImage("npc", "/witch/up1");
		left3 = createImage("npc", "/witch/up2");
		left4 = createImage("npc", "/witch/up0");
		
		right1 = createImage("npc", "/witch/up0");
		right2 = createImage("npc", "/witch/up1");
		right3 = createImage("npc", "/witch/up2");
		right4 = createImage("npc", "/witch/up0");
	}
	
	public void setAction() {
		//if (gp.keys.talkOn) gp.gameState = gp.tradingState;
		if(pathAI) {
//			int pWorldX = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
//			int pWorldY = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
			//searchPath(57, 35);
			
			
			
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
//			if(!projectile.alive && shotCounter == 30) {
//				projectile.set(worldX, worldY, true, direction, this);
//				for(int i = 0; i < gp.projectiles[1].length; i++) {
//					if(gp.projectiles[gp.currentMap][i] == null) {
//						gp.projectiles[gp.currentMap][i] = projectile;
//						break;
//					}
//				}
//				shotCounter = 0;
//				projectile.setDamage();
//			}
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
	public void setDialouge() {
//		dialogues[0][] = "You are heavily burdened, you cannot \ncarry anymore.";
		int i = 0;
		
		dialogues[quest1a][i] = "Why, hello there young man!"; i++;
		dialogues[quest1a][i] = "..."; i++;
		dialogues[quest1a][i] = "Your eyes..."; i++;
		dialogues[quest1a][i] = "You're not from here, are you?"; i++;
		
		i = 0;
		dialogues[quest1b][i] = "I see, then someone sent you here, huh?"; i++;
		dialogues[quest1b][i] = "Well, hunt some monsters in the forest and get some"
				+ "\ningredients for me.."; i++;
		dialogues[quest1b][i] = "Maybe then, I could help you with your problem.."; i++;
		dialogues[quest1b][i] = "Get me the following: "
				+ "\n \t*3 Gels from Slimes"
				+ "\n \t*5 Trenkflesh from Trenklins"; i++;
		dialogues[quest1b][i] = "You need to go downstairs to reach the other side\n"
				+ "of the forest. However, I lost the key for the door."; i++;
		dialogues[quest1b][i] = "So now you have to find the key first before you\n"
				+ "can proceed."; i++;
		dialogues[quest1b][i] = "Well, that's all.. good luck young man. "; i++;
		dialogues[quest1b][i] = "Don't die now.. hihihi"; i++; 
		
		i = 0;
		dialogues[quest1c][i] = "Very good! It seems you have done as I instructed!\n"
				+ "I shall take all that you have gathered!"; i++; 
		dialogues[quest1c][i] = "Now young man, wait as I make an amulet that shall\n"
				+ "break the curse of the villagers!"; i++; 
		dialogues[quest1c][i] = "..."; i++;
		dialogues[quest1c][i] = "...."; i++;
		dialogues[quest1c][i] = "There! A successful enchantment!"; i++; 
		dialogues[quest1c][i] = "Here! Take this, young man, and save the village!"; i++; 
		
		i = 0;
		dialogues[quest1d][i] = "Nevertheless, this is only a temporary solution, you must\n"
				+ "find and defeat the cursed tree that has become evil as\n"
				+ "a consequence of people not fulfilling SDGs, otherwise\n"
				+ "known in this world as the 'Harmonial Principles'"; i++;
		dialogues[quest1d][i] = "That is to say, the failed to Protect, restore and promote\n"
				+ "sustainable use of terrestrial ecosystems, sustainably\n"
				+ "manage forests, combat desertification, and halt and\n"
				+ "reverse land degradation and halt biodiversity loss."; i++;
		dialogues[quest1d][i] = "In other words, they failed to fulfill the 15th of the\n"
				+ "Harmonial Principles: Life on Land."; i++;
		dialogues[quest1d][i] = "Well, if they had, you wouldn't be here now would you?"; i++;
		dialogues[quest1d][i] = "Anyways, good luck again, young man.."; i++;
		dialogues[quest1d][i] = "Hi hi hi hi.."; i++;
		
		i = 0;
		dialogues[quest1e][i] = "Don't die now, hi hi hi hi.."; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, 0);
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
			
			if(nextCol == goalCol && nextRow == goalRow) {
				pathAI = false;	
				gp.gui.currentDialogue = "heh, yu fund me. wanna trade?";
				worldX = goalCol*48;
				worldY = goalRow*48;
				if(worldX == goalCol*48 && worldY == goalRow*48) gp.gameState = gp.dialogueState;
				speed = 0;
				
			}
			
		}
		
	}
	public void addInventoryItems() {
		inventory.add(new OBJ_Iron_Axe(gp));
		inventory.add(new OBJ_Wooden_Shield(gp)); 
		inventory.add(new OBJ_Health_Potion(gp));
		inventory.add(new OBJ_Wooden_Sword(gp));
		inventory.add(new ITM_Key(gp));
		inventory.add(new OBJ_Iron_Shield(gp));
		inventory.add(new OBJ_Iron_Sword(gp));
		inventory.add(new OBJ_Lantern(gp));
	}
	
	public void sellItem() {
		
		int itemIndex = gp.gui.getItemIndex();
		
		if(itemIndex < inventory.size()) {
		
			if(gp.player.coin >= inventory.get(itemIndex).coin) {
				gp.player.coin -= inventory.get(itemIndex).coin;
				
				if(gp.player.itemObtainable(inventory.get(itemIndex))) {
				}
				else {
					startDialogue(this, 1);
					gp.gui.substate = 0;
				}
				}
			else {
				startDialogue(this, 4);
				gp.gui.substate = 0;
			}
		} 
	}
	public void buyItem() {
		
		int itemIndex = gp.gui.getItemIndex();
		int playerInventorySize = gp.player.inventory.size(); 
		
		if(itemIndex < playerInventorySize) {
			
			if(gp.player.inventory.get(itemIndex) != gp.player.currentShield &&
			   gp.player.inventory.get(itemIndex) != gp.player.currentWeapon) {
				gp.player.coin += (gp.player.inventory.get(itemIndex).coin) - (gp.player.inventory.get(itemIndex).coin)/4;
				
				if(gp.player.inventory.get(itemIndex).ammount > 1 ) {
					gp.player.inventory.get(itemIndex).ammount--;
				} else gp.player.inventory.remove(itemIndex);
			} 
			else {
				startDialogue(this, 2);
				
				gp.gui.substate = 0;
			}
			
		} else if(itemIndex == maxInventorySize-1){
			gp.gui.substate = 0;
			gp.gui.selectItem = -1;
			gp.keys.enterPressed = false;
		} else {
//			
			startDialogue(this, 3);
			gp.gui.substate = 0;
			gp.gui.selectItem = 0;
		}
		
	}
}
