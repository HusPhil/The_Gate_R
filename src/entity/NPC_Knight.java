package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Knight extends Entity{
	//DialogueSets
	public static final int princessInfoA = 0;
	public static final int princessInfoB = 1;
	public static final int princessInfoC = 2;
	public static final int princessBackA = 3;
	
	public static final int princessReunitedA = 4;
	public static final int princessReunitedB = 5;
	public static final int princessReunitedC = 6;
	public static final int princessReunitedD = 7;

	
	public static final int find_home = 2;
	public static final String NPC_Name = "Knight";
	public NPC_Knight(GamePanel gp) {
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
		up1 = createImage("npc", "knight/up0");
		up2 = createImage("npc", "knight/up1");
		up3 = createImage("npc", "knight/up2");
		up4 = createImage("npc", "knight/up3");

		down1 = createImage("npc", "knight/down0");
		down2 = createImage("npc", "knight/down1");
		down3 = createImage("npc", "knight/down2");
		down4 = createImage("npc", "knight/down3");

		right1 = createImage("npc", "knight/right0");
		right2 = createImage("npc", "knight/right1");
		right3 = createImage("npc", "knight/right2");
		right4 = createImage("npc", "knight/right3");

		left1 = createImage("npc", "knight/left0");
		left2 = createImage("npc", "knight/left1");
		left3 = createImage("npc", "knight/left2");
		left4 = createImage("npc", "knight/left3");

	}
	
	public void setAction() {
		switch (currentSearchPath) {
		case find_home: searchPath(21,42, false); break;
		case pathOFF: 
			
			actionDelay++;
			if(actionDelay == 120 ) {
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
			break;
		}
		
	}
	public void setDialouge() {
		int i = 0;
		dialogues[princessInfoA][i] = "Hey! You there! Halt!"; i++;
		dialogues[princessInfoA][i] = "Identify yourself!"; i++;
		dialogues[princessInfoA][i] = "State why you are in this place, at once!"; i++;
		
		i = 0;
		dialogues[princessInfoB][i] = "I see, so you are seeking the Princess' help.."; i++;
		dialogues[princessInfoB][i] = "Forgive my rudeness earlier, actually we are also\n"
				+ "searching for the princess.."; i++;
		dialogues[princessInfoB][i] = "The truth is, she is held captive by the Evil Skeleton\nLord!"; i++;
		dialogues[princessInfoB][i] = "Many have tried to save her but no one came back\n"
				+ "alive."; i++;
		dialogues[princessInfoB][i] = "Would you dare risk your life, young adventurer?"; i++;
		
		i = 0;
		dialogues[princessInfoC][i] = "I see, so you are a one brave man yourself!"; i++;
		dialogues[princessInfoC][i] = "If you truly wish to save her, go down the stairs! "
				+ "Dare to\ndefeat the Evil Skeleton Lord and save the princess!"; i++;
		
				
		i = 0;
		dialogues[princessBackA][i] = "...!"; i++;
		dialogues[princessBackA][i] = "Princess! You're back!"; i++;
		dialogues[princessBackA][i] = "I can't belive it..!"; i++;
		
		i = 0;
		dialogues[princessReunitedA][i] = 
				"There's no need to be sad, Princess.."; i++;
		dialogues[princessReunitedA][i] = 
				"The King and the Queen surely is in a much\n"
				+ "better place now.."; i++;
		dialogues[princessReunitedA][i] = 
				"And I'm sure you can be a great ruler as they were.."; i++;
		dialogues[princessReunitedA][i] = 
				"..."; i++;
		dialogues[princessReunitedA][i] = 
				"Right, you said something about stealing a magic item\n"
				+ "in the cage of that Skeleton Monster.."; i++;
		dialogues[princessReunitedA][i] = 
				"Can it really be used to beat the Trenk Lord and\n"
				+ "stop this madness?"; i++;
				
		i = 0;
		dialogues[princessReunitedB][i] = 
				"...!"; i++;
		dialogues[princessReunitedB][i] = 
				"Y-you're here!"; i++;
		
		i = 0;
		dialogues[princessReunitedC][i] = 
				"..."; i++;
		dialogues[princessReunitedC][i] = 
				"I see, you said you have something to ask\n"
				+ "from the princess when we met.."; i++;
		dialogues[princessReunitedC][i] = 
				"I'm sorry for forgetting about that.."; i++;
				
		i = 0;
		dialogues[princessReunitedD][i] = 
				"...!"; i++;
		dialogues[princessReunitedD][i] = 
					"Princess..! You cannot go!"; i++;
		dialogues[princessReunitedD][i] = 
				"Please, atleast let me be by your side!"; i++;
				
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	
	public void update() {
		super.update();
		setAction();
		if(gp.currentMap == gp.princessKingdom) {
			direction = "down";
			spriteNum = 1;
			speed = 0;
		}
	}

}
