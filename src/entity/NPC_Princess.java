package entity;

import java.awt.Rectangle;

import DataHandling.GameProgress;
import main.GamePanel;
import object.OBJ_TerraBlade;
import object.SKL_MudBall;

public class NPC_Princess extends Entity{
	//DialogueSets
	public static final int thankPlayerA = 0;
	public static final int thankPlayerB = 1;
	public static final int thankPlayerC = 2;
	public static final int thankPlayerD = 3;
	
	public static final int playerRequestA = 4;
	public static final int playerRequestB = 5;
	public static final int playerRequestC = 6;
	public static final int playerRequestD = 7;
	public static final int playerRequestE = 8;
	public static final int playerRequestF = 9;
	
	public static final int playerCraftA = 10;
	public static final int playerCraftB = 11;
	public static final int playerCraftC = 12;
	public static final int playerCraftD = 13;
	
	//SearchPath
	public static final int find_player = 1;
	public static final int find_home = 2;
	public static final int chase_player = 3;

	public static final String NPC_Name = "Princess Riri";
	public NPC_Princess(GamePanel gp) {
		super(gp);
		type = type_npc;
		direction = "down";
		
		name = NPC_Name;
		//SOLID AREA FOR COLLISION DETECT
		solidArea = new Rectangle(gp.tileSize/4, gp.tileSize/4, gp.tileSize/2, gp.tileSize-(gp.tileSize/4));
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		currentSearchPath = pathOFF;
		speed = 1;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "princess/up0");
		up2 = createImage("npc", "princess/up1");
		up3 = createImage("npc", "princess/up2");
		up4 = createImage("npc", "princess/up3");

		down1 = createImage("npc", "princess/down0");
		down2 = createImage("npc", "princess/down1");
		down3 = createImage("npc", "princess/down2");
		down4 = createImage("npc", "princess/down3");

		right1 = createImage("npc", "princess/right0");
		right2 = createImage("npc", "princess/right1");
		right3 = createImage("npc", "princess/right2");
		right4 = createImage("npc", "princess/right3");

		left1 = createImage("npc", "princess/left0");
		left2 = createImage("npc", "princess/left1");
		left3 = createImage("npc", "princess/left2");
		left4 = createImage("npc", "princess/left3");

	}
	
	public void setAction() {
		switch (currentSearchPath) {
		case pathOFF: 
			actionDelay++;
			if(actionDelay == 120 ) {
				
				if(!lockDirection) {
					int n = rN.nextInt(100)+1;
					
					if(n<=25) direction = "up";
					if(n>=25 && n<=50) direction = "down";
					if(n>=50 && n<=75) direction = "left";
					if(n>=75 && n<=100) direction = "right";
					actionDelay = 0;
				}
				
				
			}
			break;
		case find_player: searchPath(gp.player.getPlayerWordlX(), gp.player.getPlayerWordlY(), false); break;
		case chase_player: searchPath(gp.player.getPlayerWordlX(), gp.player.getPlayerWordlY(), true); break;
		case find_home: searchPath(21,41,false); break;
		}
		
	}
	public void setDialouge() {
		int i = 0;
		dialogues[thankPlayerA][i] = "Thank you, brave hero! You have saved me from an\n"
				+ "eternity of darkness. I am forever in your debt."; i++;
		dialogues[thankPlayerA][i] = "I, Princess Riri, owe you my life!"; i++;
				
		i = 0;
		dialogues[thankPlayerB][i] = "H-hero.."; i++;
		dialogues[thankPlayerB][i] = "Please, don't stare at me too much.."; i++;
		dialogues[thankPlayerB][i] = "It's quite embarassing..."; i++;
		
		i = 0;
		dialogues[thankPlayerC][i] = "H-hero.."; i++;
		dialogues[thankPlayerC][i] = "May I ask one more favor?"; i++;
		dialogues[thankPlayerC][i] = "I fear I'm lost in these halls. Would you guide me back\nto safety?"; i++;
		
		i = 0;
		dialogues[thankPlayerD][i] = "Yes, well, it's all thanks to this man!"; i++;
		dialogues[thankPlayerD][i] = "I owe him my life!"; i++;
		dialogues[thankPlayerD][i] = "May the winds guide you safely on your path, my hero."; i++;
		dialogues[thankPlayerD][i] = "Until we meet again!"; i++;
		
		
		i = 0;
		dialogues[playerRequestA][i] = "..."; i++;
		dialogues[playerRequestA][i] = "I'd be happy to help you, hero!"; i++;
		dialogues[playerRequestA][i] = "Please, tell me how I could be of help"; i++;
		
		i = 0;
		dialogues[playerRequestB][i] = "...!"; i++;
		dialogues[playerRequestB][i] = "Wh-what did you just said?!"; i++;
		dialogues[playerRequestB][i] = "You have a water crystal?!"; i++;
		dialogues[playerRequestB][i] = "That's the final item that I need to open the\n"
				+ "portal where the Trenk Lord resides!"; i++;
		dialogues[playerRequestB][i] = "Please, take me with you! I need to use that crystal!"; i++;
		
		i = 0;
		dialogues[playerRequestC][i] = "No, you have to take care of the castle\n"
				+ "while I'm not here.."; i++;
		
		i = 0;
		dialogues[playerRequestD][i] = "I'm sure nobody here is as strong as you, hero\n"
				+ "I wouldn't worry going alone with you!"; i++;
		dialogues[playerRequestD][i] = "Please take care of me, guide the way!"; i++;
		
		i = 0;
		dialogues[playerRequestE][i] = "..."; i++;
		dialogues[playerRequestE][i] = "Leave it to me, I shall do my best!"; i++;
		
		i = 0;
		dialogues[playerRequestF][i] = "...!"; i++;
		dialogues[playerRequestF][i] = "T-this water crystal..! I-it's"; i++;
		dialogues[playerRequestF][i] = "It has no magic in it at all..!"; i++;
		dialogues[playerRequestF][i] = "It may because you have successfully activated it, but\n"
				+ "the evil power of the Trenk Lord is much stronger.."; i++;
		dialogues[playerRequestF][i] = "..."; i++;
		dialogues[playerRequestF][i] = "Fortunately, I could still make use of this to craft an item!"; i++;
		dialogues[playerRequestF][i] = "Hero, go get me 25 Trenk Meats!"; i++;
		dialogues[playerRequestF][i] = "I also need a Wooden Sword and an Iron Sword!"; i++;
		dialogues[playerRequestF][i] = "I shall craft you a weapon that will kill that monster!"; i++;
				
				
		i = 0;
		dialogues[playerCraftA][i] = "You're back already?!"; i++;
		dialogues[playerCraftA][i] = "Let's see if you have all the materials needed.."; i++;
		
		i = 0;
		dialogues[playerCraftB][i] = "...!"; i++;
		dialogues[playerCraftB][i] = "It seems you still have not gathered all of them"; i++;
		dialogues[playerCraftB][i] = "Please come back when you are finished gathering\n"
				+ "all the materials as I instructed."; i++;
		dialogues[playerCraftB][i] = "Goodluck, Hero!"; i++;
		
		i = 0;
		dialogues[playerCraftC][i] = "...!"; i++;
		dialogues[playerCraftC][i] = "Wonderful! You have all that is needed!"; i++;
		dialogues[playerCraftC][i] = "Please wait as I craft you the items!"; i++;
		dialogues[playerCraftC][i] = "...!"; i++;
		dialogues[playerCraftC][i] = "There..! A successful enchantment!"; i++;
		dialogues[playerCraftC][i] = "Now, I shall also create tha magic item that will take you\n"
				+ "where that monster resides.."; i++;
		dialogues[playerCraftC][i] = "There..! Another successful enchantment!"; i++;
				
		i = 0;
		dialogues[playerCraftD][i] = "Hero, once you have used this Vorpal Stone, you will\n"
				+ "be teleported to the Trenk Lord's palace.."; i++;
		dialogues[playerCraftD][i] = "There's no going back then.."; i++;
		dialogues[playerCraftD][i] = "The only way to return is to kill that monster.."; i++;
		dialogues[playerCraftD][i] = "You have to withstand all of his summons!"; i++;
		dialogues[playerCraftD][i] = "You can't kill him as he's hiding his real physical heart"; i++;
		dialogues[playerCraftD][i] = "You have to tire him out..! Until he has no energy\n"
				+ "left to conceal his physical heart!"; i++;
		dialogues[playerCraftD][i] = "Prepare wisely before going to the battle field!"; i++;
		dialogues[playerCraftD][i] = "I wish you all the luck, my hero!"; i++;
	}
	public void speak() {
		
		if(GameProgress.princessReunited && gp.currentMap == gp.silvioHouse) {
			if(!gp.player.itemIsInsideInventory(OBJ_TerraBlade.objName))
				CS_princessCraft();
		}
		else {
			startDialogue(this, dialogueSet);
		}
		facePlayer();
	}	
	private void CS_princessCraft() {
		gp.gameState = gp.cutSceneState;
		gp.csHandler.sceneNum = gp.csHandler.princessCraft;
	}
	
	
	
	public void update() {
		super.update();
		if(gp.currentMap == gp.princessKingdom) {
			direction = "down";
			spriteNum = 1;
			speed = 0;
		}
	}

}
