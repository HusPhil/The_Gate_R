package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Princess extends Entity{
	//DialogueSets
	public static final int cursed_talk = 0;

	public static final String NPC_Name = "Princess";
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
		dialogueSet = cursed_talk;
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
