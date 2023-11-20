package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_VillagerGirl extends Entity{
	//DialogueSets
	public static final int cursed_talk = 0;

	public static final String NPC_Name = "Villager Girl";
	public NPC_VillagerGirl(GamePanel gp) {
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
		up1 = createImage("npc", "villager_girl/up0");
		up2 = createImage("npc", "villager_girl/up1");
		up3 = createImage("npc", "villager_girl/up2");
		up4 = createImage("npc", "villager_girl/up3");

		down1 = createImage("npc", "villager_girl/down0");
		down2 = createImage("npc", "villager_girl/down1");
		down3 = createImage("npc", "villager_girl/down2");
		down4 = createImage("npc", "villager_girl/down3");

		right1 = createImage("npc", "villager_girl/right0");
		right2 = createImage("npc", "villager_girl/right1");
		right3 = createImage("npc", "villager_girl/right2");
		right4 = createImage("npc", "villager_girl/right3");

		left1 = createImage("npc", "villager_girl/left0");
		left2 = createImage("npc", "villager_girl/left1");
		left3 = createImage("npc", "villager_girl/left2");
		left4 = createImage("npc", "villager_girl/left3");

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
		dialogues[cursed_talk][i] = "Thank you for saving the village!"; i++;
		dialogues[cursed_talk][i] = "Goodluck on your adventure!"; i++;
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}	


}
