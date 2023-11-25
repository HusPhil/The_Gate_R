package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.SKL_MudBall;

public class NPC_Princess extends Entity{
	//DialogueSets
	public static final int thankPlayerA = 0;
	public static final int thankPlayerB = 1;
	public static final int thankPlayerC = 2;
	public static final int thankPlayerD = 3;	
	
	//SearchPath
	public static final int find_player = 1;
	public static final int find_home = 2;

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
				int n = rN.nextInt(100)+1;
				
				if(n<=25) direction = "up";
				if(n>=25 && n<=50) direction = "down";
				if(n>=50 && n<=75) direction = "left";
				if(n>=75 && n<=100) direction = "right";
				actionDelay = 0;
			}
			break;
		case find_player: searchPath(gp.player.getPlayerWordlX(), gp.player.getPlayerWordlY()); break;
		case find_home: searchPath(21,41); break;
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
		
		
	}
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
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
