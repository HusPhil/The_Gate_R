package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_VillagerBoy extends Entity{
	//DialogueSets
	public static final int cursed_talk = 0;

	public static final String NPC_Name = "Villager";
	public NPC_VillagerBoy(GamePanel gp) {
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
		up1 = createImage("npc", "villager_boy/up0");
		up2 = createImage("npc", "villager_boy/up1");
		up3 = createImage("npc", "villager_boy/up2");
		up4 = createImage("npc", "villager_boy/up3");

		down1 = createImage("npc", "villager_boy/down0");
		down2 = createImage("npc", "villager_boy/down1");
		down3 = createImage("npc", "villager_boy/down2");
		down4 = createImage("npc", "villager_boy/down3");

		right1 = createImage("npc", "villager_boy/right0");
		right2 = createImage("npc", "villager_boy/right1");
		right3 = createImage("npc", "villager_boy/right2");
		right4 = createImage("npc", "villager_boy/right3");

		left1 = createImage("npc", "villager_boy/left0");
		left2 = createImage("npc", "villager_boy/left1");
		left3 = createImage("npc", "villager_boy/left2");
		left4 = createImage("npc", "villager_boy/left3");

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
