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
		case find_home: searchPath(21,41); break;
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

	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
