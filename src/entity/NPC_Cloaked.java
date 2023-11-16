package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Cloaked extends Entity{
	//DialogueSets
	public static final int cursed_talk = 0;

	public static final String NPC_Name = "Cloaked";
	public NPC_Cloaked(GamePanel gp) {
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
		dialogueSet = cursed_talk;
		getNpcImage();
		setDialouge();
	}

	private void getNpcImage() {
		up1 = createImage("npc", "princess_cloaked/up0");
		up2 = createImage("npc", "princess_cloaked/up1");
		up3 = createImage("npc", "princess_cloaked/up2");
		up4 = createImage("npc", "princess_cloaked/up3");

		down1 = createImage("npc", "princess_cloaked/down0");
		down2 = createImage("npc", "princess_cloaked/down1");
		down3 = createImage("npc", "princess_cloaked/down2");
		down4 = createImage("npc", "princess_cloaked/down3");

		right1 = createImage("npc", "princess_cloaked/right0");
		right2 = createImage("npc", "princess_cloaked/right1");
		right3 = createImage("npc", "princess_cloaked/right2");
		right4 = createImage("npc", "princess_cloaked/right3");

		left1 = createImage("npc", "princess_cloaked/left0");
		left2 = createImage("npc", "princess_cloaked/left1");
		left3 = createImage("npc", "princess_cloaked/left2");
		left4 = createImage("npc", "princess_cloaked/left3");

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
		}
		
	}
	public void setDialouge() {
		int i = 0;
		dialogues[cursed_talk][i] = "Hello there, pal!"; i++;
		dialogues[cursed_talk][i] = "Thanks for saving the village!"; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
